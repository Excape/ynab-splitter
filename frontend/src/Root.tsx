import React, {useContext, useEffect} from 'react';
import {BrowserRouter, Route, Redirect, useHistory} from 'react-router-dom';
import App from './App';
import {Button} from 'semantic-ui-react';
import {clearSessionCookie, getSessionCookie, setSessionCookie, SessionContext} from './session';
import ActorChooser from './components/ActorChooser';

const Root = () => {
    const [session, setSession] = React.useState(getSessionCookie())

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
                <Route path="/choose">
                    <ActorChooser/>
                </Route>
                <Route path="*">
                    <ProtectedHandler />
                </Route>
            </BrowserRouter>
        </SessionContext.Provider>
    )
}

const ProtectedHandler = () => {
    const {session} = useContext(SessionContext);
    if (session === undefined) {
        return (
            <Redirect to="/choose" />
        )
    } else {
        return (<App />)
    }

}

export default Root;