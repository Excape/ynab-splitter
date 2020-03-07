import React from 'react';
import {Table} from "semantic-ui-react";

type Props = {
};

type State = {
    isLoaded: boolean,
    items?: [UnapprovedTransaction]
};

type UnapprovedTransaction = {
    id: string,
    date: Date,
    amount: number,
    category: string,
    memo: string,
    payee: string;
}

class UnapprovedTransactions extends React.Component<Props, State> {
    state: State = {
        isLoaded: false
    };

    componentDidMount() {
        fetch("/api/v1/transactions")
            .then(result => result.json())
            .then(
                (result) => this.setState({
                    isLoaded: true,
                    items: result
                }),
                error => this.setState({
                    isLoaded: false
                })
            );
    }

    render() {
        const { isLoaded, items } = this.state;
        if(!isLoaded) {
            return "Loading..."
        }
        return (
            <Table celled>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Date</Table.HeaderCell>
                        <Table.HeaderCell>Payee</Table.HeaderCell>
                        <Table.HeaderCell>Category</Table.HeaderCell>
                        <Table.HeaderCell>Memo</Table.HeaderCell>
                        <Table.HeaderCell>Amount</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {items!.map(item => (
                        <Table.Row>
                            <Table.Cell>{item.date}</Table.Cell>
                            <Table.Cell>{item.payee}</Table.Cell>
                            <Table.Cell>{item.category}</Table.Cell>
                            <Table.Cell>{item.memo}</Table.Cell>
                            <Table.Cell>{item.amount / 100}</Table.Cell>
                        </Table.Row>
                    ))}
                </Table.Body>
            </Table>
        );
    }
}

export default UnapprovedTransactions;