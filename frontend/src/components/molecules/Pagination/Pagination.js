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

const getFirstPaginationElement = (selectedPage) => (
  <Number active={selectedPage === 1}>1</Number>
);

const getSecondPaginationElement = (selectedPage, numberOfPages) => {
  if (numberOfPages === 4 || numberOfPages === 5) {
    return <Number active={selectedPage === 2}>2</Number>;
  }
  if (numberOfPages > 5) {
    if (selectedPage <= 3) {
      return <Number active={selectedPage === 2}>2</Number>;
    }
    return <Number>...</Number>;
  }
  return null;
};

const getMiddlePaginationElement = (selectedPage, numberOfPages) => {
  if (numberOfPages === 3) {
    return <Number active={selectedPage === 2}>2</Number>;
  }
  if (numberOfPages === 5) {
    return <Number active={selectedPage === 3}>3</Number>;
  }

  if (numberOfPages > 5) {
    if (selectedPage <= 3) {
      return <Number active={selectedPage === 3}>3</Number>;
    }
    if (selectedPage >= numberOfPages - 2) {
      return <Number active={selectedPage < numberOfPages - 1}>{numberOfPages - 2}</Number>;
    }
    return <Number active>{selectedPage}</Number>;
  }

  return null;
};

const getPenultimatePaginationElement = (selectedPage, numberOfPages) => {
  if (numberOfPages === 4) {
    return <Number active={selectedPage === 3}>3</Number>;
  }
  if (numberOfPages === 5) {
    return <Number active={selectedPage === 4}>4</Number>;
  }

  if (numberOfPages > 5) {
    if (selectedPage >= numberOfPages - 2) {
      return <Number active={selectedPage === numberOfPages - 1}>{numberOfPages - 1}</Number>;
    }
    return <Number>...</Number>;
  }

  return null;
};

const getLastPaginationElement = (selectedPage, numberOfPages) => (
  numberOfPages > 1 ? (
    <Number active={selectedPage === numberOfPages}>{numberOfPages}</Number>) : null
);

const renderPagination = (numberOfPages, selectedPage) => {
  const pagination = [];

  pagination.push(getFirstPaginationElement(selectedPage));
  pagination.push(getSecondPaginationElement(selectedPage, numberOfPages));
  pagination.push(getMiddlePaginationElement(selectedPage, numberOfPages));
  pagination.push(getPenultimatePaginationElement(selectedPage, numberOfPages));
  pagination.push(getLastPaginationElement(selectedPage, numberOfPages));

  return pagination;
};

const Pagination = ({
  numberOfPages,
  selectedPage,
  handlePage,
}) => (
  <Container>
    <ControlButton onClick={() => handlePage()}><KeyboardArrowLeftIcon fontSize="large" />
    </ControlButton>
    {renderPagination(numberOfPages, selectedPage)}
    <ControlButton onClick={() => handlePage(true)}>
      <KeyboardArrowRightIcon fontSize="large" />
    </ControlButton>
  </Container>
);

Pagination.propTypes = {
  numberOfPages: PropTypes.number.isRequired,
  selectedPage: PropTypes.number.isRequired,
  handlePage: PropTypes.func.isRequired,
};

export default Pagination;
