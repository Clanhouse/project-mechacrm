import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import Typography from './Typography';

export default {
  title: 'Atoms/Typography',
  component: Typography,
  argTypes: {
    variant: {
      options: ['text', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6'],
      control: 'inline-radio',
    },
    fontSize: {
      control: 'text',
    },
    color: {
      control: 'color',
    },
    align: {
      options: ['center', 'inherit', 'justify', 'left', 'right'],
      control: 'inline-radio',
    },
  },
};

const Template = (args) => (
  <ThemeProvider theme={theme}>
    <Typography {...args} />
  </ThemeProvider>
);

export const HeaderH1 = Template.bind({});
HeaderH1.args = {
  children: 'Kontrola biznesu w zasięgu ręki',
  variant: 'h1',
  fontSize: '54px',
  color: theme.color.secondary,
  mt: 0,
  ml: 0,
  align: 'left',
};

export const HeaderH2 = Template.bind({});
HeaderH2.args = {
  children: 'Kontrola biznesu w zasięgu ręki',
  variant: 'h2',
  fontSize: '42px',
  color: theme.color.secondary,
  mt: 0,
  ml: 0,
  align: 'left',
};

export const HeaderH3 = Template.bind({});
HeaderH3.args = {
  children: 'Kontrola biznesu w zasięgu ręki',
  variant: 'h3',
  fontSize: '36px',
  color: theme.color.secondary,
  mt: 0,
  ml: 0,
  align: 'left',
};

export const HeaderH4 = Template.bind({});
HeaderH4.args = {
  children: 'Kontrola biznesu w zasięgu ręki',
  variant: 'h4',
  fontSize: '30px',
  color: theme.color.secondary,
  mt: 0,
  ml: 0,
  align: 'left',
};

export const HeaderH5 = Template.bind({});
HeaderH5.args = {
  children: 'Kontrola biznesu w zasięgu ręki',
  variant: 'h5',
  fontSize: '24px',
  color: theme.color.secondary,
  mt: 0,
  ml: 0,
  align: 'left',
};

export const HeaderH6 = Template.bind({});
HeaderH6.args = {
  children: 'Kontrola biznesu w zasięgu ręki',
  variant: 'h6',
  fontSize: '18px',
  color: theme.color.secondary,
  mt: 0,
  ml: 0,
  align: 'left',
};

export const Text = Template.bind({});
Text.args = {
  children:
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
  variant: 'text',
  fontSize: '18px',
  color: theme.color.secondary,
  mt: 0,
  ml: 0,
  align: 'left',
};
