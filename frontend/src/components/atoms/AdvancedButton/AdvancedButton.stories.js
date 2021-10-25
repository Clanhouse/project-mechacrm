import React from 'react';
import { ReactComponent as GoogleIcon } from 'assets/svgs/google-brands.svg';
import { ReactComponent as FacebookIcon } from 'assets/svgs/facebook-f-brands.svg';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import AdvancedButton from './AdvancedButton';

export default {
  title: 'Atoms/AdvancedButton',
  component: AdvancedButton,
  argTypes: {
    icon: {
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
};

export const Google = Template.bind({});
Google.args = {
  icon: <GoogleIcon />,
  text: 'Zaloguj się przez Google',
  fullWidth: true,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked google button'),
};

export const Facebook = Template.bind({});
Facebook.args = {
  icon: <FacebookIcon />,
  text: 'Zaloguj się przez Facebook',
  fullWidth: false,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
  // eslint-disable-next-line no-console
  onClick: () => console.log('clicked facebook button'),
};