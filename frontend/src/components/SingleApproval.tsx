import React, {useEffect} from "react";
import CategoryDropdown from "./CategoryDropdown";
import {ApprovalOption, Category, UnapprovedTransaction, ApprovalResult} from "../types";
import {Button} from "semantic-ui-react";

type Props = {
    for: ApprovalOption,
    transaction: UnapprovedTransaction,
    presetCategory?: Category,
    onApprove: (result: ApprovalResult) => void
}

const SingleApproval = (props: Props) => {
    const [selectedCategory, setSelectedCategory] = React.useState(props.presetCategory);
    const [categoryOptions, setCategoryOptions] = React.useState([] as Category[]);

    useEffect(() => {
        fetch(`/api/v1/categories/${upperCase(props.for)}`)
            .then(result => result.json())
            .then(result => {
                    setCategoryOptions(result)
                }
            );
    }, [props.for]);

    function upperCase(option: ApprovalOption) {
        return option.toString().toUpperCase()
    }

    function onApprove() {
        fetch(`/api/v1/transactions/${props.transaction.id}/approveSingle?for=${upperCase(props.for)}&categoryId=${selectedCategory?.id}`)
            .then(result => result.json())
            .then(result => props.onApprove(result))
    }

    return (
        <div>
            <span>Select category:</span>
            <CategoryDropdown defaultCategory={props.presetCategory} categoryOptions={categoryOptions}
                              onChange={setSelectedCategory}/>
            <Button positive content={"Approve"} onClick={() => onApprove()}/>
        </div>
    );
};

export default SingleApproval