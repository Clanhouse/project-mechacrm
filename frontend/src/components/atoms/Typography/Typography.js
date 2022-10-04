import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  color: ${({ theme }) => theme.color.text.primary};

  font-size: ${({ variant }) => {
    switch (variant) {
      case 'h1':
        return '64px';
      case 'h2':
        return '40px';
      case 'h3':
        return '24px';
      case 'h4':
        return '18px';
      default:
        return '16px';
    }
  }};

  font-weight: ${({ variant }) => {
    switch (variant) {
      case 'h1':
        return '700';
      case 'h2':
        return '700';
      case 'h3':
        return '700';
      case 'h4':
        return '700';
      default:
        return '400';
    }
  }};

  line-height: ${({ variant }) => {
    switch (variant) {
      case 'h1':
        return '64px';
      case 'h2':
        return '53px';
      case 'h3':
        return '32px';
      case 'h4':
        return '24px';
      default:
        return '21px';
    }
  }};

  text-align: ${({ align }) => align};
  letter-spacing: -0.02em;
`;

const Typography = ({ variant, children, align }) => (
  <>
    {variant === 'h1' && (
      <Container as='h1' variant={variant} align={align}>
        {children}
      </Container>
    )}
    {variant === 'h2' && (
      <Container as='h2' variant={variant} align={align}>
        {children}
      </Container>
    )}
    {variant === 'h3' && (
      <Container as='h3' variant={variant} align={align}>
        {children}
      </Container>
    )}
    {variant === 'h4' && (
      <Container as='h4' variant={variant} align={align}>
        {children}
      </Container>
    )}
    {variant === 'p' && (
      <Container as='p' variant={variant} align={align}>
        {children}
      </Container>
    )}
  </>
);

Typography.propTypes = {
  align: PropTypes.oneOf(['center', 'inherit', 'left', 'right', 'justify']),
  variant: PropTypes.oneOf(['h1', 'h2', 'h3', 'h4', 'p']),
};

Typography.defaultProps = {
  align: 'inherit',
  variant: 'p',
};

export default Typography;
