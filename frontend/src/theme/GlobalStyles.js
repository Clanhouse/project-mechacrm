import { createGlobalStyle } from 'styled-components';
import SwitzerRegularWoff from 'fonts/Switzer-Regular.woff';
import SwitzerRegularWoff2 from 'fonts/Switzer-Regular.woff2';
import SwitzerLightWoff from 'fonts/Switzer-Light.woff';
import SwitzerLightWoff2 from 'fonts/Switzer-Light.woff2';
import SwitzerBoldWoff from 'fonts/Switzer-Bold.woff';
import SwitzerBoldWoff2 from 'fonts/Switzer-Bold.woff2';
import SwitzerThinWoff from 'fonts/Switzer-Thin.woff';
import SwitzerThinWoff2 from 'fonts/Switzer-Thin.woff2';

const GlobalStyles = createGlobalStyle`
  @font-face {
    font-family: 'Switzer';
    src: url(${SwitzerThinWoff}) format('woff'),
    url(${SwitzerThinWoff2}) format('woff2');
    font-weight: 100;
  }

  @font-face {
    font-family: 'Switzer';
    src: url(${SwitzerLightWoff}) format('woff'),
    url(${SwitzerLightWoff2}) format('woff2');
    font-weight: 300;
  }
  
  @font-face {
    font-family: 'Switzer';
    src: url(${SwitzerRegularWoff}) format('woff'),
    url(${SwitzerRegularWoff2}) format('woff2');
    font-weight: 400;
  }

  @font-face {
    font-family: 'Switzer';
    src: url(${SwitzerBoldWoff}) format('woff'),
    url(${SwitzerBoldWoff2}) format('woff2');
    font-weight: 700;
  }

  *, *::before, *::after {
    box-sizing: border-box;
  }

  * {
    margin: 0;
    padding: 0;
  }

  html, body {
    font-family: 'Switzer', sans-serif;
    height: 100%;
  }

  body {
    -webkit-font-smoothing: antialiased;
    color: ${({ theme }) => theme.color.text.primary};
    background-color: ${({ theme }) => theme.color.background.primary};
  }

  img, picture, video, canvas, svg {
    display: block;
    max-width: 100%;
  }

  input, button, textarea, select {
    font: inherit;
  }

  p, h1, h2, h3, h4, h5, h6 {
    overflow-wrap: break-word;
  }

  #root, #__next {
    isolation: isolate;
  }
`;

export default GlobalStyles;
