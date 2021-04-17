import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

function getRgbValues(color) {
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
  return { r, g, b };
}

function getContrastColor(color, isHoverOrActive) {
  const hexColor = getRgbValues(color);

  const ratio = ((hexColor.r * 299) + (hexColor.g * 587) + (hexColor.b * 114)) / 1000;

  if (isHoverOrActive) {
    return ratio >= 128 ? '#000' : '#fff';
  }

  return ratio >= 128 ? '#585858' : '#b8b8b8';
}

function getBackgroundColorWithOpacity(color) {
  const hexColor = getRgbValues(color);

  return `rgba(${hexColor.r},${hexColor.g},${hexColor.b},0.1)`;
}

const Container = styled.button`
  background-color: ${({ bgColor, variant, active }) => {
    if (variant === 'outlined') {
      return (active) ? getBackgroundColorWithOpacity(bgColor) : 'transparent';
    }
    return bgColor;
  }};

  color: ${({
    bgColor,
    variant,
    active,
  }) => (variant === 'outlined' ? bgColor : getContrastColor(bgColor, active))};

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
  
  transition: background-color 250ms ease-in-out;

  &:hover {
    background-color: ${({ bgColor, variant }) => (variant === 'outlined' ? getBackgroundColorWithOpacity(bgColor) : null)};
  }

  &:hover * {
    transition: color 200ms ease;
    color: ${({ bgColor, variant }) => (variant === 'outlined' ? null : getContrastColor(bgColor, true))};
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
    bgColor,
    variant,
    active,
  }) => (variant === 'outlined' ? bgColor : getContrastColor(bgColor, active))};

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

function IconButton({
  color,
  variant,
  size,
  icon,
  label,
  active,
  ...props
}) {
  return (
    <Container
      bgColor={color}
      variant={variant}
      size={size}
      active={active}
      {...props}
    >
      <IconBox
        bgColor={color}
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
}

IconButton.propTypes = {
  color: PropTypes.string,
  variant: PropTypes.oneOf(['outlined', 'contained']),
  size: PropTypes.oneOf(['small', 'medium', 'large']),
  icon: PropTypes.element.isRequired,
  label: PropTypes.string.isRequired,
  active: PropTypes.bool,
  onClick: PropTypes.func,
};

IconButton.defaultProps = {
  variant: 'outlined',
  size: 'medium',
  color: '#000',
  active: false,
  onClick: undefined,
};

export default IconButton;
