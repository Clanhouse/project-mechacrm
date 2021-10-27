import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import Checkbox from './Checkbox';

export default {
  title: 'Atoms/Checkbox',
  component: Checkbox,
  argTypes: {
    labelText: {
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
  labelText: 'Unchecked',
  checked: false,
  disabled: false,
};

export const Checked = Template.bind({});
Checked.args = {
  labelText: 'Checked',
  checked: true,
  disabled: false,
};

export const UncheckedAndDisabled = Template.bind({});
UncheckedAndDisabled.args = {
  labelText: 'Unchecked',
  checked: false,
  disabled: true,
};

export const CheckedAndDisabled = Template.bind({});
CheckedAndDisabled.args = {
  labelText: 'Checked',
  checked: true,
  disabled: true,
};
