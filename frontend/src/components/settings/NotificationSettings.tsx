import {Button, Grid, Header, Segment} from 'semantic-ui-react';
import React, {useCallback, useEffect, useState} from 'react';
import {SessionContext} from '../../session';
import * as notificationService from '../../services/notification-service'

enum SubscriptionStatus {
    UNSUBSCRIBED,
    SUBSCRIBED
}

const NotificationSettings = () => {
    const [subStatus, setSubStatus] = useState<SubscriptionStatus>()
    const [subscribeError, setSubscribeError] = useState<string>()
    const session = React.useContext(SessionContext);

    const sendSubscription = useCallback((subscription: PushSubscription) => {
        notificationService.sendSubscription(subscription, session.session)
            .then(response => {
                setSubStatus(SubscriptionStatus.SUBSCRIBED)
            }, (error) => {
                console.log("Subscribing failed: " + error)
                setSubscribeError("Failed to subscribe")
            })
    }, [session]);

    const sendUnsubscribe = useCallback((subscription: PushSubscription) => {
        notificationService.sendUnsubscribe(subscription, session.session)
            .then(response => {
                notificationService.unsubscribe().then(() => {
                    setSubscribeError(undefined)
                    setSubStatus(SubscriptionStatus.UNSUBSCRIBED)
                })
            }, (error) => {
                console.log("Unsubscribing failed: " + error)
                setSubscribeError("Failed to unsubscribe")
            })
    }, [session]);

    useEffect(() => {
        notificationService.getExistingSubscription().then(sub => {
                sendSubscription(sub)
                setSubStatus(SubscriptionStatus.SUBSCRIBED)
            },
            () => {
                setSubStatus(SubscriptionStatus.UNSUBSCRIBED)
            }
        );
    }, [sendSubscription, setSubStatus])


    function registerPush() {
        notificationService.subscribe()
            .then(sub => {
                sendSubscription(sub)
                setSubStatus(SubscriptionStatus.SUBSCRIBED)
            }).catch(error => {
                if (Notification.permission === 'denied') {
                    setSubscribeError("Please allow notification permissions first")
                } else {
                    setSubscribeError('Unable to subscribe: ' + error);
                }
            }
        )
    }

    function unsubscribe() {
        notificationService.getExistingSubscription().then(sub => {
            sendUnsubscribe(sub)
        })
    }

    return (
        <Grid.Row style={{paddingTop: '1em'}}>
            <Grid.Column>
                <Header as="h2">Push notifications</Header>
                <Button
                    onClick={() => registerPush()}
                    disabled={subStatus === SubscriptionStatus.SUBSCRIBED}>
                    Enable Push Notifications
                </Button>
                {subStatus === SubscriptionStatus.SUBSCRIBED && (
                    <Segment>
                        <div>
                            Push Notifications enabled
                        </div>
                        <Button onClick={() => unsubscribe()} >
                            Unsubscribe
                        </Button>
                    </Segment>
                )}
                {subscribeError !== undefined && (
                    <Segment>{subscribeError}</Segment>
                )}
            </Grid.Column>
        </Grid.Row>
    )
}

export default NotificationSettings