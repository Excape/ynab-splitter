import {ApprovalOption, UnapprovedTransaction} from "../types";
import {Button, Card, Icon} from "semantic-ui-react";
import React from "react";
import SingleApproval from "./SingleApproval";

type Props = {
    transaction: UnapprovedTransaction
}

const TransactionCard = ({ transaction }: Props) => {
    const [approvalOpen, setApprovalOpen] = React.useState(false);
    const [approvalFor, setApprovalFor] = React.useState(ApprovalOption.Undefined);

    function handleApproveRobin() {
        setApprovalOpen(true);
        setApprovalFor(ApprovalOption.Robin)
    }

    function getCategory(actor: string) : string | undefined {
        for (const entry of transaction.categoryMap) {
            if (entry.actor === actor) {
                return entry.category;
            }
        }
        return undefined;
    }

    function renderCategory(actor: string) : string {
        return getCategory(actor) ?? "?";
    }

    return (
        <Card>
            <Card.Content>
                <Card.Header>{transaction.payee}</Card.Header>
                <Card.Meta>{transaction.date}</Card.Meta>
                <Card.Description>
                    <Icon name="folder"/>
                    {renderCategory("Robin")} / {renderCategory("Sophie")}
                </Card.Description>
                <Card.Description>
                    <Icon name="dollar sign"/>
                    {transaction.amount / 100}
                </Card.Description>
            </Card.Content>
            <Card.Content extra fluid>
                <div className='approveButtons'>
                    <Button basic color="orange" content={"Robin"} onClick={handleApproveRobin}/>
                    <Button basic color="teal" content={"Sophie"}/>
                    <Button basic color="grey" content={"Split"}/>
                </div>
            </Card.Content>
            {approvalOpen && (
                <Card.Content extra fluid>
                    <SingleApproval for={approvalFor} presetCategory={getCategory(approvalFor.toString())}/>
                </Card.Content>
            )}
        </Card>
    );
};

export default TransactionCard