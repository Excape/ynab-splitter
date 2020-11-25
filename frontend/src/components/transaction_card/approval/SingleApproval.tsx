import React, {useContext, useEffect} from "react";
import CategoryDropdown from "./CategoryDropdown";
import {ApprovalOption, Category, UnapprovedTransaction, ApprovalResult, ApprovalFor} from "../../../types";
import {Button, Loader} from "semantic-ui-react";
import Cookies from 'js-cookie';
import {SessionContext} from '../../../session';

type Props = {
    for: ApprovalFor,
    transaction: UnapprovedTransaction,
    presetCategory?: Category,
    onApprove: (result: ApprovalResult) => void
}

const SingleApproval = (props: Props) => {
    const [selectedCategory, setSelectedCategory] = React.useState(props.presetCategory);
    const [categoryOptions, setCategoryOptions] = React.useState([] as Category[]);
    const [approveVisible, setApproveVisible] = React.useState(false);

    const {session} = useContext(SessionContext)

    useEffect(() => {
        fetch(`/api/v1/categories/${props.for.actor}`)
            .then(result => result.json())
            .then(result => {
                    setCategoryOptions(result);
                    setApproveVisible(true);
                }
            );
    }, [props.for]);


    function onApprove() {
        setApproveVisible(false);
        const request = {
            actor: props.for.actor,
            categoryId: selectedCategory?.id,
            executingActor: session?.actor
        }

        fetch(`/api/v1/transactions/${props.transaction.id}/approveSingle`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get("XSRF-TOKEN") ?? 'NULL'
            },
            body: JSON.stringify(request)
        })
            .then(result => result.json())
            .then(result => {
                props.onApprove(result);
            })
    }


    return (
        <div>
            <span>Select category:</span>
            <CategoryDropdown defaultCategory={props.presetCategory} categoryOptions={categoryOptions}
                              onChange={setSelectedCategory}/>
            <div className="approveBtn">
            {approveVisible
                ? <Button positive content={"Approve"} disabled={selectedCategory === undefined}
                    onClick={() => onApprove()}/>
                : <Loader active inline />
            }
            </div>
        </div>
    );
};

export default SingleApproval