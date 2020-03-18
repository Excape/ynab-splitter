import React from 'react';
import {Grid, Header} from 'semantic-ui-react'
import './App.css';
import UnapprovedTransactions from "./components/UnapprovedTransactions";

const App = () => (
    <Grid columns={1} className={"App-Grid"}>
        <Grid.Row>
            <Grid.Column>
                <Header as="h1">Unapproved Transactions</Header>
            </Grid.Column>
        </Grid.Row>
        <Grid.Row>
            <Grid.Column>
                <UnapprovedTransactions />
            </Grid.Column>
        </Grid.Row>
    </Grid>
);


export default App;
