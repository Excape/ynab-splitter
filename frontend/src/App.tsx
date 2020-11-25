import React from 'react';
import {Grid} from 'semantic-ui-react'
import './App.css';
import UnapprovedTransactions from "./components/UnapprovedTransactions";
import AuditLogList from './components/auditlog/AuditLogList';
import NavBar from './components/NavBar';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";

const App = () => {
    return (
            <Grid columns={1} className={"App-Grid"}>
                <Grid.Row>
                    <Grid.Column>
                        <NavBar />
                    </Grid.Column>
                </Grid.Row>
                <Grid.Row>
                    <Grid.Column>
                        <Switch>
                            <Route path={"/auditlog"}>
                                <AuditLogList />
                            </Route>
                            <Route path={"/"}>
                                <UnapprovedTransactions />
                            </Route>
                        </Switch>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
    );
}


export default App;
