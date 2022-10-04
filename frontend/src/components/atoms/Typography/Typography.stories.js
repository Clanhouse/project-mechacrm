import React from 'react';
import Typography from './Typography';

export default {
  title: 'Atoms/Typography',
  component: Typography,
  argTypes: {
    variant: {
      control: 'inline-radio',
    },
    align: {
      control: 'inline-radio',
    },
  },
};

const Template = ({ children, ...args }) => (
  <Typography {...args}>{children}</Typography>
);

export const Heading = Template.bind({});
Heading.args = {
  variant: 'h1',
  children: 'Typography',
  align: 'inherit',
};
