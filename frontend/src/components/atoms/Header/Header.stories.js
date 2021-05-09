import React from 'react';
import Header from './Header';
import theme from '../../../theme/MainTheme';

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
