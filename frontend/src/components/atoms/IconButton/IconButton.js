import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

function getContrastColor(color) {
  let hexColor;

  if (color.slice(0, 1) === '#') {
    hexColor = color.slice(1);
  }

  let r;
  let g;
  let b;

  if (hexColor.length < 6) {
    r = parseInt(hexColor.charAt(0) + hexColor.charAt(0), 16);
    g = parseInt(hexColor.charAt(1) + hexColor.charAt(1), 16);
    b = parseInt(hexColor.charAt(2) + hexColor.charAt(2), 16);
  } else {
    r = parseInt(hexColor.substr(0, 2), 16);
    g = parseInt(hexColor.substr(2, 2), 16);
    b = parseInt(hexColor.substr(4, 2), 16);
  }

  const ratio = ((r * 299) + (g * 587) + (b * 114)) / 1000;

  return ratio >= 128 ? '#484848' : 'white';
}

const Container = styled.button`
  background-color: ${({ bgColor, variant }) => (variant === 'outlined' ? 'transparent' : bgColor)};

  color: ${({ bgColor, variant }) => (variant === 'outlined' ? bgColor : getContrastColor(bgColor))};

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
  border: ${({ bgColor }) => `${bgColor} 1px solid`};
  border-radius: 5px;

  display: flex;
  justify-content: center;
  align-items: center;
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
  color: ${({ bgColor, variant }) => (variant === 'outlined' ? bgColor : getContrastColor(bgColor))};

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

function IconButton({
  color,
  label,
  variant,
  size,
  icon,
}) {
  return (
    <Container bgColor={color} variant={variant} size={size}>
      <IconBox size={size} bgColor={color} variant={variant}>{icon}</IconBox>
      {label}
    </Container>
  );
}

IconButton.propTypes = {
  color: PropTypes.string,
  variant: PropTypes.oneOf(['outlined', 'contained']),
  size: PropTypes.oneOf(['small', 'medium', 'large']),
  icon: PropTypes.element.isRequired,
  label: PropTypes.string.isRequired,
};

IconButton.defaultProps = {
  variant: 'outlined',
  size: 'medium',
  color: '#000',
};

export default IconButton;
