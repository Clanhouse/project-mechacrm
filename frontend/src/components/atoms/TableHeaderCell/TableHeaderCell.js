import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  margin: 0 8px;
  padding: 8px;
  
  display: flex;
  justify-content: flex-start;
  align-items: center;
  
  color: ${({ color, theme }) => {
    switch (color) {
      case 'secondary': return theme.color.secondary;
      case 'success': return theme.color.success;
      case 'info': return theme.color.info;
      case 'warning': return theme.color.warning;
      case 'danger': return theme.color.danger;
      default: return theme.color.primary;
    }
  }};
`;

const IconBox = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const Label = styled.div`
  margin-left: 8px;
  
  font-family: ${({ theme }) => theme.fontFamily};
  font-weight: ${({ theme }) => theme.fontWeight.bold};
  font-size: ${({ theme }) => theme.fontSize.m};
  
  text-transform: capitalize;
`;

const TableHeaderCell = ({
  icon,
  label,
  color,
}) => (
  <Container color={color}>
    <IconBox>{icon}</IconBox>
    <Label>{label}</Label>
  </Container>
);

TableHeaderCell.propTypes = {
  icon: PropTypes.element.isRequired,
  label: PropTypes.string.isRequired,
  color: PropTypes.oneOf(['primary', 'secondary', 'success', 'info', 'warning', 'danger']),
};

TableHeaderCell.defaultProps = {
  color: 'primary',
};

export default TableHeaderCell;
