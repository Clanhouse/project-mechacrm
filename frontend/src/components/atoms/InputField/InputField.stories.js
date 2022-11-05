import React from 'react';
import InputField from 'components/atoms/InputField/InputField';

export default {
  title: 'Atoms/InputField',
  component: InputField,
  argTypes: {
    type: {
      control: 'inline-radio',
    },
  },
};

const Template = (args) => <InputField {...args} />;
const TemplateBox = (args) => (
  <div style={{ width: '250px' }}>
    <InputField {...args} />
  </div>
);

export const InactiveInput = Template.bind({});
export const EmailInput = Template.bind({});
export const ErrorInput = Template.bind({});
export const InBoxInput = TemplateBox.bind({});

InactiveInput.args = {
  id: 'login',
  label: 'Login',
  error: false,
  type: 'text',
};

EmailInput.args = {
  id: 'email',
  label: 'Email',
  error: false,
  value: 'example@example.com',
  type: 'text',
};

ErrorInput.args = {
  id: 'password',
  label: 'Password',
  error: true,
  value: 'password',
  type: 'password',
};

InBoxInput.args = {
  id: 'firstname',
  label: 'Firstname',
  error: false,
  value: 'Amanda',
  type: 'text',
};
