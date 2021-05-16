import {AuditLog} from '../../types';
import {Icon, Table} from 'semantic-ui-react';
import React from 'react';
import MonetaryAmount from '../MonetaryAmount';

const CategoryMatrix = ({auditLog}: { auditLog: AuditLog }) => {
    return (
        <Table unstackable={true} fixed columns={4}>
            <Table.Body>
                {auditLog.transactions.map(transactionByActor => (
                    <Table.Row key={transactionByActor.actor}>
                        <Table.Cell>
                            <Icon name="user circle"/>
                            {transactionByActor.actor}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="arrow right"/>
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="folder"/>
                            {transactionByActor.newTransaction.category?.name ?? "?"}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="dollar sign"/>
                            <MonetaryAmount amount={transactionByActor.newTransaction.amount}/>
                        </Table.Cell>
                    </Table.Row>
                ))}
            </Table.Body>
        </Table>
    )
}

export default CategoryMatrix
