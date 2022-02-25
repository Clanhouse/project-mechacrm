import React from 'react';
import theme from 'theme/MainTheme';
import { ThemeProvider } from 'styled-components';
import PropTypes from 'prop-types';
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
    fullWidth: {
      control: 'boolean',
    },
    onClick: {
      action: 'large button clicked',
    },
  },
};

const Template = ({ children, ...args }) => (
  <ThemeProvider theme={theme}>
    <LargeButton {...args}>{children}</LargeButton>
  </ThemeProvider>
);

export const LoginButton = Template.bind({});
LoginButton.args = {
  type: 'button',
  children: 'Zaloguj',
  color: '#04294F',
  background: '#ffb400',
  fontSize: '42px',
  fullWidth: false,
};

export const RegisterButton = Template.bind({});
RegisterButton.args = {
  type: 'submit',
  children: 'Zarejestruj się',
  color: '#04294F',
  background: '#ffb400',
  fontSize: '42px',
  fullWidth: true,
};

Template.propTypes = {
  children: PropTypes.string.isRequired,
};
