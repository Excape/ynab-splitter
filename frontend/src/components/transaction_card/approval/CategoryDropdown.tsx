import {Dropdown, DropdownItemProps, Icon} from "semantic-ui-react";
import React from "react";
import {Category} from "../../../types";
import MonetaryAmount from '../../MonetaryAmount';

type Props = {
    defaultCategory?: Category
    categoryOptions: Category[]
    onChange: (category: Category) => void
}

const CategoryDropdown = (props: Props) => {

    function createOptions(): DropdownItemProps[] {
        function renderItem(category: Category) {

            return (<div className="category-item">
                <div className="category-item-name">{category.name}</div>
                {category.balance !== undefined && (
                    <div>
                        <Icon name="dollar sign" />
                        <MonetaryAmount amount={category.balance}/>
                    </div>
                )}
            </div>);
        }

        return props.categoryOptions
            .map((category) => ({
                    value: category.id,
                    key: category.id,
                    text: category.name,
                    content: renderItem(category)
                }
            ));
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