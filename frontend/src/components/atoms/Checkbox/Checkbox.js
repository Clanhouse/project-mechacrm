import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  margin-top: ${({ marginTop }) => `${marginTop}px`};
  margin-left: ${({ marginLeft }) => `${marginLeft}px`};
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
  cursor: pointer;
`;

const Label = styled.span`
  font-size: ${({ fontSize }) => fontSize};
  line-height: 21px;
  color: ${({ theme }) => theme.color.secondary};
  margin: 0 16px;
  letter-spacing: 0.014em;
`;

const Checkbox = ({
  name,
  label,
  checked,
  disabled,
  onChange,
  fontSize,
  mt,
  ml,
}) => (
  <Container marginTop={mt} marginLeft={ml}>
    <Box
      name={name}
      checked={checked}
      disabled={disabled}
      label={label}
      onChange={onChange}
    />
    <Label fontSize={fontSize}>{label}</Label>
  </Container>
);

Checkbox.propTypes = {
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  checked: PropTypes.bool,
  disabled: PropTypes.bool,
  onChange: PropTypes.func,
  mt: PropTypes.number,
  ml: PropTypes.number,
  fontSize: PropTypes.string,
};

Checkbox.defaultProps = {
  checked: false,
  disabled: false,
  onChange: (e) => e.preventDefault(),
  mt: 0,
  ml: 0,
  fontSize: '18px',
};

export default Checkbox;
