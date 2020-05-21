import React from 'react';
import {Grid, Menu, MenuItemProps} from 'semantic-ui-react'
import {Link, Router} from '@reach/router'
import './App.css';
import UnapprovedTransactions from "./components/UnapprovedTransactions";
import AuditLogList from './components/AuditLogList';

const App = () => {
    const defaultMenu = 'transactions'
    const [activeMenu, setActiveMenu] = React.useState()


    function handleMenuClick(event: any, {name}: MenuItemProps) {
        setActiveMenu(name ? name : defaultMenu)
    }

    return (
        <Grid columns={1} className={"App-Grid"}>
            <Grid.Row>
                <Grid.Column>
                    <Menu>
                        <Menu.Item
                            name={"transactions"}
                            onClick={handleMenuClick}
                            active={activeMenu === "transactions"}
                            as={Link}
                            to={"/"}
                        >
                            Unapproved Transactions
                        </Menu.Item>
                        <Menu.Item
                            name={"auditlog"}
                            onClick={handleMenuClick}
                            active={activeMenu === "auditlog"}
                            as={Link}
                            to={"auditlog"}
                        >
                            Audit Log
                        </Menu.Item>
                    </Menu>
                </Grid.Column>
            </Grid.Row>
            <Grid.Row>
                <Grid.Column>
                    <Router>
                        <UnapprovedTransactions path={"/"}/>
                        <AuditLogList path={"auditlog"}/>
                    </Router>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    );
}


export default App;
