import React from 'react';
import { ThemeProvider } from 'styled-components';
import GlobalStyles from './theme/GlobalStyles';

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
