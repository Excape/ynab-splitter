import React, {useEffect} from "react";
import CategoryDropdown from "./CategoryDropdown";
import {
    Actor,
    ApprovalOption,
    ApprovalResult,
    Category,
    CategoryRequest,
    SplitRequest,
    SplitTransactionRequest,
    UnapprovedTransaction
} from "../../../types";
import {Button, Loader} from "semantic-ui-react";
import CustomSplitApproval from './CustomSplitApproval';
import Cookies from 'js-cookie';
import {SessionContext} from '../../../session';

type Props = {
    transaction: UnapprovedTransaction,
    onApprove: (result: ApprovalResult) => void,
    presetCategories: Map<string, Category | undefined>
}

const SplitApproval = (props: Props) => {
    const [categoryOptions, setCategoryOptions] = React.useState(new Map<string, Category[]>())
    const [selectedCategories, setSelectedCategories] = React.useState(initSelectedCategories())
    const [approveVisible, setApproveVisible] = React.useState(false);
    const [customSplitVisible, setCustomSplitVisible] = React.useState(false);
    const session = React.useContext(SessionContext);

    function initSelectedCategories(): Map<string, Category> {
        const selectedCategories = new Map<string, Category>();
        [...props.presetCategories.keys()].forEach(actor => {
            const presetCategory = props.presetCategories.get(actor);
            if (presetCategory !== undefined) {
                selectedCategories.set(actor, presetCategory)
            }
        })
        return selectedCategories
    }


    useEffect(() => {
        const categoryPromises = props.transaction.actors.map((actor) =>
            fetchCategories(actor).then(result => setCategoryOptionsForActor(actor, result))
        )

        Promise.all(categoryPromises)
            .then(() => setApproveVisible(true));

        function fetchCategories(actor: string) {
            return fetch(`/api/v1/categories/${actor}`)
                .then(result => result.json());

        }

        function setCategoryOptionsForActor(actor: string, categories: Category[]) {
            setCategoryOptions(previous => new Map(previous.set(actor, categories)))
        }

    }, [props.transaction]);




    function upperCase(option: ApprovalOption) {
        return option.toString().toUpperCase()
    }

    function approveSplit(split: SplitRequest[]) {
        setApproveVisible(false)
        const request: SplitTransactionRequest = {
            executingActor: session.session!.actor,
            categories: createCategoryRequest(),
            split}
        fetch(`/api/v1/transactions/${props.transaction.id}/approveSplit`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get("XSRF-TOKEN") ?? 'NULL'
            },
            body: JSON.stringify(request)
        })
            .then(result => result.json())
            .then(result => props.onApprove(result))
    }

    function allCategoriesSelected() {
        return [...selectedCategories.keys()].length === props.transaction.actors.length;
    }

    function checkCategoriesSelected() {
        if (!allCategoriesSelected()) {
            throw new Error("Not all categories are set")
        }
    }

    function approveEvenSplit() {
        checkCategoriesSelected();
        const split: SplitRequest[] = props.transaction.actors.map(actor => (
            {
                actor: actor,
                split: 1.0 / props.transaction.actors.length
            }
        ))
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
        return [...selectedCategories.keys()].map((actor) => ({
            actor, categoryId: selectedCategories.get(actor)!.id
        }))
    }

    function shouldDisableApproveButtons() {
        return !allCategoriesSelected()
    }


    function setSelectedCategoryOption(actor: string, category: Category) {
        setSelectedCategories((previous) => new Map(previous.set(actor, category)))
    }

    return (
        <div>
            {props.transaction.actors.map((actor, i) => (
                <div className="categorySelect" key={i}>
                    <span>Select category for {actor}:</span>
                    <CategoryDropdown defaultCategory={props.presetCategories.get(actor)}
                                      categoryOptions={categoryOptions.get(actor) ?? []}
                                      onChange={category => setSelectedCategoryOption(actor, category)}/>
                </div>
            ))}

            <div className="approveBtn">
                { approveVisible
                ? (
                    <div>
                    <Button basic color={"blue"}
                            content={"Split 50/50"}
                            disabled={shouldDisableApproveButtons()}
                            onClick={() => approveEvenSplit()}/>
                    <Button basic color={"grey"}
                            disabled={shouldDisableApproveButtons()}
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
                    <CustomSplitApproval amountPositive={Math.abs(props.transaction.amount)} onApprove={approveCustomSplit}/>
                )}
            </div>
        </div>
    );
};

export default SplitApproval