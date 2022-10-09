import React from 'react';
import InputField from 'components/atoms/InputField/InputField';

export default {
  title: 'Atoms/InputField',
  component: InputField,
};

const Template = (args) => <InputField {...args} />;

export const EmptyInput = Template.bind({});

EmptyInput.args = {};
