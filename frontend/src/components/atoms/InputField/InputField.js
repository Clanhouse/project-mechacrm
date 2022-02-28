import React, { useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  width: 100%;
  position: relative;
`;

const Label = styled.label`
  display: block;
  margin: 2px 16px 4px;
  letter-spacing: 0.014em;
  color: ${({ color }) => color};
  font-size: 14px;
`;

const Input = styled.input`
  height: 54px;
  width: 100%;
  letter-spacing: 0.014em;
  padding: ${({ icon }) => (icon ? '0 48px 0 16px' : '0 16px')};
  border-radius: 25px;
  border: ${({ borderColor, errorMessage, theme }) => (errorMessage.length > 0
    ? `1px solid ${theme.color.danger}`
    : `1px solid ${borderColor}`
  )};
  outline: none;
  background-color: ${({ backgroundColor }) => backgroundColor};
  transition: all 0.3s ease-in-out;

  &::placeholder {
    color: ${({ placeholderColor }) => placeholderColor};
  }

  &:focus {
    border: ${({ errorMessage, theme }) => (errorMessage.length > 0
    ? `1px solid ${theme.color.danger}`
    : `1px solid ${theme.color.primary}`)};
  }
`;

const IconBox = styled.div`
  width: 24px;
  height: 24px;
  position: absolute;
  right: 16px;
  top: calc(50% - 12px);
  cursor: ${({ variant }) => (variant === 'password' ? 'pointer' : 'text')};
`;

const Message = styled.div`
  height: 19px;
  margin: 4px 16px 0;
  white-space: pre-wrap;
  font-size: 14px;
  letter-spacing: 0.014em;
  color: ${({ theme }) => theme.color.danger};
`;

const InputField = ({
  name,
  variant,
  fontSize,
  color,
  backgroundColor,
  borderColor,
  label,
  placeholder,
  placeholderColor,
  Icon,
  errorMessage,
  value,
  onChange,
  onBlur,
}) => {
  const [variantState, setVariantState] = useState(variant);
  return (
    <Container>
      <Label htmlFor={name}>{label}</Label>
      <Input
        id={name}
        name={name}
        type={variantState}
        fontSize={fontSize}
        color={color}
        backgroundColor={backgroundColor}
        borderColor={borderColor}
        placeholder={placeholder}
        placeholderColor={placeholderColor}
        icon={Icon}
        errorMessage={errorMessage}
        value={value}
        onChange={onChange}
        onBlur={onBlur}
      />
      {Icon && (
        <IconBox
          variant={variant}
          onClick={() => {
            if (variant === 'password') {
              return variantState === 'password' ? setVariantState('text') : setVariantState('password');
            }
            return 0;
          }}
        >
          <Icon size={24} />
        </IconBox>
      )}
      <Message fontSize={fontSize}>{errorMessage}</Message>
    </Container>
  );
};

InputField.propTypes = {
  variant: PropTypes.oneOf(['text', 'password']),
  name: PropTypes.string.isRequired,
  fontSize: PropTypes.string,
  color: PropTypes.string,
  backgroundColor: PropTypes.string,
  label: PropTypes.string,
  placeholder: PropTypes.string,
  borderColor: PropTypes.string,
  placeholderColor: PropTypes.string,
  Icon: PropTypes.func,
  errorMessage: PropTypes.string,
  value: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  onBlur: PropTypes.func.isRequired,
};

InputField.defaultProps = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  label: 'Label',
  borderColor: '#04294F',
  placeholderColor: '#ccc7c7',
  placeholder: 'Input message',
  Icon: null,
  errorMessage: '',
  value: '',
};

export default InputField;
