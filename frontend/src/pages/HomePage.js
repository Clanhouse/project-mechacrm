import React from 'react';
import styled from 'styled-components';
import AdvancedButton from 'components/atoms/AdvancedButton/AdvancedButton';
import { Link } from 'react-router-dom';

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  
  height: 100vh;
`;

const HomePage = () => (
  <Wrapper>
    <Link to="login"><AdvancedButton text="logowanie" /> </Link>
  </Wrapper>
);

export default HomePage;
