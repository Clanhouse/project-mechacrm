import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const theme = {
  color: {
    primary: '#007bff',
    secondary: '#6c757d',
    textColor: '#ccc',
    active: '#eee',
  },
};

const Container = styled.button`
  background-color: ${({
    color,
    variant,
  }) => {
    if (variant === 'contained') {
      return color === 'primary' ? theme.color.primary : theme.color.secondary;
    }
    return 'transparent';
  }};
  color: ${({ active }) => (active ? theme.color.active : theme.color.textColor)};

  margin: 8px;
  padding: ${({ size }) => {
    switch (size) {
      case 'small':
        return '6px 10px';
      case 'large':
        return '10px 22px';
      default:
        return '8px 16px';
    }
  }};

  font-family: 'Roboto', sans-serif;
  font-size: ${({ size }) => {
    switch (size) {
      case 'small':
        return '13px';
      case 'large':
        return '15px';
      default:
        return '14px';
    }
  }};

  font-weight: bolder;
  text-transform: uppercase;

  outline: none;
  border: ${({ color }) => `${color === 'primary' ? theme.color.primary : theme.color.secondary} 1px solid`};
  border-radius: 5px;

  display: flex;
  justify-content: center;
  align-items: center;

  transition: background-color 250ms ease-in-out;

  &:hover * {
    transition: color 200ms ease;
    color: ${theme.color.active};
  }
`;

const IconBox = styled.span`
  margin-right: 8px;
  margin-left: ${({ size }) => {
    switch (size) {
      case 'medium':
        return '-2px';
      case 'large':
        return '-4px';
      default:
        return 0;
    }
  }};

  transition: color 250ms ease-in-out;

  color: ${({
    active,
  }) => {
    if (active) {
      return theme.color.active;
    }
    return theme.color.textColor;
  }};

  width: ${({ size }) => {
    switch (size) {
      case 'small':
        return '18px';
      case 'large':
        return '22px';
      default:
        return '20px';
    }
  }};
  height: ${({ size }) => {
    switch (size) {
      case 'small':
        return '18px';
      case 'large':
        return '22px';
      default:
        return '20px';
    }
  }}
`;

const Label = styled.span`
  transition: color 250ms ease-in-out;
`;

const IconButton = ({
  color,
  variant,
  size,
  icon,
  label,
  active,
  ...props
}) => (
  <Container
    color={color}
    variant={variant}
    size={size}
    active={active}
    {...props}
  >
    <IconBox
      color={color}
      variant={variant}
      size={size}
      active={active}
    >
      {icon}
    </IconBox>
    <Label>
      {label}
    </Label>
  </Container>
);

IconButton.propTypes = {
  color: PropTypes.oneOf(['primary', 'secondary']),
  variant: PropTypes.oneOf(['outlined', 'contained']),
  size: PropTypes.oneOf(['small', 'medium', 'large']),
  icon: PropTypes.element.isRequired,
  label: PropTypes.string.isRequired,
  active: PropTypes.bool,
  onClick: PropTypes.func,
};

IconButton.defaultProps = {
  color: 'primary',
  variant: 'outlined',
  size: 'medium',
  active: false,
  onClick: undefined,
};

export default IconButton;
