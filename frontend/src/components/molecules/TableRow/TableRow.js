import styled from 'styled-components';

const TableRow = styled.tr`

  &:nth-child(odd) {
    background-color: ${({ theme }) => theme.color.light100};
  }

  &:nth-child(even) {
    background-color: ${({ theme }) => theme.color.light};
  }
`;

export default TableRow;
