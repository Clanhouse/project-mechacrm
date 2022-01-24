import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.button`
  margin-top: ${({ marginTop }) => `${marginTop}px`};
  margin-left: ${({ marginLeft }) => `${marginLeft}px`};
  
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

const LargeButton = ({ background, color, fontSize, fullWidth, mt, ml, onClick, ...props }) => (
  <Container
    type={props.type}
    color={color}
    background={background}
    fullWidth={fullWidth}
    onClick={onClick}
    marginTop={mt}
    marginLeft={ml}
  >
    <Label color={color} fontSize={fontSize} {...props} />
  </Container>
);

LargeButton.propTypes = {
  type: PropTypes.oneOf(['button', 'submit', 'reset']),
  background: PropTypes.string,
  color: PropTypes.string,
  fontSize: PropTypes.string,
  fullWidth: PropTypes.bool,
  onClick: PropTypes.func,
  mt: PropTypes.number,
  ml: PropTypes.number,
};

LargeButton.defaultProps = {
  type: 'button',
  background: '#FFB400',
  color: '#04294F',
  fullWidth: false,
  fontSize: '42px',
  onClick: undefined,
  mt: 0,
  ml: 0,
};

export default LargeButton;
