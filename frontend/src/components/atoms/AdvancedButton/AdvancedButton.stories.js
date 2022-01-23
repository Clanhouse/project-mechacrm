import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import {
  FaFacebookF, FaGoogle,
} from 'react-icons/fa';
import AdvancedButton from './AdvancedButton';

export default {
  title: 'Atoms/AdvancedButton',
  component: AdvancedButton,
  argTypes: {
    Icon: {
      control: 'hidden',
    },
    fontSize: {
      control: 'text',
    },
    fullWidth: {
      control: 'boolean',
    },
    borderColor: {
      control: 'color',
    },
    background: {
      control: 'color',
    },
    color: {
      control: 'color',
    },
  },
};

const Template = (args) => (
  <ThemeProvider theme={theme}>
    <AdvancedButton {...args} />
  </ThemeProvider>
);

export const TextButton = Template.bind({});
TextButton.args = {
  text: 'Zaloguj się przez email',
  fullWidth: false,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked text button'),
  mt: 0,
  ml: 0,
};

export const Google = Template.bind({});
Google.args = {
  Icon: FaGoogle,
  text: 'Zaloguj się przez Google',
  fullWidth: true,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked google button'),
  mt: 0,
  ml: 0,
};

export const Facebook = Template.bind({});
Facebook.args = {
  Icon: FaFacebookF,
  text: 'Zaloguj się przez Facebook',
  fullWidth: false,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked facebook button'),
  mt: 0,
  ml: 0,
};
