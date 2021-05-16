import {AuditLog, TransactionsByActor} from '../../types';
import {Icon, Table} from 'semantic-ui-react';
import React from 'react';
import MonetaryAmount from '../MonetaryAmount';

const CategoryMatrix = ({auditLog}: { auditLog: AuditLog }) => {

    function amountIsZero(transactionsByActor: TransactionsByActor) {
        return transactionsByActor.newTransaction.amount === 0;
    }

    return (
        <Table unstackable={true} fixed columns={4}>
            <Table.Body>
                {auditLog.transactions.map(transactionsByActor => (
                    <Table.Row key={transactionsByActor.actor}
                               disabled={amountIsZero(transactionsByActor)}
                               positive={!amountIsZero(transactionsByActor)}>
                        <Table.Cell>
                            <Icon name="user circle"/>
                            {transactionsByActor.actor}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="arrow right"/>
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="folder"/>
                            {transactionsByActor.newTransaction.category?.name ?? "?"}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="dollar sign"/>
                            <MonetaryAmount amount={transactionsByActor.newTransaction.amount}/>
                        </Table.Cell>
                    </Table.Row>
                ))}
            </Table.Body>
        </Table>
    )
}

export default CategoryMatrix
