import React, {useEffect} from "react";
import CategoryDropdown from "./CategoryDropdown";
import {ApprovalOption, Category, UnapprovedTransaction, ApprovalResult} from "../types";
import {Button, Loader} from "semantic-ui-react";

type Props = {
    for: ApprovalOption,
    transaction: UnapprovedTransaction,
    presetCategory?: Category,
    onApprove: (result: ApprovalResult) => void
}

const SingleApproval = (props: Props) => {
    const [selectedCategory, setSelectedCategory] = React.useState(props.presetCategory);
    const [categoryOptions, setCategoryOptions] = React.useState([] as Category[]);
    const [approveVisible, setApproveVisible] = React.useState(false);

    useEffect(() => {
        fetch(`/api/v1/categories/${upperCase(props.for)}`)
            .then(result => result.json())
            .then(result => {
                    setCategoryOptions(result);
                    setApproveVisible(true);
                }
            );
    }, [props.for]);

    function upperCase(option: ApprovalOption) {
        return option.toString().toUpperCase()
    }

    function onApprove() {
        setApproveVisible(false);
        fetch(`/api/v1/transactions/${props.transaction.id}/approveSingle?for=${upperCase(props.for)}&categoryId=${selectedCategory?.id}`)
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

            {approveVisible
                ? <Button positive content={"Approve"} onClick={() => onApprove()}/>
                : <Loader active inline />
            }
        </div>
    );
};

export default SingleApproval