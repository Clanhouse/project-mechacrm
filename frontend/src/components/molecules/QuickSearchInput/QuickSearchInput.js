import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  color: ${({ theme }) => theme.color.grey300};
  background-color: ${({ theme }) => theme.color.grey100};
  border-radius: 5px;
  padding: 8px 16px;
  font-family: ${({ theme }) => theme.fontFamily};
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const IconBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 16px;
`;

const SearchField = styled.input`
  width: 100%;
  padding: 6px;
  background-color: transparent;
  color: inherit;
  border: none;
  outline: none;
  font-family: inherit;
  font-size: ${({ theme }) => theme.fontSize.m};
  &::placeholder {
    color: inherit;
  }
`;

const QuickSearchInput = ({ icon, placeholder, value, onChange }) => (
  <Container>
    <IconBox>
      {icon}
    </IconBox>
    <SearchField
      placeholder={placeholder}
      value={value}
      onChange={(e) => onChange(e.target.value)}
    />
  </Container>
);

QuickSearchInput.propTypes = {
  icon: PropTypes.element.isRequired,
  placeholder: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
};

export default QuickSearchInput;
