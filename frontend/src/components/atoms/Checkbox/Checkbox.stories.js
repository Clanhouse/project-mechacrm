import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import Checkbox from './Checkbox';

export default {
  title: 'Atoms/Checkbox',
  component: Checkbox,
};

const Template = (args) => (
  <ThemeProvider theme={theme}>
    <Checkbox {...args} />
  </ThemeProvider>
);

export const Unchecked = Template.bind({});
Unchecked.args = {
  label: 'Zapamiętaj',
  name: 'Unchecked',
  checked: false,
  disabled: false,
  fontSize: '18px',
};

export const Checked = Template.bind({});
Checked.args = {
  label: 'Przeczytałem',
  name: 'Checked',
  checked: true,
  disabled: false,
  fontSize: '18px',
};

export const UncheckedAndDisabled = Template.bind({});
UncheckedAndDisabled.args = {
  label: 'Opcja nie aktywna',
  name: 'Unchecked',
  checked: false,
  disabled: true,
  fontSize: '18px',
};

export const CheckedAndDisabled = Template.bind({});
CheckedAndDisabled.args = {
  label: 'Parametr testowy',
  name: 'Checked',
  checked: true,
  disabled: true,
  fontSize: '18px',
};
