import React from 'react';
import { ThemeProvider } from 'styled-components';
import {
  BrowserRouter, Route, Switch,
} from 'react-router-dom';
import LoginPage from 'pages/LoginPage';
import HomePage from 'pages/HomePage';
import NotFoundPage from 'pages/NotFoundPage';
import GlobalStyles from './theme/GlobalStyles';

import theme from './theme/MainTheme';

const App = () => (
  <BrowserRouter>
    <GlobalStyles />
    <ThemeProvider theme={theme}>
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/login" exact component={LoginPage} />
        <Route component={NotFoundPage} />
      </Switch>
    </ThemeProvider>
  </BrowserRouter>
);

export default App;
