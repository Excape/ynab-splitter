import {AuditLog, Transaction} from '../../types';
import {Card, Icon, Table} from 'semantic-ui-react';
import MonetaryAmount from '../MonetaryAmount';
import React from 'react';

const AuditLogCard = ({auditlog}: { auditlog: AuditLog }) => {


    function formatDate() {
        return new Date(auditlog.date).toLocaleDateString();
    }

    return (
        <Card fluid>
            <Card.Content>
                <Card.Header>{auditlog.payee}</Card.Header>
                <Card.Meta>Approved by {auditlog.executingActor} @ {formatDate()}</Card.Meta>
                <Card.Description>
                    <AuditLogMatrix auditLog={auditlog}/>
                </Card.Description>
            </Card.Content>
        </Card>
    );
}

const AuditLogMatrix = ({auditLog}: {auditLog: AuditLog}) => {

    function renderCategory(transaction: Transaction ) {
        return transaction.category ? transaction.category.name : "Uncategorized";
    }
    return (
        <Table>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Name</Table.HeaderCell>
                    <Table.HeaderCell>Category</Table.HeaderCell>
                    <Table.HeaderCell>Amount</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {auditLog.transactions.map(({actor, oldTransaction, newTransaction}) => (
                    <Table.Row>
                        <Table.Cell>
                            <Icon name="user circle" />
                            {actor}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="folder" />
                            {renderCategory(oldTransaction)}
                            <Icon name="long arrow alternate right"/>
                            {renderCategory(newTransaction)}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="dollar sign" />
                            <MonetaryAmount amount={oldTransaction.amount}/>
                            <Icon name="long arrow alternate right"/>
                            <MonetaryAmount amount={newTransaction.amount}/>
                        </Table.Cell>
                    </Table.Row>
                ))}
            </Table.Body>
        </Table>
    )
}

export default AuditLogCard