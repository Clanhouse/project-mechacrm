import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Component = styled.p`
  margin-top: ${({ marginTop }) => `${marginTop}px`};
  margin-left: ${({ marginLeft }) => `${marginLeft}px`};
  color: ${({ color, theme }) => color || theme.color.primary};
  letter-spacing: 0.014em;
  text-align: ${({ align }) => align};
  font-weight: ${({ theme, variant }) => (variant === 'text' ? theme.fontWeight.regular : theme.fontWeight.bold)};
  font-size: ${({ fontSize }) => fontSize};
  text-shadow: ${({ variant }) => {
    switch (variant) {
      case 'h1':
      case 'h2':
        return '0px 4px 4px rgba(0, 0, 0, 0.4)';
      case 'h3':
      case 'h4':
        return '0px 3px 3px rgba(0, 0, 0, 0.4)';
      case 'h5':
      case 'h6':
        return '0px 2px 2px rgba(0, 0, 0, 0.4)';
      default:
        return 'none';
    }
  }};
`;

const Typography = ({ variant, color, fontSize, mt, ml, align, ...props }) => (
  <Component
    as={variant === 'text' ? 'p' : variant}
    variant={variant}
    color={color}
    fontSize={fontSize}
    marginTop={mt}
    marginLeft={ml}
    align={align}
    {...props}
  />
);

Typography.propTypes = {
  variant: PropTypes.oneOf(['text', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6']),
  fontSize: PropTypes.string,
  color: PropTypes.string,
  mt: PropTypes.number,
  ml: PropTypes.number,
  align: PropTypes.oneOf(['center', 'inherit', 'justify', 'left', 'right']),
};

Typography.defaultProps = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  mt: 0,
  ml: 0,
  align: 'inherit',
};

export default Typography;
