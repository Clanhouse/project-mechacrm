import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div``;

const Input = styled.input`
  width: 100%;
  min-height: 42px;

  padding: 12px 24px;

  font-size: 14px;
  line-height: 18px;

  outline: none;

  border-radius: 2px;
  border: ${({ theme, error }) =>
    error
      ? `2px solid ${theme.color.error[1]}`
      : `2px solid ${theme.color.gray[2]}`};

  transition: border 0.3s ease-in-out;

  &:focus,
  &:hover {
    border: ${({ theme, error }) =>
      error
        ? `2px solid ${theme.color.error[1]}`
        : `2px solid ${theme.color.primary[1]}`};
  }

  &::placeholder {
    color: ${({ theme }) => `${theme.color.gray[3]}`};
  }
`;

const InputField = ({ id, type, label, error, value, onChange }) => (
  <Container>
    <Input
      id={id}
      type={type}
      placeholder={label}
      error={error}
      value={value}
      onChange={onChange}
    />
  </Container>
);

InputField.propTypes = {
  id: PropTypes.string.isRequired,
  type: PropTypes.oneOf(['text', 'password', 'email']),
  label: PropTypes.string.isRequired,
  error: PropTypes.bool,
  value: PropTypes.string,
  onChange: PropTypes.func,
};

InputField.defaultProps = {
  type: 'text',
  error: false,
  value: undefined,
  onChange: undefined,
};

export default InputField;
