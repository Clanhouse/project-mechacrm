import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  padding: 0;
  margin: auto 0;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const Box = styled.input.attrs({ type: 'checkbox' })`
  width: 16px;
  height: 16px;
  background: #efecec;
  border: 1px solid #3f3d56;
  box-sizing: border-box;
  border-radius: 2px;
  margin: 0;
`;

const Label = styled.span`
  font-size: 18px;
  font-family: ${({ theme }) => theme.fontFamily};
  font-weight: ${({ theme }) => theme.fontWeight.regular}
  line-height: 21px;
  color: ${({ theme }) => theme.color.text.primary};
  margin: 0 16px;
  letter-spacing: 0.014em;
`;

const Checkbox = ({
  labelText,
  checked,
  disabled,
  onChange,
}) => (
  <Container>
    <Box
      checked={checked}
      disabled={disabled}
      labelText={labelText}
      onChange={onChange}
    />
    <Label>{labelText}</Label>
  </Container>
);

Checkbox.propTypes = {
  labelText: PropTypes.string.isRequired,
  checked: PropTypes.bool,
  disabled: PropTypes.bool,
  onChange: PropTypes.func,
};

Checkbox.defaultProps = {
  checked: true,
  disabled: false,
  onChange: (e) => e.preventDefault(),
};

export default Checkbox;
