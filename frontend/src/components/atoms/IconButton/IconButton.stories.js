import React from 'react';
import IconButton from './IconButton';
import { ReactComponent as IconAlarm } from '../../../assets/svgs/alarm-light.svg';
import { ReactComponent as IconCalendar } from '../../../assets/svgs/calendar-range.svg';

export default {
  title: 'Atoms/IconButton',
  component: IconButton,
};

const Template = (args) => <IconButton {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  color: 'primary',
  label: 'icon button',
  icon: <IconAlarm />,
};

export const Secondary = Template.bind({});
Secondary.args = {
  color: 'secondary',
  label: 'icon button',
  icon: <IconCalendar />,
};
