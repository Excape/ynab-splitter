import React, {PropsWithChildren} from 'react';
import {Grid} from 'semantic-ui-react'
import './App.css';
import UnapprovedTransactions from "./components/UnapprovedTransactions";
import AuditLogList from './components/auditlog/AuditLogList';
import NavBar from './components/NavBar';
import {Route, Switch} from "react-router-dom";
import Settings from './components/settings/Settings';

const App = () => {
    return (
        <AppGrid>
            <Grid.Row>
                <Grid.Column>
                    <NavBar/>
                </Grid.Column>
            </Grid.Row>
            <Grid.Row>
                <Grid.Column>
                    <Switch>
                        <Route path={"/auditlog"}>
                            <AuditLogList/>
                        </Route>
                        <Route path={"/settings"}>
                            <Settings/>
                        </Route>
                        <Route path={"/"}>
                            <UnapprovedTransactions/>
                        </Route>
                    </Switch>
                </Grid.Column>
            </Grid.Row>
        </AppGrid>
    );
}

export const AppGrid = ({children}: PropsWithChildren<any>) => {
    return (
        <Grid columns={1} className={"App-Grid"}>
            {children}
        </Grid>
    )
}


export default App;
