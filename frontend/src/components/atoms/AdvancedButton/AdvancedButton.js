import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.button`
  padding: 0 16px;
  display: flex;
  justify-content: center;
  width: ${({ fullWidth }) => fullWidth && '100%'};
  border-radius: 25px;
  border: ${({ borderColor }) => borderColor && `1px solid ${borderColor}`};
  background-color: ${({ background }) => background};
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.35);
  transition: all 0.3s ease-in-out;

  &:hover {
    background-color: #f4f0f0;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
    border: 1px solid transparent;
  }
`;

const IconBox = styled.div`
  display: flex;
  justify-content: flex-end;
  align-self: center;
  margin-right: 15px;
  width: 24px;
  height: 24px;
  color: ${({ color }) => color};
`;

const LabelBox = styled.div`
  padding: 18px 0;
  display: flex;
  justify-content: flex-start;
  align-self: center;
  margin-left: ${({ icon }) => icon && '15px'};
  font-family: ${({ theme }) => theme.fontFamily};
  font-weight: ${({ theme }) => theme.fontWeight.regular};
  font-size: ${({ fontSize }) => fontSize};
  letter-spacing: 0.014em;
  line-height: 20px;
  color: ${({ color }) => color};
`;

const AdvancedButton = ({
  Icon,
  fontSize,
  fullWidth,
  borderColor,
  background,
  color,
  onClick,
  children,
}) => (
  <Container
    fullWidth={fullWidth}
    borderColor={borderColor}
    background={background}
    onClick={onClick}
  >
    {Icon && (
      <IconBox color={color}>
        <Icon size={24} />
      </IconBox>
    )}
    <LabelBox fontSize={fontSize} color={color} icon={Icon}>
      {children}
    </LabelBox>
  </Container>
);

AdvancedButton.propTypes = {
  Icon: PropTypes.elementType,
  children: PropTypes.string.isRequired,
  fontSize: PropTypes.string,
  fullWidth: PropTypes.bool,
  borderColor: PropTypes.string,
  background: PropTypes.string,
  color: PropTypes.string,
  onClick: PropTypes.func,
};

AdvancedButton.defaultProps = {
  Icon: undefined,
  fontSize: '18px',
  fullWidth: false,
  borderColor: '#000',
  color: '#04294F',
  background: '#FFF',
  onClick: undefined,
};

export default AdvancedButton;
