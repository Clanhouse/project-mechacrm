import React from 'react';
import theme from 'theme/MainTheme';
import { ThemeProvider } from 'styled-components';
import LargeButton from './LargeButton';

export default {
  title: 'Atoms/LargeButton',
  component: LargeButton,
  argTypes: {
    text: {
      control: 'text',
    },
    background: {
      control: 'color',
    },
    color: {
      control: 'color',
    },
    fontSize: {
      control: 'text',
    },
    fullWidth: {
      control: 'boolean',
    },
  },
};

const Template = (args) => <ThemeProvider theme={theme}><LargeButton {...args} /></ThemeProvider>;

export const LoginButton = Template.bind({});
LoginButton.args = {
  text: 'Zaloguj',
  color: '#04294F',
  background: '#ffb400',
  fontSize: '42px',
  fullWidth: false,
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked'),
};

export const RegisterButton = Template.bind({});
RegisterButton.args = {
  text: 'Zarejestruj się',
  color: '#04294F',
  background: '#ffb400',
  fontSize: '42px',
  fullWidth: true,
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked'),
};
