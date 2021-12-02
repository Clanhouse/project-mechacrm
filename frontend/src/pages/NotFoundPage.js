import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  height: 100vh;
`;

const NotFoundPage = () => (
  <Wrapper>
    <h1>Strona nie została znaleziona</h1>
    <Link to="/">Powrót na strona główną</Link>
  </Wrapper>
);

export default NotFoundPage;
