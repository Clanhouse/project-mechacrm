import React from 'react';
import PropTypes from 'prop-types';
import TableHeaderCell from '../../atoms/TableHeaderCell/TableHeaderCell';

const TableHeader = ({ columns }) => (
  <thead>
    <tr>
      {columns.map((column) => (
        <th key={column.label}>
          <TableHeaderCell label={column.label} icon={column.icon} />
        </th>
      ))}
    </tr>
  </thead>
);

TableHeader.propTypes = {
  columns: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default TableHeader;
