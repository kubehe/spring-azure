import React from "react";
import {Link} from "react-router-dom";
import {ListGroup, ListGroupItem} from "reactstrap";


const LeftBar = () => {

  return (
    <div className="col-lg-3">
      <h1 className="my-4">Calories Counter</h1>
      <ListGroup>
        <ListGroupItem><Link to="/foods">Foods</Link></ListGroupItem>
        <ListGroupItem><Link to="/users">Users</Link></ListGroupItem>
        <ListGroupItem><Link to="/your-food-history">Your Food
          History</Link></ListGroupItem>
        <ListGroupItem><Link to="/others-food-history">Others Food
          History</Link></ListGroupItem>
      </ListGroup>
    </div>
  );
};

export default LeftBar;