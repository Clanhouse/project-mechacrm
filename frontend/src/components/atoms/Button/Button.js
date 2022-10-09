import React, { useRef } from 'react';
import styled, { keyframes } from 'styled-components';
import { btnAnimation } from 'helper/btnAnimation';
import PropTypes from 'prop-types';

const Container = styled.button`
  display: flex;
  align-items: center;
  cursor: ${({ inactive }) => !inactive && 'pointer'};
  position: relative;
  overflow: hidden;

  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  letter-spacing: 0.02em;
  transition: all 0.2s ease-in-out;

  padding: ${({ size }) => (size === 'normal' ? '12px 40px' : '8px 40px')};

  height: ${({ size }) => (size === 'normal' ? '46px' : '38px')};

  color: ${({ theme, color, variant }) =>
    variant === 'contained' ? theme.color.gray[1] : theme.color[color][1]};

  background-color: transparent;

  border: ${({ theme, color, variant, inactive }) => {
    if (inactive) return `2px solid ${theme.color.gray[3]}`;
    if (variant === 'text') return '2px solid transparent';
    return `2px solid ${theme.color[color][1]}`;
  }};

  &::after {
    position: absolute;
    inset: 0;
    z-index: -1;
    content: '';
    width: 100%;
    background-color: ${({ theme, color, variant, inactive }) => {
      if (inactive) return theme.color.gray[3];
      if (variant === 'contained') return theme.color[color][1];
      return 'transparent';
    }};
  }

  &:hover {
    border: ${({ theme, color, variant, inactive }) => {
      if (inactive) return `2px solid ${theme.color.gray[3]}`;
      if (variant === 'text') return '2px solid transparent';
      return `2px solid ${theme.color[color][2]}`;
    }};

    background-color: ${({ theme, color, variant, inactive }) => {
      if (inactive) return theme.color.gray[3];
      if (variant === 'contained') return theme.color[color][2];
      return 'transparent';
    }};

    color: ${({ theme, color, variant }) =>
      variant !== 'contained' ? theme.color[color][2] : theme.color.gray[1]};
  }
`;

const zoom = keyframes`
  to {
    transform: translate(-50%, -50%) scale(2);
    opacity: 0;
  }
`;

const Circle = styled.span`
  position: absolute;
  width: 200px;
  height: 200px;
  background-color: white;
  border-radius: 50%;
  transform: translate(-50%, -50%) scale(0);
  animation: ${zoom} 0.5s;
  display: none;
`;

const IconBox = styled.div`
  width: 18px;
  height: 18px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Text = styled.p`
  margin-left: ${({ Icon, children }) => Icon && children && '8px'};
`;

const Button = ({
  variant,
  children,
  Icon,
  color,
  size,
  onClick,
  inactive,
}) => {
  const circleRef = useRef(null);
  return (
    <div
      onClick={(e) => btnAnimation(e, inactive, circleRef)}
      aria-hidden='true'
    >
      <Container
        variant={variant}
        color={color}
        size={size}
        onClick={onClick}
        Icon={Icon}
        text={children}
        inactive={inactive}
      >
        {Icon && (
          <IconBox>
            <Icon size={16} />
          </IconBox>
        )}
        <Text Icon={Icon}>{children}</Text>
        <Circle ref={circleRef} />
      </Container>
    </div>
  );
};

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
  inactive: PropTypes.bool,
};

Button.defaultProps = {
  color: 'primary',
  variant: 'contained',
  Icon: undefined,
  size: 'normal',
  onClick: undefined,
  inactive: false,
};

export default Button;
