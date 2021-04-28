import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import Header from '../../atoms/Header/Header';
import TableRow from '../../molecules/TableRow/TableRow';
import TableHeader from '../../molecules/TableHeader/TableHeader';
import TableCell from '../../atoms/TableCell/TableCell';

const Wrapper = styled.div`
  padding: 8px;

  background-color: ${({ theme }) => theme.color.grey};
`;

const TableContainer = styled.table`
  width: 100%;
  border-spacing: 0;
`;

const TableBody = styled.tbody`
`;

const Table = ({ columns, rowsData }) => (
  <Wrapper>
    <Header title="customers" />
    <TableContainer>
      <TableHeader columns={columns} />
      <TableBody>
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
      </TableBody>
    </TableContainer>
  </Wrapper>
);

Table.propTypes = {
  columns: PropTypes.arrayOf(PropTypes.object).isRequired,
  rowsData: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default Table;
