import React from 'react';
import TableRow from './TableRow';

export default {
  title: 'Molecules/TableRow',
  component: TableRow,
};

export const TableRowStory = () => (
  <table style={{ width: '100%' }}>
    <TableRow>
      <td>row 1</td>
    </TableRow>
    <TableRow>
      <td>row 2</td>
    </TableRow>
    <TableRow>
      <td>row 3</td>
    </TableRow>
    <TableRow>
      <td>row 4</td>
    </TableRow>
  </table>
);
