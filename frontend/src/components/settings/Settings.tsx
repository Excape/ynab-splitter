import React, {useEffect, useState} from 'react';
import {SettingsActor, UserSettings} from './types';
import {Loader, Grid} from 'semantic-ui-react';
import AddActor from './AddActor';

const Settings = () => {
    const [userSettings, setUserSettings] = React.useState<UserSettings>()
    const [userLoaded, setUserLoaded] = React.useState(false)

    useEffect(() => {
        fetch("/api/v1/user")
            .then(result => result.json())
            .then(user => {
                setUserSettings(user.settings)
                setUserLoaded(true)
            })
    }, [])


    if (!userLoaded) {
        return (<Loader active inline='centered' />)
    }

    const {actors} = userSettings!

    return (
        <div>
            <h2>Configure Budgets and Accounts</h2>
            <ActorSettings initActors={actors}/>
        </div>
    )
}

export const ActorSettings = ({initActors}: {initActors: SettingsActor[]}) => {
    const [actors, setActors] = useState(initActors)

    function onActorAdded(actor: SettingsActor) {
        const updatedActors = [ ...actors ]
        updatedActors.push(actor)
        setActors(updatedActors)
    }
    // TODO: Improve actor list
    return (
        <Grid.Row>
            <Grid.Column>
                <ul>
                    {actors.map(actor => (
                        <li>{actor.name}</li>
                    ))}
                </ul>
            </Grid.Column>

            <AddActor onAddActor={onActorAdded}/>
        </Grid.Row>
    )
}

export default Settings