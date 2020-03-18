import React from "react";
import CategoryDropdown from "./CategoryDropdown";
import {ApprovalOption} from "../types";
import {Button} from "semantic-ui-react";

type Props = {
    for: ApprovalOption
    presetCategory?: string
}

const SingleApproval = (props: Props) => {
    const [selectedCategory, setSelectedCategory] = React.useState(props.presetCategory);

    return (
        <div>
            <span>Select category:</span>
            <CategoryDropdown defaultCategory={props.presetCategory} onChange={setSelectedCategory}/>
            <Button positive content={"Approve"}/>
        </div>
    );
};

export default SingleApproval