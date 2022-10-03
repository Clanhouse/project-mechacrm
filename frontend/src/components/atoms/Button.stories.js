import React from 'react';
import Button from './Button';

export default {
  title: 'Atoms/Button',
  component: Button,
  argTypes: {
    variant: {
      control: 'inline-radio',
    },
  },
};

const Template = (args) => <Button {...args} />;

export const RegularButton = Template.bind({});
RegularButton.args = {
  color: 'primary',
  children: 'Button',
  variant: 'normal',
};

export const RegularButtonWithIcon = Template.bind({});
RegularButtonWithIcon.args = {
  color: 'primary',
  children: 'Button',
  icon: 'Button',
  variant: 'normal',
};

export const IconButton = Template.bind({});
IconButton.args = {
  color: 'primary',
  icon: 'Button',
  variant: 'normal',
};
