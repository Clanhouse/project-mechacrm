import React from 'react';
import { BsCheckLg } from 'react-icons/all';
import Button from 'components/atoms/Button/Button';

export default {
  title: 'Atoms/Button',
  component: Button,
  argTypes: {
    variant: {
      control: 'inline-radio',
    },
    size: {
      control: 'inline-radio',
    },
  },
};

const Template = (args) => <Button {...args} />;

export const TextButton = Template.bind({});
TextButton.args = {
  color: 'primary',
  children: 'Button',
  variant: 'contained',
  size: 'normal',
};

export const TextButtonWithIcon = Template.bind({});
TextButtonWithIcon.args = {
  color: 'primary',
  children: 'Button',
  Icon: BsCheckLg,
  variant: 'contained',
  size: 'normal',
};
