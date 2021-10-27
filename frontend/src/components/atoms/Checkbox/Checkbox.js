import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import theme from 'theme/MainTheme';

const Container = styled.div`
  padding: 0;
  margin: auto 10;
  width:100%;
  display: flex;
  justify-content: flex-start;
  align-content: center;
  align-items: center;
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
  font-size: 18px;
  font-family: ${theme.fontFamily};
  font-weight: ${theme.fontWeight.regular}
  line-height: 21px;
  color: ${theme.color.text.primary};
  margin: 10px 16px;
`;

const checkbox = ({
  labeltext,
  checked,
  disabled,
}) => (
  <Container>
    <Box checked={checked} disabled={disabled} labeltext={labeltext} />
    <Label>{labeltext}</Label>
  </Container>
);

checkbox.propTypes = {
  labeltext: PropTypes.string.isRequired,
  checked: PropTypes.bool,
  disabled: PropTypes.bool,
};

checkbox.defaultProps = {
  labeltext: 'lorem ipsum',
};

export default checkbox;
