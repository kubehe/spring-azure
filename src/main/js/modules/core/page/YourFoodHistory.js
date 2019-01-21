import React, {useState, useEffect} from "react";
import moment from "moment";
import {Button, Card, CardBody, CardHeader, Table} from "reactstrap";
import {getCurrentUserName, getFoods, getUser, removeFoodHistory} from "../../../api/api";
import FoodHistoryModal from "./modal/FoodHistoryModal";

const mapUserData = (userData, fetchData, userName) => (userData.map(({food, calories, dateOfConsumption}) =>
  <tr key={dateOfConsumption}>
    <td>{food}</td>
    <td>{calories} cal</td>
    <td>{moment(dateOfConsumption.split("T").join(" ").split(".")[0], "YYYY-MM-DD hh:mm:ss").fromNow()}</td>
    <td><Button onClick={() => removeFoodHistory({food, name: userName, dateOfConsumption}).then(() => fetchData())}
                color="danger">Delete</Button></td>
  </tr>
));

const YourFoodHistory = () => {
  const [userData, setUserData] = useState([]);
  const [userName, setUserName] = useState("");

  const fetchData = () => getCurrentUserName().then((res) => {
    setUserName(res.data.user);
    getUser(res.data.user).then((response) => setUserData(response.data.userFoodHistory === null ? [] : response.data.userFoodHistory))
  });

  const [foodList, setFoodList] = useState([]);
  useEffect(() => {
    getFoods().then((res) => setFoodList(res.data));
  }, []);


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
            <th>Date of consumption</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          {mapUserData(userData, fetchData, userName)}
          </tbody>
        </Table>
        <FoodHistoryModal foodList={foodList} onOk={fetchData} name={userName}/>
      </CardBody>
    </Card>
  )
};

export default YourFoodHistory;
