import React from 'react';
import { ThemeProvider } from 'styled-components';
import {
  BrowserRouter, Routes, Route,
} from 'react-router-dom';
import LoginPage from 'pages/LoginPage';
import NotFoundPage from 'pages/NotFoundPage';
import GlobalStyles from 'theme/GlobalStyles';
import theme from 'theme/MainTheme';
import RegisterPage from 'pages/RegisterPage';
import HomePage from 'pages/HomePage';
import Dashboard from 'pages/Dashboard';

const App = () => (
  <BrowserRouter>
    <GlobalStyles />
    <ThemeProvider theme={theme}>
      <Routes>
        <Route path="/" exact element={<HomePage />} />
        <Route path="/login" exact element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </ThemeProvider>
  </BrowserRouter>
);

export default App;
