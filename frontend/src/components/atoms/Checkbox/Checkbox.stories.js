import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import Checkbox from './Checkbox';

export default {
  title: 'Atoms/Checkbox',
  component: Checkbox,
  argTypes: {
    labeltext: {
      control: 'text',
    },
    variant: {
      control: 'inline-radio',
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
  labeltext: 'Unchecked',
  checked: false,
  disabled: false,
};

export const Checked = Template.bind({});
Checked.args = {
  labeltext: 'Checked',
  checked: true,
  disabled: false,
};

export const UncheckedAndDisabled = Template.bind({});
UncheckedAndDisabled.args = {
  labeltext: 'Unchecked',
  checked: false,
  disabled: false,
};

export const CheckedAndDisabled = Template.bind({});
CheckedAndDisabled.args = {
  labeltext: 'Checked',
  checked: true,
  disabled: false,
};
