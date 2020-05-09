import React, {useEffect} from "react";
import CategoryDropdown from "./CategoryDropdown";
import {
    ApprovalOption,
    ApprovalResult,
    Category,
    CategoryRequest,
    SplitRequest,
    SplitTransactionRequest,
    UnapprovedTransaction
} from "../types";
import {Button} from "semantic-ui-react";

type Props = {
    transaction: UnapprovedTransaction,
    onApprove: (result: ApprovalResult) => void,
    presetCategoryRobin?: Category
    presetCategorySophie?: Category
}

const SplitApproval = (props: Props) => {
    const [selectedCategoryRobin, setSelectedCategoryRobin] = React.useState<null | Category>();
    const [categoryOptionsRobin, setCategoryOptionsRobin] = React.useState([] as Category[]);
    const [selectedCategorySophie, setSelectedCategorySophie] = React.useState<null | Category>();
    const [categoryOptionsSophie, setCategoryOptionsSophie] = React.useState([] as Category[]);


    useEffect(() => {
        fetchCategories(ApprovalOption.Sophie)
            .then(result => setCategoryOptionsSophie(result));

        fetchCategories(ApprovalOption.Robin)
            .then(result => setCategoryOptionsRobin(result));

        function fetchCategories(actor: ApprovalOption) {
            return fetch(`/api/v1/categories/${upperCase(actor)}`)
                .then(result => result.json());

        }
    }, []);


    function upperCase(option: ApprovalOption) {
        return option.toString().toUpperCase()
    }

    // function onApprove() {
    //     // TODO set "from" correctly
    //     fetch(`/api/v1/transactions/${props.transaction.id}/approveSingle?from=ROBIN&for=${upperCase(props.for)}&categoryId=${selectedCategoryRobin?.id}`)
    //         .then(result => result.json())
    //         .then(result => props.onApprove(result))
    // }

    function approveSplit(split: SplitRequest[]) {
        const request: SplitTransactionRequest = {categories: createCategoryRequest(), split}
        // TODO set "from" correctly
        fetch(`/api/v1/transactions/${props.transaction.id}/approveSplit?from=ROBIN`, {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(request)
        })
            .then(result => result.json())
            .then(result => props.onApprove(result))
    }

    function checkCategoriesSelected() {
        if (selectedCategorySophie == null) {
            alert("Set category for Sophie!")
        } else if (selectedCategoryRobin === null) {
            alert("Set category for Robin!")
        } else return;
        throw new Error("category not set")
    }

    function approveEvenSplit() {
        checkCategoriesSelected();
        const split: SplitRequest[] = [
            {actor: "ROBIN", split: 0.5},
            {actor: "SOPHIE", split: 0.5}
        ];
        approveSplit(split)
    }

    function createCategoryRequest(): CategoryRequest[] {
        return [
            {actor: "ROBIN", categoryId: selectedCategoryRobin!.id},
            {actor: "SOPHIE", categoryId: selectedCategorySophie!.id}
        ]
    }

    return (
        <div>
            <span>Select category for Robin:</span>
            <CategoryDropdown defaultCategory={props.presetCategoryRobin} categoryOptions={categoryOptionsRobin}
                              onChange={setSelectedCategoryRobin}/>
            <span>Select category for Sophie:</span>
            <CategoryDropdown defaultCategory={props.presetCategorySophie} categoryOptions={categoryOptionsSophie}
                              onChange={setSelectedCategorySophie}/>
            <div>
                <Button basic color={"blue"}
                        content={"Split 50/50"}
                        disabled={selectedCategoryRobin === undefined || selectedCategorySophie === undefined}
                        onClick={() => approveEvenSplit()}/>
                <Button basic color={"grey"} disabled content={"Custom split"}/>
            </div>
        </div>
    );
};

export default SplitApproval