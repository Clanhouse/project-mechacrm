import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import Checkbox from './Checkbox';

export default {
  title: 'Atoms/Checkbox',
  component: Checkbox,
  argTypes: {
    label: {
      control: 'text',
    },
  },
};

const Template = (args) => (
  <ThemeProvider theme={theme}>
    <Checkbox {...args} />
  </ThemeProvider>
);

export const Unchecked = Template.bind({});
Unchecked.args = {
  label: 'Unchecked',
  name: 'Unchecked',
  checked: false,
  disabled: false,
  mt: 0,
  ml: 0,
  fontSize: '18px',
};

export const Checked = Template.bind({});
Checked.args = {
  label: 'Checked',
  name: 'Checked',
  checked: true,
  disabled: false,
  mt: 0,
  ml: 0,
  fontSize: '18px',
};

export const UncheckedAndDisabled = Template.bind({});
UncheckedAndDisabled.args = {
  label: 'Unchecked',
  name: 'Unchecked',
  checked: false,
  disabled: true,
  mt: 0,
  ml: 0,
  fontSize: '18px',
};

export const CheckedAndDisabled = Template.bind({});
CheckedAndDisabled.args = {
  label: 'Checked',
  name: 'Checked',
  checked: true,
  disabled: true,
  mt: 0,
  ml: 0,
  fontSize: '18px',
};
