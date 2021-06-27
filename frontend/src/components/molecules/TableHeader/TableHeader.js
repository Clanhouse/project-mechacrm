import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import TableHeaderCell from '../../atoms/TableHeaderCell/TableHeaderCell';

const TableHeaderContainer = styled.thead`
  background-color: ${({ theme }) => theme.color.light};
`;

const TableHeader = ({ columns }) => (
  <TableHeaderContainer>
    <tr>
      {columns.map((column) => (
        <th key={column.label}>
          <TableHeaderCell label={column.label} icon={column.icon} color={column.color} />
        </th>
      ))}
    </tr>
  </TableHeaderContainer>
);

TableHeader.propTypes = {
  columns: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default TableHeader;
