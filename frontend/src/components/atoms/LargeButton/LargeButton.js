import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  
  width: ${({ fullWidth }) => fullWidth && '100%'};
  height: 72px;

  border-radius: 25px;
  border: none;

  background: ${({ background }) => background};

  transition: all 0.2s ease-in-out;

  &:hover {
    background: ${({ background }) => `linear-gradient(0deg, rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), ${background}`};

    & > span {
      color: white;
    }
  }
`;

const Label = styled.span`
  padding: 12px 16px;

  font-family: ${({ theme }) => theme.fontFamily};
  font-weight: ${({ theme }) => theme.fontWeight.regular};
  font-size: ${({ fontSize }) => fontSize};

  letter-spacing: 0.014em;

  color: ${({ color }) => color};
  transition: all 0.2s ease-in-out;
`;

const LargeButton = ({
  text,
  background,
  color,
  fontSize,
  fullWidth,
  onClick,
}) => (
  <Container background={background} fullWidth={fullWidth} onClick={onClick}>
    <Label color={color} fontSize={fontSize}>{text}</Label>
  </Container>
);

LargeButton.propTypes = {
  text: PropTypes.string.isRequired,
  background: PropTypes.string,
  color: PropTypes.string,
  fontSize: PropTypes.string,
  fullWidth: PropTypes.bool,
  onClick: PropTypes.func.isRequired,
};

LargeButton.defaultProps = {
  background: '#fff',
  color: '#000',
  fullWidth: false,
  fontSize: '42px',
};

export default LargeButton;
