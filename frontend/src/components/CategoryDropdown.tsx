import {Dropdown} from "semantic-ui-react";
import React from "react";

type Props = {
    defaultCategory?: string
    onChange: (value: any) => void
}

const CategoryDropdown = (props: Props) => {
    return (
        <Dropdown
            placeholder={"Select Category"}
            fluid
            search
            selection
            defaultValue={props.defaultCategory}
            onChange={(event, data) => props.onChange(data.value)}
        />
    );
};

export default CategoryDropdown