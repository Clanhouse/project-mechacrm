import React, { useState } from 'react';
import SearchIcon from '@material-ui/icons/Search';
import theme from 'theme/MainTheme';
import QuickSearchInput from './QuickSearchInput';

export default {
  title: 'Molecules/QuickSearchInput',
  component: QuickSearchInput,
  argTypes: {
    placeholder: {
      defaultValue: 'Quick search ...',
    },
  },
};

export const QuickSearch = (args) => {
  const { placeholder } = args;

  const [input, setInput] = useState('');

  return (
    <div style={{ width: '50%' }}>
      <QuickSearchInput
        icon={<SearchIcon />}
        placeholder={placeholder}
        onChange={setInput}
        value={input}
        theme={theme}
      />
    </div>
  );
};
