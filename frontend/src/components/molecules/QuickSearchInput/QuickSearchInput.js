import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  color: rgba(13, 71, 161, 0.5);
  background-color: ${({ theme }) => theme.color.grey100};
  border-radius: 20px;
  padding: 8px 16px;
  font-family: ${({ theme }) => theme.fontFamily};
  display: grid;
  grid-template-columns: auto 100%;
  align-items: center;
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
    {icon}
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
