import React, {useState, useEffect} from "react";
import {Button, Card, CardBody, CardHeader, Table} from "reactstrap";
import {getFoods, removeFood} from "../../../api/api";
import FoodModal from "./modal/FoodModal";

const mapFoods = (foods, fetchData) => (foods.map(({name, calories}) =>
  <tr key={name}>
    <td>{name}</td>
    <td>{calories} cal</td>
    <td><Button onClick={() => removeFood(name).then(() => fetchData())} color="danger">Delete</Button></td>
  </tr>
));

const Foods = () => {
  const [foods, setFoods] = useState([]);

  const fetchData = () => getFoods().then((response) => setFoods(response.data === null ? [] : response.data));

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <Card className="my-4">
      <CardHeader>Foods</CardHeader>
      <CardBody>
        <Table>
          <thead>
          <tr>
            <th>Food Name</th>
            <th>Calories</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          {mapFoods(foods, fetchData)}
          </tbody>
        </Table>
        <FoodModal onOk={fetchData} names={foods.map(({name}) => name)}/>
      </CardBody>
    </Card>
  )
};

export default Foods;