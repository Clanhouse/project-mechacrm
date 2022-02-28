import React from 'react';
import styled from 'styled-components';
import { ReactComponent as MotomoLogo } from 'assets/svgs/motomo-logo.svg';
import { ReactComponent as MotomoGear } from 'assets/svgs/motomo-gear.svg';
import Typography from 'components/atoms/Typography/Typography';
import PropTypes from 'prop-types';

const Container = styled.div`
  height: 100vh;
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  
  @media only screen and (max-width: 1400px) {
    grid-template-columns: 1fr 2fr;
  }
`;

const LeftSide = styled.div`
  height: 100%;
  background-color: ${({ theme }) => theme.color.light};
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const LogoBox = styled.div`
  width: 200px;
  margin: 0 auto 80px;
`;

const SloganBox = styled.div`
  padding: 24px;
  display: flex;
  flex-direction: column;
  align-items: center;
  
  & > h1 {
    margin-top: 40px;
  }
  
  & > p {
    margin-top: 24px;
  }
`;

const RightSide = styled.div`
  padding: 0 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  
  & > p {
    margin-top: 16px;
  }
  
  & form {
    & > div:nth-child(2) {
      margin-left: 16px;
    }
    
    & button {
      margin-top: 16px;
    }
  }
`;

const RegisterPageTemplate = ({ formSection }) => (
  <Container>
    <LeftSide>
      <LogoBox>
        <MotomoLogo />
      </LogoBox>
      <SloganBox>
        <MotomoGear height={150} />
        <Typography variant="h1" fontSize="40px" align="center">
          Zaufaj profesjonalistom
        </Typography>
        <Typography align="center">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
        </Typography>
      </SloganBox>
    </LeftSide>
    <RightSide>{formSection}</RightSide>
  </Container>
);

RegisterPageTemplate.propTypes = {
  formSection: PropTypes.element.isRequired,
};

export default RegisterPageTemplate;
