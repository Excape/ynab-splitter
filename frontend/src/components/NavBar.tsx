import React from 'react';
import {Icon, Menu} from 'semantic-ui-react';
import {NavLink} from 'react-router-dom';

const NavBar = () => {
    return (
        <Menu fluid widths={3}>
            <Menu.Item
                name={"transactions"}
                exact // needed for active element of router to work
                as={NavLink}
                to={"/"}>
                Unapproved Transactions
            </Menu.Item>
            <Menu.Item
                name={"auditlog"}
                exact
                as={NavLink}
                to={"/auditlog"}>
                Audit Log
            </Menu.Item>
            <Menu.Item
                name={"settings"}
                exact
                as={NavLink}
                to={"/settings"}>
                <Icon name="cog" />
            </Menu.Item>
        </Menu>
    )
}

export default NavBar