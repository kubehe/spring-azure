import React from 'react';
import {Button} from "reactstrap";
import Navbar from "./modules/navbar/Navbar";
import Main from "./modules/core/Main";
import Footer from "./modules/footer/Footer";
import styled from "styled-components";

const Layout = styled.div`
padding-top: 54px;
height: 100vh;

`;

const App = () => {
  return (
    <Layout>
      <Navbar/>
      <Main/>
      <Footer/>


    </Layout>
  )
};

// const App = () => <Button>dupa</Button>;

export default App;