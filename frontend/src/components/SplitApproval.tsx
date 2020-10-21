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
import {Button, Loader} from "semantic-ui-react";
import CustomSplitApproval from './CustomSplitApproval';

type Props = {
    transaction: UnapprovedTransaction,
    onApprove: (result: ApprovalResult) => void,
    presetCategoryRobin?: Category
    presetCategorySophie?: Category
}

const SplitApproval = (props: Props) => {
    const [selectedCategoryRobin, setSelectedCategoryRobin] = React.useState(props.presetCategoryRobin);
    const [categoryOptionsRobin, setCategoryOptionsRobin] = React.useState([] as Category[]);
    const [selectedCategorySophie, setSelectedCategorySophie] = React.useState(props.presetCategorySophie);
    const [categoryOptionsSophie, setCategoryOptionsSophie] = React.useState([] as Category[]);
    const [approveVisible, setApproveVisible] = React.useState(false);
    const [customSplitVisible, setCustomSplitVisible] = React.useState(false);


    useEffect(() => {
        let promiseSophie = fetchCategories(ApprovalOption.Sophie)
            .then(result => setCategoryOptionsSophie(result));

        let promiseRobin = fetchCategories(ApprovalOption.Robin)
            .then(result => setCategoryOptionsRobin(result));

        Promise.all([promiseRobin, promiseSophie])
            .then(() => setApproveVisible(true));

        function fetchCategories(actor: ApprovalOption) {
            return fetch(`/api/v1/categories/${upperCase(actor)}`)
                .then(result => result.json());

        }
    }, []);


    function upperCase(option: ApprovalOption) {
        return option.toString().toUpperCase()
    }

    function approveSplit(split: SplitRequest[]) {
        setApproveVisible(false)
        const request: SplitTransactionRequest = {categories: createCategoryRequest(), split}
        fetch(`/api/v1/transactions/${props.transaction.id}/approveSplit`, {
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

    function approveCustomSplit(splitRobin: number) {
        checkCategoriesSelected();
        const split: SplitRequest[] = [
            {actor: "ROBIN", split: splitRobin},
            {actor: "SOPHIE", split: 1 - splitRobin}
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
            <div className="categorySelect">
                <span>Select category for Robin:</span>
                <CategoryDropdown defaultCategory={props.presetCategoryRobin} categoryOptions={categoryOptionsRobin}
                                  onChange={setSelectedCategoryRobin}/>
            </div>

            <div className="categorySelect">
            <span>Select category for Sophie:</span>
            <CategoryDropdown defaultCategory={props.presetCategorySophie} categoryOptions={categoryOptionsSophie}
                              onChange={setSelectedCategorySophie}/>
            </div>

            <div className="approveBtn">
                { approveVisible
                ? (
                    <div>
                    <Button basic color={"blue"}
                            content={"Split 50/50"}
                            disabled={selectedCategoryRobin === undefined || selectedCategorySophie === undefined}
                            onClick={() => approveEvenSplit()}/>
                    <Button basic color={"grey"}
                            disabled={customSplitVisible}
                            content={"Custom split"}
                            onClick={() => setCustomSplitVisible(true)}
                    />
                    </div>
                    )
                : <Loader active inline />
                }
            </div>
            <div>
                {customSplitVisible && (
                    <CustomSplitApproval amount={props.transaction.amount} onApprove={approveCustomSplit}/>
                )}
            </div>
        </div>
    );
};

export default SplitApproval