import {useHistory} from 'react-router-dom';
import React, {useContext, useEffect, useState} from 'react';
import {SessionContext, setSessionCookie} from '../session';
import {Button, Grid, Header, Segment} from 'semantic-ui-react';
import {Actor} from '../types';
import ActorSettings from './settings/ActorSettings';

const ActorChooser = () => {
    const history = useHistory()
    const session = useContext(SessionContext)
    const [actors, setActors] = useState([] as Actor[])
    const [actorsLoaded, setActorsLoaded] = useState(false)

    useEffect(() => {
        fetch(`/api/v1/actors`)
            .then(result => result.json())
            .then(result => {
                    setActors(result)
                    setActorsLoaded(true)
                }
            );
    }, [setActors, setActorsLoaded])

    function onSelect(actor: Actor) {
        setSessionCookie({actor: actor.name})
        session.login()
        history.replace("/")
    }


    function actorsDefined() {
        return actorsLoaded && actors.length > 0;
    }

    return (
        <Grid columns={1} className={"App-Grid"}>
            <Grid.Row>
                <Grid.Column>
                    {actorsDefined() ? (
                        <div>
                            <Header as="h1">Who are you?</Header>
                            {/* todo: Improve button layout */}
                            {/*<Button.Group fluid>*/}
                            {actors.map((actor, i) => (
                                <Button key={i} fluid onClick={() => onSelect(actor)}>{actor.name}</Button>)
                            )}
                            {/*</Button.Group>*/}
                        </div>
                    ) : <NoActorsDefinedDialog/>}
                </Grid.Column>
            </Grid.Row>
        </Grid>
    )
}

const NoActorsDefinedDialog = () => {
    const history = useHistory()

    function redirectToApp() {
        history.push("/")
    }

    return (
        <div>
            <Header as="h3">
                Hello there! Please set up 2 accounts first:
            </Header>
            <Segment basic>
            <ActorSettings />
            </Segment>
            <Grid.Row>
                <Grid.Column>
                    <Button onClick={() => redirectToApp()}>
                        Done
                    </Button>
                </Grid.Column>
            </Grid.Row>
        </div>
    )
}

export default ActorChooser