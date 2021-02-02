import React, {useContext, useEffect} from 'react';
import {BrowserRouter, Redirect, Route, Switch} from 'react-router-dom';
import App from './App';
import {clearSessionCookie, getSessionCookie, SessionContext} from './session';
import ActorChooser from './components/ActorChooser';
import * as notificationService from './services/notification-service'

const Root = () => {
    const [session, setSession] = React.useState(getSessionCookie())

    useEffect(() => {
        // send updated notification to server
        notificationService.getExistingSubscription()
            .then(
                sub => notificationService.sendSubscription(sub, session),
                () => console.log("no subscription present")
            )
    })

    function performLogin() {
        setSession(getSessionCookie())
    }

    function performLogout() {
        clearSessionCookie()
        setSession(undefined)
    }


    const value = {
        session: session,
        login: performLogin,
        logout: performLogout
    }

    return (
        <SessionContext.Provider value={value}>
            <BrowserRouter>
                <Switch>
                    <Route path="/choose">
                        <ActorChooser/>
                    </Route>
                    <Route path="*">
                        <ProtectedHandler/>
                    </Route>
                </Switch>
            </BrowserRouter>
        </SessionContext.Provider>
    )
}

const ProtectedHandler = () => {
    const {session} = useContext(SessionContext);
    if (session === undefined) {
        return (
            <Redirect to="/choose"/>
        )
    } else {
        return (
            <App/>
        )
    }

}

export default Root;