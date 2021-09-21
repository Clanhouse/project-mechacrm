import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Component = styled.div`
  margin: 0;

  font-family: ${({ theme }) => theme.fontFamily};
  font-size: ${({ fontSize }) => fontSize};
  font-weight: ${({ theme }) => theme.fontWeight.regular};
  letter-spacing: 0.014em;
  line-height: ${({ fontSize }) => `calc(${fontSize} + 4px)`};

  color: ${({
    color,
    theme,
  }) => (color || theme.color.text.primary)};
`;

const Typography = ({
  variant,
  text,
  fontSize,
  color,
}) => (
  <>
    {variant === 'text' && <Component fontSize={fontSize} color={color}>{text}</Component>}

    {variant === 'h1' && (
    <Component
      as="h1"
      style={{
        fontWeight: 'bold',
        textShadow: '0px 4px 4px rgba(0, 0, 0, 0.4)',
      }}
      fontSize={fontSize}
      color={color}
    >{text}
    </Component>
    )}

    {variant === 'h2' && (
      <Component
        as="h2"
        style={{
          fontWeight: 'bold',
          textShadow: '0px 4px 4px rgba(0, 0, 0, 0.4)',
        }}
        fontSize={fontSize}
        color={color}
      >{text}
      </Component>
    )}

    {variant === 'h3' && (
      <Component
        as="h3"
        style={{
          fontWeight: 'bold',
          textShadow: '0px 3px 3px rgba(0, 0, 0, 0.4)',
        }}
        fontSize={fontSize}
        color={color}
      >{text}
      </Component>
    )}

    {variant === 'h4' && (
      <Component
        as="h4"
        style={{
          fontWeight: 'bold',
          textShadow: '0px 3px 3px rgba(0, 0, 0, 0.4)',
        }}
        fontSize={fontSize}
        color={color}
      >{text}
      </Component>
    )}

    {variant === 'h5' && (
      <Component
        as="h5"
        style={{
          fontWeight: 'bold',
          textShadow: '0px 2px 2px rgba(0, 0, 0, 0.4)',
        }}
        fontSize={fontSize}
        color={color}
      >{text}
      </Component>
    )}

    {variant === 'h6' && (
      <Component
        as="h6"
        style={{
          fontWeight: 'bold',
          textShadow: '0px 2px 2px rgba(0, 0, 0, 0.4)',
        }}
        fontSize={fontSize}
        color={color}
      >{text}
      </Component>
    )}
  </>
);

Typography.propTypes = {
  text: PropTypes.string.isRequired,
  variant: PropTypes.oneOf(['text', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6']),
  fontSize: PropTypes.string,
  color: PropTypes.string,
};

Typography.defaultProps = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
};

export default Typography;
