import React from 'react';
import GlobalStyles from 'theme/GlobalStyles';
import { ThemeProvider } from 'styled-components';
import { Route, Routes } from 'react-router-dom';
import lightTheme from '../theme/lightTheme';
import MainPage from './MainPage';
import NotFoundPage from './NotFoundPage';

function App() {
  return (
    <ThemeProvider theme={lightTheme}>
      <GlobalStyles />
      <Routes>
        <Route path='/' element={<MainPage />} />
        <Route path='*' element={<NotFoundPage />} />
      </Routes>
    </ThemeProvider>
  );
}

export default App;
