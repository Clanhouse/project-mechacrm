import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import KeyboardArrowLeftIcon from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRightIcon from '@material-ui/icons/KeyboardArrowRight';

const Container = styled.div`
  box-shadow: rgba(0, 0, 0, 0.15) 10.95px 10.95px 20.6px;

  border: lightgray 1px solid;
  border-radius: 5px;

  display: flex;
  justify-content: center;
  align-items: center;
`;

const Number = styled.div`
  width: 32px;
  height: 32px;

  margin: 8px;

  border-radius: 5px;

  font-family: 'Roboto', sans-serif;
  font-weight: bold;
  font-size: 18px; //TODO

  display: flex;
  justify-content: center;
  align-items: center;

  background-color: ${({ active }) => (active ? 'blue' : 'transparent')};
  color: ${({ active }) => (active ? 'white' : 'inherit')};
`;

const ControlButton = styled.button`
  background-color: white; //TODO
  color: dimgray; //TODO

  border: none;
  border-radius: 5px;

  padding: 4px;
  margin: 8px;

  transition: background-color 200ms ease-in-out;

  &:hover {
    background-color: lightgray;
  }
`;

const second = (count, page) => {
  if (count <= 5 || (count > 5 && page <= 3)) {
    return '2';
  }
  return '...';
};

const third = (count, page) => {
  if (page <= 3) {
    return '3';
  }
  if (page > count - 2) {
    return count - 2;
  }
  return page;
};

const fourth = (count, page) => {
  if (count <= 5) {
    return '4';
  }
  if (page < count - 1) {
    return '...';
  }
  return count - 1;
};

const Pagination = ({
  count,
  page,
  handlePage,
}) => (
  <Container>
    <ControlButton onClick={() => handlePage()}><KeyboardArrowLeftIcon fontSize="large" /></ControlButton>
    {count > 0 && (
      <Number active={page === 1}>
        1
      </Number>
    )}

    {count > 1 && (
      <Number active={page === 2}>
        {second(count, page)}
      </Number>
    )}

    {count > 2 && (
      <Number active={(page === 3) || (count > 5 && page <= count - 2 && page > 2)}>
        {third(count, page)}
      </Number>
    )}

    {count > 3 && (
      <Number active={(page === 4 && count <= 5) || (count > 5 && page === count - 1)}>
        {fourth(count, page)}
      </Number>
    )}

    {count > 4 && (
      <Number active={page === count}>
        {count}
      </Number>
    )}
    <ControlButton onClick={() => handlePage(true)}><KeyboardArrowRightIcon fontSize="large" /></ControlButton>
  </Container>
);

Pagination.propTypes = {
  count: PropTypes.number.isRequired,
  page: PropTypes.number.isRequired,
  handlePage: PropTypes.func.isRequired,
};

export default Pagination;
