import styled from 'styled-components';

const TableRow = styled.tr`

  &:nth-child(odd) {
    background-color: ${({ theme }) => theme.color.grey100};
  }

  &:nth-child(even) {
    background-color: ${({ theme }) => theme.color.white};
  }
`;

export default TableRow;
