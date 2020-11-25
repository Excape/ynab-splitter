import {Dropdown, DropdownItemProps} from "semantic-ui-react";
import React from "react";
import {Category} from "../../../types";

type Props = {
    defaultCategory?: Category
    categoryOptions: Category[]
    onChange: (category: Category) => void
}


const CategoryDropdown = (props: Props) => {

    function createOptions(): DropdownItemProps[] {
        return props.categoryOptions
            .map(({id, name}) => ({value: id, key: id, text: name}));
    }

    function onChange(value: any) {
        let category = props.categoryOptions.find((category) => category.id === value);
        if (category !== undefined) {
            props.onChange(category)
        }
    }

    return (
        <Dropdown
            placeholder={"Select Category"}
            fluid
            search
            selection
            options={createOptions()}
            defaultValue={props.defaultCategory?.id}
            onChange={(event, data) => onChange(data.value)}
        />
    );
};

export default CategoryDropdown