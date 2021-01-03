import React, {useEffect} from 'react';
import {UserSettings} from './types';
import {Button, Loader} from 'semantic-ui-react';
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
            <div>
                <ul>
                {actors.map(actor => (
                    <li>{actor.name}</li>
                ))}
                </ul>
            </div>

            <AddActor />

        </div>
    )
}

export default Settings