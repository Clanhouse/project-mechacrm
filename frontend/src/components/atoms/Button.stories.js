import React from 'react';
import { BsCheckLg, HiTrash } from 'react-icons/all';
import Button from './Button';

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

export const RegularButton = Template.bind({});
RegularButton.args = {
  color: 'primary',
  children: 'Button',
  variant: 'contained',
  size: 'normal',
};

export const RegularButtonWithIcon = Template.bind({});
RegularButtonWithIcon.args = {
  color: 'primary',
  children: 'Button',
  Icon: BsCheckLg,
  variant: 'contained',
  size: 'normal',
};

export const IconButton = Template.bind({});
IconButton.args = {
  color: 'primary',
  Icon: HiTrash,
  variant: 'contained',
  size: 'normal',
};
