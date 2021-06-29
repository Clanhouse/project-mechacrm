import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import Header from 'components/atoms/Header/Header';
import TableRow from 'components/molecules/TableRow/TableRow';
import TableHeader from 'components/molecules/TableHeader/TableHeader';
import TableCell from 'components/atoms/TableCell/TableCell';

const TableContainer = styled.table`
  width: 100%;
  border-spacing: 0;
  box-shadow: rgba(0,0,0,0.2) 5px 6px 6px;
`;

const Table = ({ columns, rowsData }) => (
  <>
    <Header title="customers" />
    <TableContainer>
      <TableHeader columns={columns} />
      <tbody>
        {rowsData.map((row) => (
          <TableRow key={row.id}>
            <TableCell>{row.id}</TableCell>
            <TableCell>{row.name}</TableCell>
            <TableCell>{row.surname}</TableCell>
            <TableCell>{row.phone}</TableCell>
            <TableCell>{row.address}</TableCell>
            <TableCell>{row.cars}</TableCell>
          </TableRow>
        ))}
      </tbody>
    </TableContainer>
  </>
);

Table.propTypes = {
  columns: PropTypes.arrayOf(PropTypes.object).isRequired,
  rowsData: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default Table;
