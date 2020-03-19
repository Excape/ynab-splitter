import React, {useEffect} from 'react';
import {Card} from "semantic-ui-react";
import {UnapprovedTransaction} from "../types";
import TransactionCard from "./TransactionCard";


const UnapprovedTransactions = () => {
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


    if (!isLoaded) {
        return (<div>"Loading..."</div>)
    }
    return (

        <Card.Group>
            {items!.map(item => (
                <TransactionCard transaction={item} key={item.id}/>
            ))}
        </Card.Group>

    );
};

export default UnapprovedTransactions;