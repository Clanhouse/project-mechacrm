import React from 'react';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/MainTheme';
import LoginPageTemplate from './LoginPageTemplate';

export default {
  title: 'Template/LoginPage',
  component: LoginPageTemplate,
};

const Template = (args) => (
  <ThemeProvider theme={theme}>
    <LoginPageTemplate {...args} />
  </ThemeProvider>
);

export const LoginPage = Template.bind({});
LoginPage.parameters = {
  controls: { disable: true },
};

LoginPage.args = {
  slogan: <div style={{ display: 'block', width: '100%', height: '100%', backgroundColor: '#600' }} />,
  loginSection: <div style={{ display: 'block', width: '100%', height: '800px', backgroundColor: '#640' }} />,
};
