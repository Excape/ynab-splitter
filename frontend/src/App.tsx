import React from 'react';
import {Grid, Menu} from 'semantic-ui-react'
import './App.css';
import UnapprovedTransactions from "./components/UnapprovedTransactions";
import AuditLogList from './components/AuditLogList';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    NavLink
} from "react-router-dom";

const App = () => {
    return (
        <Router>
            <Grid columns={1} className={"App-Grid"}>
                <Grid.Row>
                    <Grid.Column>
                        <Menu fluid widths={2}>
                            <Menu.Item
                                name={"transactions"}
                                exact // needed for active element of router to work
                                as={NavLink}
                                to={"/"}>
                                Unapproved Transactions
                            </Menu.Item>
                            <Menu.Item
                                name={"auditlog"}
                                exact
                                as={NavLink}
                                to={"/auditlog"}>
                                Audit Log
                            </Menu.Item>
                        </Menu>
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
        </Router>
    );
}


export default App;
