import React from 'react';
import RegisterPageTemplate from 'components/templates/RegisterPageTemplate/RegisterPageTemplate';

export default {
  title: 'Template/RegisterPage',
  component: RegisterPageTemplate,
};

const Template = (args) => <RegisterPageTemplate {...args} />;

export const RegisterPage = Template.bind({});

RegisterPage.args = {
  formSection: (<div style={{ width: '100%', height: '70%', backgroundColor: 'green' }} />),
};

RegisterPage.parameters = {
  controls: { disable: true },
};
