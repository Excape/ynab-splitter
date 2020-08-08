import React, {useEffect} from 'react';
import {Card, Loader} from "semantic-ui-react";
import {UnapprovedTransaction} from "../types";
import TransactionCard from "./TransactionCard";
import {RouteComponentProps} from '@reach/router';


const UnapprovedTransactions = (props: RouteComponentProps) => {
    const [isLoaded, setIsLoaded] = React.useState(false);
    const [items, setItems] = React.useState([] as UnapprovedTransaction[]);


    useEffect(() => {
        fetch("/api/v1/transactions")
            .then(result => result.json())
            .then(result => {
                    setItems(result);
                    setIsLoaded(true);
                },
                error => setIsLoaded(false)
            );
    }, []);

    function getTime(date?: Date) {
        return date != null ? new Date(date).getTime() : 0;
    }


    if (!isLoaded) {
        return (<Loader active inline='centered' />)
    }

    if (isLoaded && items!.length === 0) {
        return (<div>No unapproved transactions</div>)
    }
    return (

        <Card.Group>
            {items!
                .sort((a, b) => getTime(b.date) - getTime(a.date))
                .map(item => (
                <TransactionCard transaction={item} key={item.id}/>
            ))}
        </Card.Group>

    );
};

export default UnapprovedTransactions;