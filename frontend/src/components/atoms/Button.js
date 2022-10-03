import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.button`
  border-radius: 8px;
  padding: 12px 40px;

  color: white;

  background-color: ${({ theme, color }) => {
    switch (color) {
      case 'primary':
        return theme.color.primary;
      case 'secondary':
        return theme.color.secondary;
      default:
        return 'yellow';
    }
  }};

  border: ${({ theme, color }) => {
    switch (color) {
      case 'primary':
        return `2px solid ${theme.color.primary}`;
      case 'secondary':
        return `2px solid ${theme.color.secondary}`;
      default:
        return `2px solid ${theme.color.primary}`;
    }
  }};
`;

const IconBox = styled.p``;

const Text = styled.p``;

const Button = ({ variant, children, Icon, color, size, onClick }) => (
  <Container variant={variant} color={color} size={size} onClick={onClick}>
    {Icon && <IconBox>button</IconBox>}
    <Text>{children}</Text>
  </Container>
);

Button.propTypes = {
  color: PropTypes.oneOf([
    'primary',
    'secondary',
    'success',
    'danger',
    'warning',
    'info',
  ]),
  variant: PropTypes.oneOf(['normal', 'outlined']),
  Icon: PropTypes.elementType,
  size: PropTypes.oneOf(['small', 'normal', 'large']),
  onClick: PropTypes.func,
};

Button.defaultProps = {
  color: 'primary',
  variant: 'normal',
  Icon: undefined,
  size: 'normal',
  onClick: undefined,
};

export default Button;
