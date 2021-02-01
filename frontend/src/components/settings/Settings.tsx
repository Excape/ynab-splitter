import React from 'react';
import ActorSettings from './ActorSettings';
import NotificationSettings from './NotificationSettings';

const Settings = () => {

    return (
        <div>
            <h2>Configure Budgets and Accounts</h2>
            <ActorSettings />
            <NotificationSettings />
        </div>
    )
}

export default Settings