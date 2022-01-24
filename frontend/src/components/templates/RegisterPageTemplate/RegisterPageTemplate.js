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
`;

const LeftSide = styled.div`
  height: 100%;
  background-color: ${({ theme }) => theme.color.light};
`;

const LogoBox = styled.div`
  width: 200px;
  margin: 100px 0 0 80px;
`;

const SloganBox = styled.div`
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const RightSide = styled.div`
  padding-left: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const RegisterPageTemplate = ({ formSection }) => (
  <Container>
    <LeftSide>
      <LogoBox>
        <MotomoLogo />
      </LogoBox>
      <SloganBox>
        <MotomoGear height={150} />
        <Typography variant="h1" fontSize="40px" align="center" mt={40}>
          Zaufaj profesjonalistom
        </Typography>
        <Typography align="center" mt={24}>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          hdhdhdhfhffhhh
        </Typography>
      </SloganBox>
    </LeftSide>
    <RightSide>
      {formSection}
    </RightSide>
  </Container>
);

RegisterPageTemplate.propTypes = {
  formSection: PropTypes.element.isRequired,
};

export default RegisterPageTemplate;
