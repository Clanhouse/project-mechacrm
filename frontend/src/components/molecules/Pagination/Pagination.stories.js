import React, { useState } from 'react';
import Pagination from './Pagination';

export default {
  title: 'Organisms/Pagination',
  component: Pagination,
  argTypes: {
    numberOfPages: {
      defaultValue: 13,
    },
  },
};

export const PaginationStory = (args) => {
  const { numberOfPages } = args;

  const [page, setPage] = useState(1);

  const handlePage = (newPageNumber) => {
    setPage(newPageNumber);
  };

  return (<Pagination numberOfPages={numberOfPages} selectedPage={page} handlePage={handlePage} />);
};
