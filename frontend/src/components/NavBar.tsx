import React from 'react';
import {Menu} from 'semantic-ui-react';
import {NavLink} from 'react-router-dom';

const NavBar = () => {
    return (
        <Menu fluid widths={2}>
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
        </Menu>
    )
}

export default NavBar