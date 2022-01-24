import React from 'react';
import theme from 'theme/MainTheme';
import { ThemeProvider } from 'styled-components';
import LargeButton from './LargeButton';

export default {
  title: 'Atoms/LargeButton',
  component: LargeButton,
  argTypes: {
    type: {
      options: ['button', 'submit'],
      control: 'inline-radio',
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
  type: 'button',
  children: 'Zaloguj',
  color: '#04294F',
  background: '#ffb400',
  fontSize: '42px',
  fullWidth: false,
  mt: 0,
  ml: 0,
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked'),
};

export const RegisterButton = Template.bind({});
RegisterButton.args = {
  type: 'submit',
  children: 'Zarejestruj się',
  color: '#04294F',
  background: '#ffb400',
  fontSize: '42px',
  fullWidth: true,
  mt: 0,
  ml: 0,
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked'),
};
