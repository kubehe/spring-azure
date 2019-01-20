import React, {useState} from "react";
import {
  Button,
  Form,
  FormFeedback,
  FormGroup,
  Input,
  Label,
  Modal,
  ModalBody,
  ModalFooter,
  ModalHeader
} from "reactstrap";
import {addUser} from "../../../../api/api";

const ModalComponent = ({onOk, names}) => {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [roles, setRoles] = useState([]);

  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen((prev) => !prev);

  return (
    <>
      <Button color="success" onClick={() => setIsOpen(true)}>Add User</Button>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}>Add new User</ModalHeader>
        <Form>
          <ModalBody>
            <FormGroup>
              <Label for="name">User Name</Label>
              <Input
                type="text"
                name="name"
                id="name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="User name should be unique"
                valid={!names.includes(name) && name !== ""}
                invalid={names.includes(name)}
              />
              <FormFeedback valid>Sweet! that name is available</FormFeedback>
              <FormFeedback invalid>Oh no, name is already used :/</FormFeedback>
            </FormGroup>
            <FormGroup>
              <Label for="password">Password</Label>
              <Input value={password} onChange={(e) => setPassword(e.target.value)} type="password" name="password"
                     id="password" placeholder="Password should be at least 6 characters long"
                     invalid={password.length < 6} valid={password.length > 6}/>
              <FormFeedback valid>Sweet! Password is safe enough for our super secret database</FormFeedback>
              <FormFeedback invalid>Oh no, password is too weak :/</FormFeedback>
            </FormGroup>
            <FormGroup>
              <Label for="SelectMulti">Select Roles</Label>
              <Input onChange={(e) => setRoles([e.target.value])} type="select" name="selectMulti" id="SelectMulti"
                     multiple invalid={roles.length < 1} valid={roles.length > 1}>
                <option>ADMIN</option>
                <option>REGULAR</option>
              </Input>
              <FormFeedback valid>You have chosen role successfully</FormFeedback>
              <FormFeedback invalid>Oh no, please choose role :/</FormFeedback>
            </FormGroup>
          </ModalBody>
          <ModalFooter>
            <Button
              disabled={name === null || name === undefined || names.includes(name) || name === "" || password === null || password === undefined || password.length < 6 || roles.length < 1}
              color="primary" type="submit" onClick={(e) => {
              e.preventDefault();
              toggle();
              addUser({name, password, roles}).then(() => onOk())
            }}>Add</Button>
            <Button color="secondary" type="reset" onClick={toggle}>Cancel</Button>
          </ModalFooter>
        </Form>
      </Modal>
    </>
  )

};

export default ModalComponent;
