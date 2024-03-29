import React, {useEffect, useState} from 'react';
import {Grid, Loader} from "semantic-ui-react";
import {UnapprovedTransaction} from "../types";
import TransactionCard from "./transaction_card/TransactionCard";
import ReloadPageOnFail from './ReloadPageOnFail';

const UnapprovedTransactions = () => {
    const [isLoaded, setIsLoaded] = useState(false);
    const [loadingFailed, setLoadingFailed] = useState(false)
    const [items, setItems] = useState([] as UnapprovedTransaction[]);


    useEffect(() => {
        fetch("/api/v1/transactions", {redirect: "error"})
            .then(result => result.json())
            .then(result => {
                    setItems(result);
                    setIsLoaded(true);
                },
                error => setLoadingFailed(true)
            );
    }, [setItems, setIsLoaded]);

    function getTime(date?: Date) {
        return date != null ? new Date(date).getTime() : 0;
    }

    if (loadingFailed) {
        return (<ReloadPageOnFail message="Please reload to show transactions" />)
    }

    if (!isLoaded) {
        return (<Loader active inline='centered' />)
    }

    if (isLoaded && items!.length === 0) {
        return (<div>No unapproved transactions</div>)
    }
    return (
        <Grid stackable columns={2}>
            {items!
                .sort((a, b) => getTime(b.date) - getTime(a.date))
                .map(item => (
                    <Grid.Column key={item.id}>
                        <TransactionCard transaction={item} key={item.id}/>
                    </Grid.Column>
                ))}
        </Grid>
    );
};

export default UnapprovedTransactions;