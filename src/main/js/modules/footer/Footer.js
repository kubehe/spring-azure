import React from "react";
import styled from "styled-components";
import {Container} from "reactstrap";

const Wrapper = styled.footer`
position: sticky;
top: 100vh;
`;

const Footer = () => {
  return (
    <Wrapper className="py-5 bg-dark">
      <Container>
        <p className="m-0 text-center text-white">Project for .net 2018-2019</p>
      </Container>
    </Wrapper>
  )
};

export default Footer;