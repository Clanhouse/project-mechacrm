import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  background-color: ${({ theme }) => theme.color.white};
  margin-bottom: 2px;
`;

const Title = styled.div`
  padding: 16px 24px;
  text-transform: capitalize;
  font-family: ${({ theme }) => theme.fontFamily};
  font-weight: ${({ theme }) => theme.fontWeight.bold};
  font-size: ${({ theme }) => theme.fontSize.l};
  color: ${({ theme }) => theme.color.primary};

  box-shadow: rgba(0, 0, 0, 0.2) 5px 6px 6px;
`;

const Header = ({ title }) => (
  <Container>
    <Title>{title}</Title>
  </Container>
);

Header.propTypes = {
  title: PropTypes.string.isRequired,
};

export default Header;
