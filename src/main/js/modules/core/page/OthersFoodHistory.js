import React, {useState, useEffect} from "react";
import moment from "moment";
import {Button, Card, CardBody, CardHeader, Table} from "reactstrap";
import {getUsers, removeFoodHistory} from "../../../api/api";

const mapFoods = (userFoodHistory) => userFoodHistory.map(({food, calories, dateOfConsumption}) =>
  <tr key={dateOfConsumption}>
    <td>{food}</td>
    <td>{calories} cal</td>
    <td>{dateOfConsumption && moment(dateOfConsumption.split("T").join(" ").split(".")[0], "YYYY-MM-DD hh:mm:ss").fromNow()}</td>
  </tr>);


const mapUserData = (userData) =>
  userData.map(({name, userFoodHistory}) =>
    <Card key={name} className="my-4">
      <CardHeader>User: {name}</CardHeader>
      <CardBody>
        <Table>
          <thead>
          <tr>
            <th>Food Name</th>
            <th>Calories</th>
            <th>Date of consumption</th>
          </tr>
          </thead>
          <tbody>
          {mapFoods(userFoodHistory)}
          </tbody>
        </Table>
      </CardBody>
    </Card>
  );

const YourFoodHistory = () => {
  const [userData, setUserData] = useState([]);

  const fetchData = () => getUsers().then((response) => setUserData(response.data === null ? [] : response.data));

  useEffect(() => {
    fetchData();
  }, []);

  console.log(userData)
  return (
    <>{mapUserData(userData)}</>
  )
};

export default YourFoodHistory;
