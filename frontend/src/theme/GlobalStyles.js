import { createGlobalStyle } from 'styled-components';

const GlobalStyles = createGlobalStyle`
  *, *::before, *::after {
    box-sizing: border-box;
  }
  * {
    margin: 0;
    padding: 0;
  }
  html, body {
    font-family: 'Montserrat', sans-serif;
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
