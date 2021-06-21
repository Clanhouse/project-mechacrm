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
  padding: 10px;

  border-radius: 5px;
  cursor: pointer;

  font-family: 'Roboto', sans-serif;
  font-weight: bold;
  font-size: 18px; //TODO

  display: flex;
  justify-content: center;
  align-items: center;

  background-color: ${({ active }) => (active ? '#0D47A1' : 'transparent')};
  color: ${({ active }) => (active ? 'white' : '#707070')};

  &:hover {
    background-color: ${({ disableHover }) => (disableHover ? null : 'lightgray')};
  }
`;

const ControlButton = styled.button`
  background-color: white; //TODO
  color: dimgray; //TODO

  border: none;
  border-radius: 5px;
  cursor: pointer;

  padding: 4px;
  margin: 8px;

  transition: background-color 200ms ease-in-out;

  &:hover {
    background-color: lightgray;
  }
`;

const getFirstPaginationElement = (selectedPage, handlePage) => (
  <Number
    key={1}
    active={selectedPage === 1}
    onClick={() => handlePage(1)}
  >
    1
  </Number>
);

const getSecondPaginationElement = (selectedPage, numberOfPages, handlePage) => {
  if (numberOfPages === 4 || numberOfPages === 5) {
    return (
      <Number
        key={2}
        active={selectedPage === 2}
        onClick={() => handlePage(2)}
      >
        2
      </Number>
    );
  }
  if (numberOfPages > 5) {
    if (selectedPage <= 3) {
      return (
        <Number
          key={2}
          active={selectedPage === 2}
          onClick={() => handlePage(2)}
        >
          2
        </Number>
      );
    }
    return <Number key="firstDots" disableHover>...</Number>;
  }
  return null;
};

const getMiddlePaginationElement = (selectedPage, numberOfPages, handlePage) => {
  if (numberOfPages === 3) {
    return (
      <Number
        key={2}
        active={selectedPage === 2}
        onClick={() => handlePage(2)}
      >
        2
      </Number>
    );
  }
  if (numberOfPages === 5) {
    return (
      <Number
        key={3}
        active={selectedPage === 3}
        onClick={() => handlePage(3)}
      >
        3
      </Number>
    );
  }

  if (numberOfPages > 5) {
    if (selectedPage <= 3) {
      return (
        <Number
          key={3}
          active={selectedPage === 3}
          onClick={() => handlePage(3)}
        >
          3
        </Number>
      );
    }
    if (selectedPage >= numberOfPages - 2) {
      return (
        <Number
          key={numberOfPages - 2}
          active={selectedPage < numberOfPages - 1}
          onClick={() => handlePage(numberOfPages - 2)}
        >
          {numberOfPages - 2}
        </Number>
      );
    }
    return (
      <Number
        key={selectedPage}
        active
        onClick={() => handlePage(selectedPage)}
      >
        {selectedPage}
      </Number>
    );
  }

  return null;
};

const getPenultimatePaginationElement = (selectedPage, numberOfPages, handlePage) => {
  if (numberOfPages === 4) {
    return (
      <Number
        key={3}
        active={selectedPage === 3}
        onClick={() => handlePage(3)}
      >
        3
      </Number>
    );
  }
  if (numberOfPages === 5) {
    return (
      <Number
        key={4}
        active={selectedPage === 4}
        onClick={() => handlePage(4)}
      >
        4
      </Number>
    );
  }

  if (numberOfPages > 5) {
    if (selectedPage >= numberOfPages - 2) {
      return (
        <Number
          key={numberOfPages - 1}
          active={selectedPage === numberOfPages - 1}
          onClick={() => handlePage(numberOfPages - 1)}
        >
          {numberOfPages - 1}
        </Number>
      );
    }
    return <Number key="lastDots" disableHover>...</Number>;
  }

  return null;
};

const getLastPaginationElement = (selectedPage, numberOfPages, handlePage) => (
  numberOfPages > 1 ? (
    <Number
      key={numberOfPages}
      active={selectedPage === numberOfPages}
      onClick={() => handlePage(numberOfPages)}
    >
      {numberOfPages}
    </Number>
  ) : null
);

const renderPagination = (numberOfPages, selectedPage, handlePage) => {
  const pagination = [];

  pagination.push(getFirstPaginationElement(selectedPage, handlePage));
  pagination.push(getSecondPaginationElement(selectedPage, numberOfPages, handlePage));
  pagination.push(getMiddlePaginationElement(selectedPage, numberOfPages, handlePage));
  pagination.push(getPenultimatePaginationElement(selectedPage, numberOfPages, handlePage));
  pagination.push(getLastPaginationElement(selectedPage, numberOfPages, handlePage));

  return pagination;
};

const Pagination = ({
  numberOfPages,
  selectedPage,
  handlePage,
}) => (
  <Container>
    <ControlButton
      onClick={() => handlePage(selectedPage - 1 < 1 ? 1 : selectedPage - 1)}
    >
      <KeyboardArrowLeftIcon
        fontSize="large"
      />
    </ControlButton>
    {renderPagination(numberOfPages, selectedPage, handlePage)}
    <ControlButton
      onClick={() => handlePage(
        selectedPage + 1 > numberOfPages ? numberOfPages : selectedPage + 1,
      )}
    >
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
