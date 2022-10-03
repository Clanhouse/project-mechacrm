import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import backgroundImage from 'assets/images/notFound.jpg';

const Wrapper = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;

  height: 100vh;

  &::before {
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;

    background: url(${backgroundImage}) center/cover no-repeat;
    filter: brightness(60%);
  }
`;

const MessageBox = styled.div`
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Title = styled.h1`
  text-transform: uppercase;
  font-size: 52px;
`;

const NotFoundPage = () => (
  <Wrapper>
    <MessageBox>
      <Title>Strona nie została znaleziona</Title>
      <Link style={{ color: 'white', fontSize: '24px' }} to="/">Powrót na strona główną</Link>
    </MessageBox>
  </Wrapper>
);

export default NotFoundPage;
