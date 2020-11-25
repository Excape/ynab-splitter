import React, {useEffect} from "react";
import {AuditLog, Transaction} from "../../types";
import {Card, Grid, Icon, Loader} from 'semantic-ui-react';
import {renderAmount} from '../../util';

const AuditLogList = () => {
    const [isLoaded, setIsLoaded] = React.useState(false);
    const [items, setItems] = React.useState([] as AuditLog[])

    useEffect(() => {
        fetch("/api/v1/auditlog")
            .then(result => result.json())
            .then(result => {
                    let sortedItems = [...result]
                    sortedItems.sort((a: AuditLog, b: AuditLog) =>
                        new Date(b.date).getTime() - new Date(a.date).getTime())
                    setItems(sortedItems);
                    setIsLoaded(true)
                },
                error => setIsLoaded(false)
            );
    }, []);

    if (!isLoaded) {
        return (<Loader active inline='centered' />)
    }

    return (
        <Grid stackable columns={3}>
            {items!
                .map(item => (
                    <Grid.Column  key={item.newTransaction.id}>
                        <AuditLogCard auditlog={item}/>
                    </Grid.Column>
                ))}
        </Grid>
    );
}

const AuditLogCard = ({auditlog}: { auditlog: AuditLog }) => {

    function renderActor(actor: string) {
        return actor.charAt(0) + actor.toLowerCase().slice(1);
    }

    function renderCategory(transaction: Transaction ) {
        return transaction.category ? transaction.category.name : "Uncategorized";
    }

    function renderTransactionAmount(transaction: Transaction) {
        return renderAmount(transaction.amount)
    }

    return (
        <Card fluid>
            <Card.Content>
                <Card.Header>{auditlog.newTransaction.payee} ({renderActor(auditlog.newTransaction.actor.name)})</Card.Header>
                <Card.Meta>{auditlog.newTransaction.date}</Card.Meta>
                <Card.Meta>Approved by {renderActor(auditlog.executingActor)}</Card.Meta>
                <Card.Description>
                    <Icon name="folder"/>
                    {renderCategory(auditlog.oldTransaction)} -> {renderCategory(auditlog.newTransaction)}
                </Card.Description>
                <Card.Description>
                    <Icon name="dollar sign"/>
                    <strong>{renderTransactionAmount(auditlog.oldTransaction)} -> {renderTransactionAmount(auditlog.newTransaction)}</strong>
                </Card.Description>
            </Card.Content>
        </Card>
    );
}

export default AuditLogList;