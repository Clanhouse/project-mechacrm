import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import { FaFacebookF, FaGoogle } from 'react-icons/fa';
import PropTypes from 'prop-types';
import AdvancedButton from './AdvancedButton';

export default {
  title: 'Atoms/AdvancedButton',
  component: AdvancedButton,
  argTypes: {
    Icon: {
      control: 'hidden',
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
    onClick: {
      action: 'advanced button clicked',
    },
  },
};

const Template = ({ children, ...args }) => (
  <ThemeProvider theme={theme}>
    <AdvancedButton {...args}>{children}</AdvancedButton>
  </ThemeProvider>
);

export const TextButton = Template.bind({});
TextButton.args = {
  children: 'Zaloguj się przez email',
  fullWidth: false,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
};

export const Google = Template.bind({});
Google.args = {
  Icon: FaGoogle,
  children: 'Zaloguj się przez Google',
  fullWidth: true,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
};

export const Facebook = Template.bind({});
Facebook.args = {
  Icon: FaFacebookF,
  children: 'Zaloguj się przez Facebook',
  fullWidth: false,
  fontSize: '18px',
  borderColor: '#000',
  color: '#04294F',
  background: '#fff',
};

Template.propTypes = {
  children: PropTypes.string.isRequired,
};
