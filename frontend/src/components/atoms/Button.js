import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.button`
  display: flex;
  align-items: center;
  cursor: pointer;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  letter-spacing: 0.02em;
  transition: all 0.2s ease-in-out;

  padding: ${({ size, Icon, text }) => {
    if (Icon && text === null) return '8px 13px';
    if (size === 'normal') return '12px 40px';
    return '8px 40px';
  }};

  height: ${({ size }) => (size === 'normal' ? '45px' : '37px')};

  color: ${({ theme, color, variant }) => {
    if (variant === 'contained') return '#F8F8F8';
    return theme.color[color];
  }};

  background-color: ${({ theme, color, variant }) => {
    if (variant === 'contained') return theme.color[color];
    return 'transparent';
  }};

  border: ${({ theme, color, variant }) => {
    if (variant === 'text') return '2px solid transparent';
    return `2px solid ${theme.color[color]}`;
  }};
`;

const IconBox = styled.p``;

const Text = styled.p`
  margin-left: ${({ Icon, children }) => Icon && children && '8px'};
`;

const Button = ({ variant, children, Icon, color, size, onClick }) => (
  <Container
    variant={variant}
    color={color}
    size={size}
    onClick={onClick}
    Icon={Icon}
    text={children}
  >
    {Icon && (
      <IconBox>
        <Icon size={16} />
      </IconBox>
    )}
    <Text Icon={Icon}>{children}</Text>
  </Container>
);

Button.propTypes = {
  color: PropTypes.oneOf([
    'primary',
    'secondary',
    'light_blue',
    'purple',
    'pink',
    'orange',
    'error',
    'success',
  ]),
  variant: PropTypes.oneOf(['contained', 'outlined', 'text']),
  Icon: PropTypes.elementType,
  size: PropTypes.oneOf(['small', 'normal']),
  onClick: PropTypes.func,
};

Button.defaultProps = {
  color: 'primary',
  variant: 'contained',
  Icon: undefined,
  size: 'normal',
  onClick: undefined,
};

export default Button;
