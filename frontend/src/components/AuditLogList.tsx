import React, {useEffect} from "react";
import {AuditLog} from "../types";
import {RouteComponentProps} from '@reach/router';
import {Card, Icon} from 'semantic-ui-react';

const AuditLogList = (props: RouteComponentProps) => {
    const [isLoaded, setIsLoaded] = React.useState(false);
    const [items, setItems] = React.useState([] as AuditLog[])

    useEffect(() => {
        fetch("/api/v1/auditlog")
            .then(result => result.json())
            .then(result => {
                    setItems(result);
                    setIsLoaded(true)
                },
                error => setIsLoaded(false)
            );
    }, []);



    if (!isLoaded) {
        return (<div>Loading...</div>)
    }

    return (
        <Card.Group>
            {/* TODO sort*/}
            {items!
                .map(item => (
                    <AuditLogCard auditlog={item} key={item.newTransaction.id}/>
                ))}
        </Card.Group>
    );
}

const AuditLogCard = ({auditlog}: { auditlog: AuditLog }) => {

    function renderActor(actor: string) {
        return actor.charAt(0) + actor.toLowerCase().slice(1);
    }

    return (
        <Card>
            <Card.Content>
                <Card.Header>{auditlog.newTransaction.payee} ({renderActor(auditlog.newTransaction.actor)})</Card.Header>
                <Card.Meta>{auditlog.newTransaction.date}</Card.Meta>
                <Card.Meta>Approved by {renderActor(auditlog.executingActor)}</Card.Meta>
                <Card.Description>
                    <Icon name="folder"/>
                    {auditlog.oldTransaction.category?.name} -> {auditlog.newTransaction.category?.name}
                </Card.Description>
                <Card.Description>
                    <Icon name="dollar sign"/>
                    {auditlog.oldTransaction.amount / 1000} -> {auditlog.newTransaction.amount / 1000}
                </Card.Description>
            </Card.Content>
        </Card>
    );
}

export default AuditLogList;