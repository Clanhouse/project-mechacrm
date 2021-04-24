import React from 'react';
import GlobalStyles from './theme/GlobalStyles';
import { ThemeProvider } from 'styled-components';

import theme from './theme/MainTheme';

const App = () => (
  <>
    <GlobalStyles />
    <ThemeProvider theme={theme}>
      <div>Hello world!</div>
    </ThemeProvider>
  </>
);

export default App;
