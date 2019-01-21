import React, {useState, useEffect} from "react";
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
import {addFoodHistory, getFoods} from "../../../../api/api";
import moment from "moment";

const ModalComponent = ({onOk, name, foodList}) => {
  const [food, setFood] = useState("");
  const [time, setTime] = useState(null);
  const [date, setDate] = useState(null);

  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen((prev) => !prev);

  console.log(time)
  return (
    <>
      <Button color="success" onClick={() => setIsOpen(true)}>Add</Button>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}>Add Record of eaten food</ModalHeader>
        <Form>
          <ModalBody>
            <FormGroup>
              <Label for="SelectMulti">Food</Label>
              <Input onChange={(e) => setFood(e.target.value)} type="select" name="selectMulti" id="SelectMulti"
                     multiple invalid={food == null || !foodList.map((food) => food.name).includes(food)}
                     valid={foodList.map((food) => food.name).includes(food)}>
                {foodList.map((food) => food.name).map((name) => <option key={name}>{name}</option>)}
              </Input>
              <FormFeedback valid>You have chosen food successfully</FormFeedback>
              <FormFeedback invalid>Oh no, please choose food :/</FormFeedback>
            </FormGroup>
            <FormGroup>
              <Label for="date">Date</Label>
              <Input onChange={(e) => setDate(e.target.value)} type="date" name="date" id="date"
                     invalid={date == null} valid={date != null}>
              </Input>
              <FormFeedback valid>You have chosen date successfully</FormFeedback>
              <FormFeedback invalid>Oh no, please choose date :/</FormFeedback>
            </FormGroup>
            <FormGroup>
              <Label for="time">Time</Label>
              <Input onChange={(e) => setTime(e.target.value)} type="time" name="time" id="time"
                     invalid={time == null} valid={time != null}>
              </Input>
              <FormFeedback valid>You have chosen time successfully</FormFeedback>
              <FormFeedback invalid>Oh no, please choose time :/</FormFeedback>
            </FormGroup>

          </ModalBody>
          <ModalFooter>
            <Button disabled={food == null || !foodList.map((food) => food.name).includes(food)}
                    color="primary" type="submit" onClick={(e) => {
              e.preventDefault();
              toggle();
              addFoodHistory({
                dateOfConsumption: moment(`${date} ${time}`, 'YYYY-MM-DD hh:mm').unix() * 1000,
                name,
                food
              }).then(() => onOk()).then(() => setDate(null) && setFood("") && setTime(null))
            }}>Add</Button>
            <Button color="secondary" type="reset" onClick={toggle}>Cancel</Button>
          </ModalFooter>
        </Form>
      </Modal>
    </>
  )

};

export default ModalComponent;
