import React from 'react';
import styled from 'styled-components';
import TableCell from './TableCell';

const DivContainer = styled.div`
  display: flex;
  justify-content: center;
  border: green 1px solid;
  padding: 8px 16px;
  font-size: 14px;
`;

const Button = styled.button`
  border-radius: 5px;
  border: none;
  background-color: rgba(102, 51, 153, 0.8);
  color: white;
  padding: 8px 16px;
  text-transform: uppercase;
  font-weight: bold;
  
  &:hover {
    background-color: rgba(102, 51, 153, 1);
  }
`;

export default {
  title: 'Atoms/TableCell',
  component: TableCell,
};

export const TableCellAsText = () => <TableCell>text table cell</TableCell>;

export const TableCellAsDiv = () => (
  <TableCell>
    <DivContainer>
      table cell as div
    </DivContainer>
  </TableCell>
);

export const TableCellAsButton = () => (
  <TableCell>
    <Button>
      table cell as button
    </Button>
  </TableCell>
);