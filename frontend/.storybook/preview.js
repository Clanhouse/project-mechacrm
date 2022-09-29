import GlobalStyles from '../src/theme/GlobalStyles';
import lightTheme from '../src/theme/lightTheme';
import { ThemeProvider } from 'styled-components';

export const parameters = {
  actions: {argTypesRegex: '^on[A-Z].*'},
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};

export const decorators = [
  (Story) => (
    <ThemeProvider theme={lightTheme}>
      <GlobalStyles/>
      <Story/>
    </ThemeProvider>
  ),
];
