import {AuditLog, Transaction, UndoApprovalResult} from '../../types';
import {Card, Icon, Segment, Table, Button} from 'semantic-ui-react';
import MonetaryAmount from '../MonetaryAmount';
import React, {useState} from 'react';
import UndoApproval from '../UndoApproval';

const AuditLogCard = ({auditlog}: { auditlog: AuditLog }) => {
    const [undoApprovalResult, setUndoApprovalResult] = useState<UndoApprovalResult>()

    function formatDate() {
        return new Date(auditlog.date).toLocaleDateString();
    }

    return (
        <Card fluid>
            <Card.Content>
                <div style={{float: "right"}}>
                    <UndoApproval
                        auditLogId={auditlog.id}
                        onUndoApproval={setUndoApprovalResult}
                        enabled={!undoApprovalResult?.success}
                    />
                </div>
                <Card.Header>
                    <span>{auditlog.payee}</span>
                </Card.Header>
                <Card.Meta>Approved by {auditlog.executingActor} @ {formatDate()}</Card.Meta>
                {undoApprovalResult !== undefined && (
                    <Card.Meta textAlign="right">
                        {undoApprovalResult.success ? "Transaction has been restored!" : "Transaction could not be restored"}
                    </Card.Meta>
                )}
                <Card.Description>
                    <AuditLogMatrix auditLog={auditlog}/>
                </Card.Description>
            </Card.Content>
        </Card>
    );
}

const AuditLogMatrix = ({auditLog}: { auditLog: AuditLog }) => {

    function renderCategory(transaction: Transaction) {
        return transaction.category ? transaction.category.name : "Uncategorized";
    }

    return (
        <Table>
            <Table.Body>
                {auditLog.transactions.map(({actor, oldTransaction, newTransaction}) => (
                    <Table.Row key={oldTransaction.id}>
                        <Table.Cell>
                            <Icon name="user circle"/>
                            {actor}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="folder"/>
                            {renderCategory(oldTransaction)}
                            <Icon name="long arrow alternate right"/>
                            {renderCategory(newTransaction)}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="dollar sign"/>
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