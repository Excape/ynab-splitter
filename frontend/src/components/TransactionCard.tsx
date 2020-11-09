import {ApprovalOption, ApprovalResult, Category, UnapprovedTransaction} from "../types";
import {Button, Card, Icon} from "semantic-ui-react";
import React from "react";
import SingleApproval from "./SingleApproval";
import SplitApproval from "./SplitApproval";

type Props = {
    transaction: UnapprovedTransaction
}

const TransactionCard = ({transaction}: Props) => {
    const [approvalOpen, setApprovalOpen] = React.useState(false);
    const [approvalFor, setApprovalFor] = React.useState(ApprovalOption.Undefined);
    const [approvalResult, setApprovalResult] = React.useState<ApprovalResult | null>(null);


    function handleApprove(approveFor: ApprovalOption) {
        setApprovalOpen(true);
        setApprovalFor(approveFor);
    }

    function getCategory(actor: ApprovalOption): Category | undefined {
        for (const entry of transaction.categoryMap) {
            if (entry.actor === actor) {
                return entry.category;
            }
        }
        return undefined;
    }

    function renderCategory(actor: ApprovalOption): string {
        return getCategory(actor)?.name ?? "?";
    }

    function onApprove(result: ApprovalResult) {
        setApprovalResult(result);
        setApprovalOpen(false);
    }

    function approveOpenFor(option: ApprovalOption) {
        return approvalFor === option;
    }

    return (
        <Card fluid>
            <Card.Content>
                <Card.Header>{transaction.payee}</Card.Header>
                <Card.Meta>{transaction.date}</Card.Meta>
                <Card.Description>
                    <Icon name="folder"/>
                    {renderCategory(ApprovalOption.Robin)} / {renderCategory(ApprovalOption.Sophie)}
                </Card.Description>
                <Card.Description>
                    <Icon name="dollar sign"/>
                    {transaction.amount / 1000}
                </Card.Description>
            </Card.Content>
            {!approvalResult?.success && (
            <Card.Content extra fluid={"true"}>
                <div className='approveButtons'>
                    <Button fluid basic={!approveOpenFor(ApprovalOption.Robin)} color="orange" content={"Robin"} onClick={() => handleApprove(ApprovalOption.Robin)}/>
                    <Button fluid basic={!approveOpenFor(ApprovalOption.Sophie)} color="teal" content={"Sophie"} onClick={() => handleApprove(ApprovalOption.Sophie)}/>
                    <Button fluid basic={!approveOpenFor(ApprovalOption.Split)} color="grey" content={"Split"} onClick={() => handleApprove(ApprovalOption.Split)}/>
                </div>
            </Card.Content>)}
            {approvalOpen && (
                <Card.Content extra fluid={"true"}>
                    {approvalFor === ApprovalOption.Split ? (
                        <SplitApproval transaction={transaction} onApprove={onApprove} presetCategoryRobin={getCategory(ApprovalOption.Robin)}
                            presetCategorySophie={getCategory(ApprovalOption.Sophie)}/>
                    ) : (
                        <SingleApproval for={approvalFor} transaction={transaction}
                                        presetCategory={getCategory(approvalFor)} onApprove={onApprove}/>
                    )}
                </Card.Content>
            )}
            {approvalResult != null && (
                <Card.Content extra fluid="true">
                    <span>{approvalResult.success ? "Transaction successfully saved!" : "Transaction could not be saved"}</span>
                </Card.Content>
            )}
        </Card>
    );
};

export default TransactionCard