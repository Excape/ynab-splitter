import React from 'react';
import {Button, Icon, Segment} from 'semantic-ui-react';
import * as notificationService from '../services/notification-service'
import {SessionContext} from '../session';

const ReloadPageOnFail = ({message}: { message: string }) => {

    const session = React.useContext(SessionContext);

    function reloadPage() {
        if ("serviceWorker" in navigator) {
            navigator.serviceWorker.ready.then((registration) => {
                notificationService.getExistingSubscription().then(
                    (sub) => {
                        notificationService.sendUnsubscribe(sub, session!.session).then(() =>
                            notificationService.unsubscribe())
                    },
                    (error) => console.log('no subscription to unsubscribe'))
                registration.unregister()
                window.location.reload()
            })
        } else {
            window.location.reload()
        }
    }

    return (
        <div>
            <Segment basic textAlign="center">
                <em>{message}</em>
            </Segment>
            <Segment basic textAlign="center">
                <Button onClick={() => reloadPage()}>
                    <Icon name="redo alternate"/>
                    Reload
                </Button>
            </Segment>
        </div>
    )
}

export default ReloadPageOnFail