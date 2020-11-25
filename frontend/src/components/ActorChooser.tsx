import {useHistory} from 'react-router-dom';
import React, {useContext, useEffect, useState} from 'react';
import {SessionContext, setSessionCookie} from '../session';
import {Button, Grid, Header} from 'semantic-ui-react';
import {Actor} from '../types';

const ActorChooser = () => {
    const history = useHistory()
    const session = useContext(SessionContext)
    const [actors, setActors] = useState([] as Actor[])

    useEffect(() => {
        fetch(`/api/v1/actors`)
            .then(result => result.json())
            .then(result => {
                    setActors(result)
                }
            );
    }, [actors])

    function onSelect(actor: Actor) {
        setSessionCookie({actor: actor.name})
        session.login()
        history.replace("/")
    }

    return (
        <Grid columns={1} className={"App-Grid"}>
            <Grid.Row>
                <Grid.Column>
                    <Header as="h1">Who are you?</Header>
                    {/*<Button.Group fluid>*/}
                        {actors.map((actor, i) => (
                            <Button key={i} fluid onClick={() => onSelect(actor)}>{actor.name}</Button>)
                        )}
                    {/*</Button.Group>*/}

                </Grid.Column>
            </Grid.Row>
        </Grid>
    )
}

export default ActorChooser