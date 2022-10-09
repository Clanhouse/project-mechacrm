import React from 'react';
import { AiOutlineLock, FcGoogle, AiOutlineMenu } from 'react-icons/all';
import IconButton from 'components/atoms/IconButton/IconButton';

export default {
  title: 'Atoms/IconButton',
  component: IconButton,
  argTypes: {
    variant: {
      control: 'inline-radio',
    },
    size: {
      control: 'inline-radio',
    },
  },
};

const Template = (args) => <IconButton {...args} />;

export const PlusButton = Template.bind({});
PlusButton.args = {
  color: 'primary',
  children: 'Button',
  Icon: AiOutlineLock,
  variant: 'contained',
  size: 'normal',
  inactive: false,
};

export const GoogleButton = Template.bind({});
GoogleButton.args = {
  color: 'primary',
  children: 'Button',
  Icon: FcGoogle,
  variant: 'outlined',
  size: 'normal',
  inactive: false,
};

export const MenuButton = Template.bind({});
MenuButton.args = {
  color: 'primary',
  children: 'Button',
  Icon: AiOutlineMenu,
  variant: 'text',
  size: 'normal',
  inactive: false,
};
