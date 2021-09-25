import React from 'react';
import Checkbox from './Checkbox';

export default {
  title: 'Atoms/Checkbox',
  component: Checkbox,
  argTypes: {
    LabelText: {
      control: 'text',
      defaultValue: 'Lorem ipsum',
    },
    checked: {
      conrol: 'inline-radio',
      defaultValue: false,
    },
  },
};

export const Default = (args) => <Checkbox {...args} />;
