import {
    ApprovalFor,
    ApprovalResult,
    Category,
    UnapprovedTransaction,
    UndoApprovalResult
} from "../../types";
import {Card, Icon, Table} from "semantic-ui-react";
import React, {useState, Fragment} from "react";
import SingleApproval from "./approval/SingleApproval";
import SplitApproval from "./approval/SplitApproval";
import ApprovalButtons from './ApprovalButtons';
import MonetaryAmount from '../MonetaryAmount';
import UndoApproval from '../UndoApproval';

type Props = {
    transaction: UnapprovedTransaction
}

function getCategory(transaction: UnapprovedTransaction, actor: string): Category | undefined {
    for (const entry of transaction.categoryMap) {
        if (entry.actor === actor) {
            return entry.category;
        }
    }
    return undefined;
}

const TransactionCard = ({transaction}: Props) => {
    const [approvalOpen, setApprovalOpen] = React.useState(false);
    const [approvalFor, setApprovalFor] = React.useState<ApprovalFor>({actor: undefined, splitApproval: false});
    const [approvalResult, setApprovalResult] = React.useState<ApprovalResult | null>(null);
    const [undoApprovalResult, setUndoApprovalResult] = useState<UndoApprovalResult>()


    function handleSelectApproval(approveFor: ApprovalFor) {
        setApprovalOpen(true);
        setApprovalFor(approveFor);
    }

    function onApprove(result: ApprovalResult) {
        setApprovalResult(result);
        setApprovalOpen(false);
        setUndoApprovalResult(undefined)
    }

    function getPresetCategories(): Map<string, Category | undefined> {
        const presetCategories = new Map<string, Category | undefined>()
        transaction.actors.forEach((actor) =>
            presetCategories.set(actor, getCategory(transaction, actor)))
        return presetCategories;
    }

    function onUndoApproval(result: UndoApprovalResult) {
        setUndoApprovalResult(result)
        if (result.success) {
            setApprovalOpen(false)
            setApprovalResult(null)
        }
    }

    return (
        <Card fluid>
            <Card.Content>
                <Card.Header>{transaction.payee}</Card.Header>
                <Card.Meta>{transaction.date}</Card.Meta>
                <Card.Description>
                    <Icon name="dollar sign"/>
                    <strong><MonetaryAmount amount={transaction.amount}/></strong>
                </Card.Description>

                <CategoryMatrix transaction={transaction} />

            </Card.Content>
            {!approvalResult?.success && (
                <Card.Content extra fluid={"true"}>
                    <ApprovalButtons actors={transaction.actors}
                                     onSelect={(approvalOption => handleSelectApproval(approvalOption))}/>
                </Card.Content>)}
            {approvalOpen && (
                <Card.Content extra fluid={"true"}>
                    {approvalFor?.splitApproval ? (
                        <SplitApproval transaction={transaction} onApprove={onApprove}
                                       presetCategories={getPresetCategories()}/>
                    ) : (
                        <SingleApproval for={approvalFor} transaction={transaction}
                                        presetCategory={getCategory(transaction, approvalFor.actor!)}
                                        onApprove={onApprove}/>
                    )}
                </Card.Content>
            )}
            {approvalResult != null && (
                <Fragment>
                    <Card.Content extra fluid="true">
                        <span>{approvalResult.success ? "Transaction successfully saved!" : "Transaction could not be saved"}</span>
                    </Card.Content>
                    <Card.Content extra fluid="true">
                        <UndoApproval auditLogId={approvalResult.auditLogId!} onUndoApproval={onUndoApproval}/>
                    </Card.Content>
                </Fragment>
            )}
            {undoApprovalResult !== undefined && (
                <Card.Content extra fluid="true">
                    <span>{undoApprovalResult.success ? "Transaction has been restored!" : "Transaction could not be restored"}</span>
                </Card.Content>
            )}
        </Card>
    );
};

export default TransactionCard

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