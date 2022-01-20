import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Typography from 'components/atoms/Typography/Typography';
import { ReactComponent as MotomoLogo } from 'assets/svgs/motomo-logo.svg';

const Header = styled.div`
  width: 100%;
  height: 80px;
  position: sticky;
  box-shadow: 0 0.25rem 1rem 0 rgb(0 0 0 / 16%);
`;

const Navbar = styled.div`
  max-width: 1400px;
  height: 100%;
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  align-items: center;
`;

const LogoBox = styled.div`
  width: 150px;
`;

const LinksBox = styled.div`
  flex: 1;
  display: flex;
  justify-content: center;
`;

const LinkItem = styled.div`
  font-size: 16px;
  font-weight: bold;
  transition: all 0.3s ease-in-out;
  padding: 0 16px;
  cursor: pointer;

  &:hover {
    color: ${({ theme }) => theme.color.primary};
  }
`;

const ControlBox = styled.div`
  padding-left: 16px;
  display: flex;
  align-items: center;
`;

const RegisterButton = styled.button`
  height: 50px;
  padding: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  color: white;
  background-color: ${({ theme }) => theme.color.secondary};
  transition: all 0.3s ease-in-out;
  cursor: pointer;
  box-shadow: rgba(0, 0, 0, 0.19) 0 10px 20px, rgba(0, 0, 0, 0.23) 0 6px 6px;

  &:hover {
    background-color: ${({ theme }) => theme.color.primary};
  }
`;

const MainSection = styled.div`
  max-width: 1400px;
  height: 100vh;
  margin: 0 auto;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  
`;

const FooterSection = styled.div`
  max-width: 1400px;
  width: 100%;
  height: 100px;
  margin: 0 auto;
  padding: 0 24px;
  font-size: 13px;
  border-top: 1px solid #eee;
  display: flex;
  align-items: center;
`;

const HomePage = () => (
  <>
    <Header>
      <Navbar>
        <LogoBox>
          <MotomoLogo />
        </LogoBox>
        <LinksBox>
          <LinkItem>Cennik</LinkItem>
          <LinkItem>Dokumentacja</LinkItem>
          <LinkItem>Blog</LinkItem>
        </LinksBox>
        <ControlBox>
          <Link to="login">
            <LinkItem>Logowanie</LinkItem>
          </Link>
          <Link to="register">
            <RegisterButton>Wypróbuj Motomo</RegisterButton>
          </Link>
        </ControlBox>
      </Navbar>
    </Header>
    <MainSection>
      <Typography variant="h1" text="MOTOMO" color="white" fontSize="100px" />
    </MainSection>
    <FooterSection>
      &#169; Copyright 2022 Clanhouse
    </FooterSection>
  </>
);

export default HomePage;
