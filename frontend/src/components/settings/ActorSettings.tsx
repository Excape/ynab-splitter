import {SettingsActor} from './types';
import React, {useCallback, useEffect, useState} from 'react';
import {Grid, Header, Icon, Loader, Table} from 'semantic-ui-react';
import AddActor from './AddActor';
import ReloadPageOnFail from '../ReloadPageOnFail';
import DeleteActor from './DeleteActor';

export const ActorSettings = () => {
    const [actors, setActors] = useState([] as SettingsActor[])
    const [actorsLoaded, setActorsLoaded] = useState(false)
    const [loadingFailed, setLoadingFailed] = useState(false)

    const fetchActors = useCallback(() => {
        return fetch("/api/v1/user/actors")
            .then(result => result.json())
            .then(actors => {
                setActors(actors)
                setActorsLoaded(true)
            }, error => {
                setLoadingFailed(true)
            })
    }, []);

    useEffect(() => {
        fetchActors();
    }, [fetchActors])

    function onActorAdded() {
        return fetchActors()
    }

    if (loadingFailed) {
        return <ReloadPageOnFail message={"Please reload the page"}/>
    }

    if (!actorsLoaded) {
        return <Loader active inline='centered'/>
    }

    function canAddMoreActors(): boolean {
        return actorsLoaded && actors.length < 2
    }

    function onDeleteActor() {
        return fetchActors()
    }

    return (
        <Grid.Row>
            <Grid.Column>
                <Table columns={3}>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell>Name</Table.HeaderCell>
                            <Table.HeaderCell>Budget</Table.HeaderCell>
                            <Table.HeaderCell>Account</Table.HeaderCell>
                            <Table.HeaderCell>Remove</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {actors.map(actor => (
                            <Table.Row key={actor.actorName}>
                                <Table.Cell>
                                    <Icon name="user"/>
                                    {actor.actorName}
                                </Table.Cell>
                                <Table.Cell>{actor.budgetName}</Table.Cell>
                                <Table.Cell>{actor.accountName}</Table.Cell>
                                <Table.Cell>
                                    <DeleteActor actorName={actor.actorName} onDeleteActor={onDeleteActor}/>
                                </Table.Cell>
                            </Table.Row>
                        ))}
                    </Table.Body>
                </Table>
            </Grid.Column>

            <Header as="h3">Add a new budget user</Header>
            {canAddMoreActors() ? (
                <AddActor onAddActor={onActorAdded}/>
            ) : <p>
                Cannot add more than two users
            </p>}

        </Grid.Row>
    )
}

export default ActorSettings
