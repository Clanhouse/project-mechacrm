import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import Avatar from 'components/atoms/Avatar/Avatar';
import theme from 'theme/MainTheme';
import ArrowDropDownIcon from '@material-ui/icons/ArrowDropDown';

const Container = styled.div`
  margin: 10px;
  padding: 10px 20px;
  
  border-bottom: solid deepskyblue 1px;
  
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const AvatarBox = styled.div`
  width: 50px;
  height: 50px;
`;

const UserName = styled.div`
  margin-left: 20px;
  
  font-size: ${theme.fontSize.medium};
  font-weight: ${theme.fontWeight.bold};
  font-family: ${theme.fontFamily};
  color: ${theme.color.secondary};
`;

const ExpandButton = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  
  width: 20px;
  height: 20px;
  
  margin-left: 10px;
  padding: 10px;
  color: ${theme.color.black};
  border-radius: 5px;

  transition: all 200ms ease-in-out;
  
  :hover {
    color: ${theme.color.grey300};
    background-color: ${theme.color.grey100};
  }
`;

const LoggedUser = ({ icon, username }) => (
  <Container>
    <AvatarBox>
      <Avatar imageLink={icon} />
    </AvatarBox>
    <UserName>{username}</UserName>
    <ExpandButton>
      <ArrowDropDownIcon fontSize="large" />
    </ExpandButton>
  </Container>
);

LoggedUser.propTypes = {
  icon: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
};

export default LoggedUser;
