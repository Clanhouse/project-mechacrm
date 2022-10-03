import React from 'react';
import LoginPageTemplate from './LoginPageTemplate';

export default {
  title: 'Template/LoginPage',
  component: LoginPageTemplate,
};

const Template = (args) => <LoginPageTemplate {...args} />;

export const LoginPage = Template.bind({});
LoginPage.parameters = {
  controls: { disable: true },
};

LoginPage.args = {
  loginSection: (
    <div style={{ display: 'block', width: '100%', height: '800px', backgroundColor: '#640' }} />
  ),
};
