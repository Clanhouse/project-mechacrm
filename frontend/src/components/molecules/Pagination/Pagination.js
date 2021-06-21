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
