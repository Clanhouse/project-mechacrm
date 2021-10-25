import React, { useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  width: 100%;
  position: relative;
`;

const Label = styled.div`
  margin: 8px 16px;

  font-family: ${({ theme }) => theme.fontFamily};
  font-size: ${({ fontSize }) => fontSize};
  line-height: ${({ fontSize }) => `calc(${fontSize} + 2px)`};
  letter-spacing: 0.014em;

  color: ${({ color }) => color};
`;

const Input = styled.input`
  box-sizing: border-box;

  height: 54px;
  width: 100%;

  font-family: ${({ theme }) => theme.fontFamily};
  font-size: 18px;
  letter-spacing: 0.014em;

  padding: ${({ icon }) => (icon ? '0 48px 0 16px' : '0 16px')};
  margin: 0;

  border-radius: 25px;
  border: ${({ borderColor, isError, theme }) => (isError ? `1px solid ${theme.color.danger}` : `1px solid ${borderColor}`)};
  outline: none;
  
  background-color: ${({ backgroundColor }) => backgroundColor};
  
  transition: all 0.3s ease-in-out;

  &::placeholder {
    color: ${({ placeholderColor }) => placeholderColor};
  }
  
  &:focus {
    border: ${({ isError, theme }) => (isError ? `1px solid ${theme.color.danger}` : `1px solid ${theme.color.primary}`)};
  }
`;

const IconBox = styled.div`
  width: 24px;
  height: 24px;
  
  position: absolute;

  right: 16px;
  top: calc(50% - 12px);

  background-image: ${({ icon }) => `url(${icon})`};
  background-repeat: no-repeat;

  cursor: ${({ variant }) => (variant === 'password' ? 'pointer' : 'text')};
`;

const Message = styled.div`
  height: ${({ fontSize }) => `calc(${fontSize} + 2px)`};
  
  margin: 8px 16px;

  font-family: ${({ theme }) => theme.fontFamily};
  font-size: ${({ fontSize }) => fontSize};
  line-height: ${({ fontSize }) => `calc(${fontSize} + 2px)`};
  letter-spacing: 0.014em;

  color: ${({ theme }) => theme.color.danger};
`;

const InputField = ({
  variant,
  fontSize,
  color,
  backgroundColor,
  borderColor,
  label,
  placeholder,
  placeholderColor,
  icon,
  isError,
  errorMessage,
  value,
  onChange,
}) => {
  const [type, setType] = useState(variant);
  return (
    <Container>
      <Label fontSize={fontSize}>{label}</Label>
      <Input
        type={type}
        fontSize={fontSize}
        color={color}
        backgroundColor={backgroundColor}
        borderColor={borderColor}
        placeholder={placeholder}
        placeholderColor={placeholderColor}
        icon={icon}
        isError={isError}
        value={value}
        onChange={onChange}
      />
      {icon && (
        <IconBox
          icon={icon}
          variant={variant}
          onClick={() => {
            if (variant === 'password') {
              return (type === 'password' ? setType('text') : setType('password'));
            }
            return 0;
          }}
        />
      )}
      <Message fontSize={fontSize}>{isError && errorMessage}</Message>
    </Container>
  );
};

InputField.propTypes = {
  variant: PropTypes.oneOf(['text', 'password']),
  fontSize: PropTypes.string,
  color: PropTypes.string,
  backgroundColor: PropTypes.string,
  label: PropTypes.string,
  placeholder: PropTypes.string,
  borderColor: PropTypes.string,
  placeholderColor: PropTypes.string,
  icon: PropTypes.string,
  isError: PropTypes.bool,
  errorMessage: PropTypes.string,
  value: PropTypes.string,
  onChange: PropTypes.func,
};

InputField.defaultProps = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  label: 'Label',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  placeholder: 'Input message',
  icon: null,
  isError: false,
  errorMessage: 'Error message',
  value: '',
  onChange: undefined,
};

export default InputField;
