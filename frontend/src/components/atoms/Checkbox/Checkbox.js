import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  background-color: none;
  padding: 0;
  margin: 0;
`;

const Box = styled.input.attrs({ type: 'checkbox' })`
  width: 16px;
  height: 16px;
  background: #efecec;
  border: 1px solid #3f3d56;
  box-sizing: border-box;
  border-radius: 2px;
`;

const Label = styled.span`
  font-size: ${({ theme }) => theme.fontSize.m};
  font-family: ${({ theme }) => theme.fontFamily};
  font-weight: ${({ theme }) => theme.fontWeight.regular}
  line-height: 1.16em;
  color: ${({ theme }) => theme.color.secondary};
  margin: 8px 16px;
`;

const Checkbox = ({ LabelText, checked, disabled }) => (
  <Container>
    <Box checked={checked} disabled={disabled} />
    <Label>{LabelText}</Label>
  </Container>
);

Checkbox.propTypes = {
  LabelText: PropTypes.string.isRequired,
  checked: PropTypes.bool,
  disabled: PropTypes.bool,
};

Checkbox.defaultProps = {
  checked: false,
  disabled: false,
};

export default Checkbox;
