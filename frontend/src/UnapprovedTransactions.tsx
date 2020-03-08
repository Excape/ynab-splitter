import React from 'react';
import {Button, Card, Container, Icon, Table} from "semantic-ui-react";

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
    category: any,
    memo: string,
    payee: string;
}
//
// type Category = {
//     Robin?: string,
//     Sophie?: string
// }

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

            <Card.Group>
                {items!.map(item => (
                    <Card>
                        <Card.Content>
                            <Card.Header>{item.payee}</Card.Header>
                            <Card.Meta>{item.date}</Card.Meta>
                                <Card.Description>
                                    <Icon name="folder" />
                                    {"Robin" in item.category ? item.category.Robin : "?"} / {"Sophie" in item.category ? item.category.Sophie : "?"}
                                </Card.Description>
                            <Card.Description>
                                <Icon name="dollar sign" />
                                {item.amount / 100}
                            </Card.Description>
                        </Card.Content>
                        <Card.Content extra fluid>
                            <div className='approveButtons'>
                            <Button basic color="teal" content={"Robin"}/>
                            <Button basic color="orange" content={"Sophie"}/>
                            <Button basic color="grey" content={"Split"}/>
                            </div>
                        </Card.Content>
                    </Card>
                ))}
            </Card.Group>

        );
    }
}

export default UnapprovedTransactions;