import React from 'react';
import theme from 'theme/MainTheme';
import Header from './Header';

export default {
  title: 'Atoms/Header',
  component: Header,
  argTypes: {
    title: {
      control: 'text',
      defaultValue: 'Header Table',
    },
  },
};

export const HeaderStory = (args) => <Header {...args} theme={theme} />;
