import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  border-radius: 50%;
  width: 100%;
  height: 100%;

  background-image: ${({ imageLink }) => `url(${imageLink})`};
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  
  overflow: hidden;
`;

function Avatar({ imageLink }) {
  return (
    <Container imageLink={imageLink} />
  );
}

Avatar.propTypes = {
  imageLink: PropTypes.string.isRequired,
};

Avatar.defaultProps = {};

export default Avatar;
