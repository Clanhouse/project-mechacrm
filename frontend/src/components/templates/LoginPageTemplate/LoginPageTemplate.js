import React from 'react';
import styled from 'styled-components';
import motomoLogo from 'assets/images/motomo-logo.png';
import backgroundImg from 'assets/images/login-page-background.png';
import PropTypes from 'prop-types';

const Container = styled.div`
  margin: 0 auto;
  
  width: 1440px;
  height: 1024px;
  
  background-image: url(${backgroundImg});
  
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 70px;
`;

const Logo = styled.div`
  margin: 16px 80px;
`;

const SloganSection = styled.div`
  margin-top: 160px;
  padding-left: 70px;
  height: 180px;
`;

const LoginSection = styled.div`
  display: block;
  margin-top: 140px;
  padding: 0 70px 70px;
`;

const LoginPageTemplate = ({ slogan, loginSection }) => (
  <Container>
    <div>
      <Logo>
        <img src={motomoLogo} alt="motomo logo" />
      </Logo>
      <SloganSection>{slogan}</SloganSection>
    </div>

    <div>
      <LoginSection>{loginSection}</LoginSection>
    </div>
  </Container>
);

LoginPageTemplate.propTypes = {
  slogan: PropTypes.element.isRequired,
  loginSection: PropTypes.element.isRequired,
};

export default LoginPageTemplate;
