import React from 'react';
import styled from 'styled-components';
import { ReactComponent as MotomoLogo } from 'assets/svgs/motomo-logo.svg';
import backgroundImg from 'assets/images/login-page-background.png';
import PropTypes from 'prop-types';
import Typography from 'components/atoms/Typography/Typography';

const Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  background: url(${backgroundImg}) no-repeat center/cover;
`;

const Container = styled.div`
  max-width: 1200px;
  height: 100%;
  margin: 0 auto;
  padding: 40px;

  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 60px;
`;

const LeftSection = styled.div`
  margin: 64px 0 0 0;
`;

const Logo = styled.div`
  width: 200px;
`;

const RightSection = styled.div`
  
`;

const SloganSection = styled.div`
  margin-top: 160px;
`;

const LoginSection = styled.div`
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  
  padding: 50px;

  & > h2 {
    margin-bottom: 24px;
  }
  
  & > p {
    margin-bottom: 16px;
  }
  
  & > form {
    & div:nth-child(3) {
      margin-left: 16px;
    }
    
    & button {
      margin-top: 16px;
    }
  }
`;

const LoginPageTemplate = ({ loginSection }) => (
  <Wrapper>
    <Container>
      <LeftSection>
        <Logo>
          <MotomoLogo />
        </Logo>
        <SloganSection>
          <Typography variant="h1" fontSize="36px">
            Kontrola biznesu w zasięgu ręki
          </Typography>
          <Typography mt={42} fontSize="20px">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore et dolore magna aliqua.
          </Typography>
        </SloganSection>
      </LeftSection>

      <RightSection>
        <LoginSection>{loginSection}</LoginSection>
      </RightSection>
    </Container>
  </Wrapper>
);

LoginPageTemplate.propTypes = {
  loginSection: PropTypes.element.isRequired,
};

export default LoginPageTemplate;
