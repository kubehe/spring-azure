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
import {addFood} from "../../../../api/api";

const ModalComponent = ({onOk, names}) => {
  const [name, setName] = useState("");
  const [calories, setCalories] = useState(0);

  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen((prev) => !prev);

  return (
    <>
      <Button color="success" onClick={() => setIsOpen(true)}>Add Food</Button>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}>Add new Food type</ModalHeader>
        <Form>
          <ModalBody>
            <FormGroup>
              <Label for="name">Food Name</Label>
              <Input
                type="text"
                name="name"
                id="name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Name of the food should be unique"
                valid={!names.includes(name) && name !== ""}
                invalid={names.includes(name)}
              />
              <FormFeedback valid>Sweet! that name is available</FormFeedback>
              <FormFeedback invalid>Oh no, name is already used :/</FormFeedback>
            </FormGroup>
            <FormGroup>
              <Label for="calories">Calories</Label>
              <Input
                type="number"
                name="number"
                id="calories"
                value={calories}
                onChange={(e) => setCalories(e.target.value)}
                valid={calories > 0 && calories < 15000}
                invalid={calories > 15000}
              />
              <FormFeedback invalid>No way this food is so caloric...</FormFeedback>
            </FormGroup>

          </ModalBody>
          <ModalFooter>
            <Button disabled={isNaN(calories) || calories > 15000 || names.includes(name) || name === ""}
                    color="primary" type="submit" onClick={(e) => {
              e.preventDefault();
              toggle();
              addFood({name, calories}).then(() => onOk())
            }}>Add</Button>
            <Button color="secondary" type="reset" onClick={toggle}>Cancel</Button>
          </ModalFooter>
        </Form>
      </Modal>
    </>
  )

};

export default ModalComponent;