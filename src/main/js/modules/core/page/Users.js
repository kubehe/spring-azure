import React, {useState, useEffect} from "react";
import {Badge, Button, Card, CardBody, CardHeader, Table} from "reactstrap";
import {getUsers, removeUser} from "../../../api/api";
import UserModal from "./modal/UserModal";

const mapUsers = (users, fetchData) => (users.map(({name, roles}) =>
  <tr key={name}>
    <td>{name}</td>
    <td>{roles.map((role) => <Badge className="mr-1" pill color="primary">{role}</Badge>)}</td>
    <td><Button onClick={() => removeUser(name).then(() => fetchData())} color="danger">Delete</Button></td>
  </tr>
));

const Foods = () => {
  const [users, setUsers] = useState([]);

  const fetchData = () => getUsers().then((response) => setUsers(response.data === null ? [] : response.data));

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
            <th>User Name</th>
            <th>Roles</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          {mapUsers(users, fetchData)}
          </tbody>
        </Table>
        <UserModal onOk={fetchData} names={users.map(({name}) => name)}/>
      </CardBody>
    </Card>
  )
};

export default Foods;
