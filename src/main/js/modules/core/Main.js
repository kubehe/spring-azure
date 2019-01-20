import React from "react";
import {Container, Row} from "reactstrap";
import Page from "./page/Page";
import LeftBar from "./left-bar/LeftBar";

const Main = () => {
  return (
    <Container>
      <Row>
        <LeftBar/>
        <div className="col-lg-9">
          <Page/>
        </div>
      </Row>
    </Container>
  )
};

export default Main;
