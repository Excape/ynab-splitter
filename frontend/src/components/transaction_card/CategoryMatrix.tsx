import {UnapprovedTransaction} from '../../types';
import {Icon, Table} from 'semantic-ui-react';
import React from 'react';
import {getCategory} from './TransactionCard';

const CategoryMatrix = ({transaction}: { transaction: UnapprovedTransaction }) => {
    return (
        <Table unstackable={true} fixed columns={3}>
            <Table.Body>
                {transaction.actors.map(actor => (
                    <Table.Row key={actor}>
                        <Table.Cell>
                            <Icon name="user circle"/>
                            {actor}
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="arrow right"/>
                        </Table.Cell>
                        <Table.Cell>
                            <Icon name="folder"/>
                            {getCategory(transaction, actor)?.name ?? "?"}
                        </Table.Cell>
                    </Table.Row>
                ))}
            </Table.Body>
        </Table>
    )
}

export default CategoryMatrix