import React, {useEffect} from "react";
import CategoryDropdown from "./CategoryDropdown";
import {ApprovalOption, ApprovalResult, Category, UnapprovedTransaction} from "../types";
import {Button} from "semantic-ui-react";

type Props = {
    transaction: UnapprovedTransaction,
    onApprove: (result: ApprovalResult) => void,
    presetCategoryRobin?: Category
    presetCategorySophie?: Category
}

const SplitApproval = (props: Props) => {
    const [selectedCategoryRobin, setSelectedCategoryRobin] = React.useState();
    const [categoryOptionsRobin, setCategoryOptionsRobin] = React.useState([] as Category[]);
    const [selectedCategorySophie, setSelectedCategorySophie] = React.useState();
    const [categoryOptionsSophie, setCategoryOptionsSophie] = React.useState([] as Category[]);


    useEffect(() => {
        fetchCategories(ApprovalOption.Sophie)
            .then(result => setCategoryOptionsSophie(result));

        fetchCategories(ApprovalOption.Robin)
            .then(result => setCategoryOptionsRobin(result));
    }, []);

    function fetchCategories(actor: ApprovalOption) {
        return fetch(`/api/v1/categories/${upperCase(actor)}`)
            .then(result => result.json());

    }

    function getCategory(actor: string): Category | undefined {
        for (const entry of props.transaction.categoryMap) {
            if (entry.actor === actor) {
                return entry.category;
            }
        }
        return undefined;
    }

    function upperCase(option: ApprovalOption) {
        return option.toString().toUpperCase()
    }

    // function onApprove() {
    //     // TODO set "from" correctly
    //     fetch(`/api/v1/transactions/${props.transaction.id}/approveSingle?from=ROBIN&for=${upperCase(props.for)}&categoryId=${selectedCategoryRobin?.id}`)
    //         .then(result => result.json())
    //         .then(result => props.onApprove(result))
    // }

    return (
        <div>
            <span>Select category for Robin:</span>
            <CategoryDropdown defaultCategory={props.presetCategoryRobin} categoryOptions={categoryOptionsRobin}
                              onChange={setSelectedCategoryRobin}/>
            <span>Select category for Sophie:</span>
            <CategoryDropdown defaultCategory={props.presetCategorySophie} categoryOptions={categoryOptionsSophie}
                              onChange={setSelectedCategorySophie}/>
            <div>
                <Button basic color={"blue"} content={"Split 50/50"}/>
                <Button basic color={"grey"} disabled content={"Custom split"}/>
            </div>
        </div>
    );
};

export default SplitApproval