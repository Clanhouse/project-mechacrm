import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: ${({ fullWidth }) => fullWidth && '100%'};
  height: 72px;
  border-radius: 25px;
  border: none;
  cursor: pointer;
  background: ${({ background }) => background};
  transition: all 0.3s ease-in-out;

  &:hover {
    background: ${({ color }) => color};

    & > span {
      color: white;
    }
  }
`;

const Label = styled.span`
  padding: 12px 16px;
  font-size: ${({ fontSize }) => fontSize};
  letter-spacing: 0.014em;
  color: ${({ color }) => color};
  transition: all 0.3s ease-in-out;
`;

const LargeButton = ({ background, color, fontSize, fullWidth, onClick, type, children }) => (
  <Container
    type={type}
    color={color}
    background={background}
    fullWidth={fullWidth}
    onClick={onClick}
  >
    <Label color={color} fontSize={fontSize}>
      {children}
    </Label>
  </Container>
);

LargeButton.propTypes = {
  type: PropTypes.oneOf(['button', 'submit', 'reset']),
  background: PropTypes.string,
  color: PropTypes.string,
  fontSize: PropTypes.string,
  fullWidth: PropTypes.bool,
  onClick: PropTypes.func,
  children: PropTypes.string.isRequired,
};

LargeButton.defaultProps = {
  type: 'button',
  background: '#FFB400',
  color: '#04294F',
  fullWidth: false,
  fontSize: '42px',
  onClick: undefined,
};

export default LargeButton;
