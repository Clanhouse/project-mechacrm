import React from 'react';
import { ThemeProvider } from 'styled-components';
import eyeIcon from 'assets/svgs/eye-solid.svg';
import phoneIcon from 'assets/svgs/phone-solid.svg';
import envelopeIcon from 'assets/svgs/envelope-solid.svg';
import theme from 'theme/MainTheme';
import InputField from './InputField';

export default {
  title: 'Atoms/InputField',
  component: InputField,
  argTypes: {
    variant: {
      options: ['text', 'password'],
      control: 'inline-radio',
    },
    fontSize: {
      control: 'text',
    },
    color: {
      control: 'color',
    },
    backgroundColor: {
      control: 'color',
    },
    borderColor: {
      control: 'color',
    },
    placeholderColor: {
      control: 'color',
    },
    icon: {
      control: 'none',
    },
    isError: {
      control: 'boolean',
    },
    errorMessage: {
      control: 'text',
    },
    value: {
      control: 'text',
    },
  },
};

const Template = (args) => (
  <ThemeProvider theme={theme}>
    <div style={{ width: '80%' }}>
      <InputField {...args} />
    </div>
  </ThemeProvider>
);

export const Login = Template.bind({});
Login.args = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Nazwa użytkownika/email',
  placeholder: 'Wpisz nazwę użytkownika/email',
  isError: false,
  errorMessage: '',
  value: '',
};

export const LoginError = Template.bind({});
LoginError.args = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Nazwa użytkownika/email',
  placeholder: 'Wpisz nazwę użytkownika/email',
  isError: true,
  errorMessage: 'Podana nazwa użytkownika/ email jest nieprawidłowy',
  value: '',
};

export const Password = Template.bind({});
Password.args = {
  variant: 'password',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Hasło',
  placeholder: 'Wpisz hasło',
  icon: eyeIcon,
  isError: false,
  errorMessage: '',
  value: '',
};

export const Phone = Template.bind({});
Phone.args = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Numer telefonu',
  placeholder: '+48  _ _ _  _ _ _  _ _ _',
  icon: phoneIcon,
  isError: false,
  errorMessage: '',
  value: '',
};

export const Email = Template.bind({});
Email.args = {
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Email',
  placeholder: 'Wpisz Email',
  icon: envelopeIcon,
  isError: false,
  errorMessage: '',
  value: '',
};