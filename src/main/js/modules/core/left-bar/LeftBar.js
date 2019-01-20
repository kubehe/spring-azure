import React from "react";
import {Link, withRouter} from "react-router-dom";
import {ListGroup, ListGroupItem} from "reactstrap";


const LeftBar = ({history, location}) => {

  return (
    <div className="col-lg-3">
      <h1 className="my-4">Calories Counter</h1>
      <ListGroup>
        <ListGroupItem active={"/foods" === location.pathname}><Link to="/foods">Foods</Link></ListGroupItem>
        <ListGroupItem active={"/users" === location.pathname}><Link to="/users">Users</Link></ListGroupItem>
        <ListGroupItem active={"/your-food-history" === location.pathname}><Link to="/your-food-history">Your Food
          History</Link></ListGroupItem>
        <ListGroupItem active={"/others-food-history" === location.pathname}><Link to="/others-food-history">Others Food
          History</Link></ListGroupItem>
      </ListGroup>
    </div>
  );
};

export default withRouter(LeftBar)