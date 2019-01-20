import React, {useState} from 'react';
import {
  Collapse,
  Container, DropdownItem, DropdownMenu, DropdownToggle,
  Nav,
  Navbar,
  NavbarBrand,
  NavbarToggler,
  NavItem,
  NavLink,
  UncontrolledDropdown
} from "reactstrap";

const NavbarComponent = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <Navbar color="dark" dark expand="xl" fixed="top">
      <NavbarBrand href="https://github.com/kubehe">@kubehe</NavbarBrand>
      <NavbarToggler onClick={() => setIsOpen((prev) => !prev)}/>
      <Collapse isOpen={isOpen} navbar>
        <Nav className="ml-auto" navbar>
          <NavItem>
            <NavLink href="/logout">Logout</NavLink>
          </NavItem>
          <UncontrolledDropdown nav inNavbar>
            <DropdownToggle nav caret>
              Options
            </DropdownToggle>
            <DropdownMenu right>
              <DropdownItem>
                <a href="/swagger-ui.html">Swagger Api</a>
              </DropdownItem>
              <DropdownItem>
                Option 2
              </DropdownItem>
              <DropdownItem divider/>
              <DropdownItem>
                Reset
              </DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Nav>
      </Collapse>
    </Navbar>
  )
};

export default NavbarComponent;