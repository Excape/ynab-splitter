import React from 'react';
import {Container, Grid, GridColumn, Header, List} from 'semantic-ui-react'
import './App.css';
import UnapprovedTransactions from "./UnapprovedTransactions";

const App = () => (
    <Grid columns={1}>
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
