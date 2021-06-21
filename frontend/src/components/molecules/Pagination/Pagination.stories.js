import React, { useState } from 'react';
import Pagination from './Pagination';

export default {
  title: 'Organisms/Pagination',
  component: Pagination,
  argTypes: {
    numberOfPages: {
      defaultValue: 3,
    },
  },
};

export const PaginationStory = (args) => {
  const { count } = args;

  const [page, setPage] = useState(1);

  const handlePage = (right) => {
    if (right) {
      const p = page + 1;
      return p > count ? setPage(count) : setPage(p);
    }
    const p = page - 1;
    return p < 1 ? setPage(1) : setPage(p);
  };

  return (<Pagination numberOfPages={numberOfPages} selectedPage={page} handlePage={handlePage} />);
};
