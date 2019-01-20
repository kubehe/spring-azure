import React, {useEffect, useState} from "react";
import styled from "styled-components";
import {Container} from "reactstrap";
import {getCurrentUserName} from "../../api/api";

const Wrapper = styled.footer`
position: sticky;
top: 100vh;
`;

const Footer = () => {
  const [currentUser, setCurrentUser] = useState("");
  useEffect(() => {
    getCurrentUserName().then((response) => setCurrentUser(response.data.user));
  }, []);


  return (
    <Wrapper className="py-5 bg-dark">
      <Container>
        <p className="m-0 text-center text-white">Project for .net 2018-2019, logged in: {currentUser}</p>
      </Container>
    </Wrapper>
  )
};

export default Footer;