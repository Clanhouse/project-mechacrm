import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Cell = styled.td`
  padding: 8px;
  
  color: ${({ theme }) => theme.color.dark};
  font-family: ${({ theme }) => theme.fontFamily};
  font-weight: ${({ theme }) => theme.fontWeight.light};
  font-size: ${({ theme }) => theme.fontSize.m};
  
  text-align: left;
`;

const TableCell = ({ children }) => (
  <Cell>{children}</Cell>
);

TableCell.propTypes = {
  children: PropTypes.element.isRequired,
};

export default TableCell;
