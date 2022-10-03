import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import {
  FaEnvelope, FaEye, FaPhone,
} from 'react-icons/fa';
import InputField from './InputField';

export default {
  title: 'Atoms/InputField',
  component: InputField,
  argTypes: {
    variant: {
      options: ['text', 'password'],
      control: 'inline-radio',
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
    Icon: {
      control: 'none',
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
  name: 'login',
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Nazwa użytkownika/email',
  placeholder: 'Wpisz nazwę użytkownika/email',
  errorMessage: '',
  value: '',
};

export const LoginError = Template.bind({});
LoginError.args = {
  name: 'login',
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Nazwa użytkownika/email',
  placeholder: 'Wpisz nazwę użytkownika/email',
  errorMessage: 'Podana nazwa użytkownika/ email jest nieprawidłowy',
  value: '',
};

export const Password = Template.bind({});
Password.args = {
  name: 'password',
  variant: 'password',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Hasło',
  placeholder: 'Wpisz hasło',
  Icon: FaEye,
  errorMessage: '',
  value: '',
};

export const Phone = Template.bind({});
Phone.args = {
  name: 'phone',
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Numer telefonu',
  placeholder: '+48  _ _ _  _ _ _  _ _ _',
  Icon: FaPhone,
  errorMessage: '',
  value: '',
};

export const Email = Template.bind({});
Email.args = {
  name: 'email',
  variant: 'text',
  fontSize: '18px',
  color: '#04294F',
  backgroundColor: '#fff',
  borderColor: '#3F3D56',
  placeholderColor: '#979797',
  label: 'Email',
  placeholder: 'Wpisz Email',
  Icon: FaEnvelope,
  errorMessage: '',
  value: '',
};
