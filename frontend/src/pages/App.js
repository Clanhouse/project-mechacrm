import React from 'react';
import GlobalStyles from 'theme/GlobalStyles';
import { ThemeProvider } from 'styled-components';
import lightTheme from '../theme/lightTheme';

function App() {
  return (
    <ThemeProvider theme={lightTheme}>
      <GlobalStyles />
      <div>Motomo</div>
    </ThemeProvider>
  );
}

export default App;
