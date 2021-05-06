import React from 'react';
import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import LogoImage from '../../../assets/logo.png';
import IconButton from '../../atoms/IconButton/IconButton';
import { ReactComponent as IconAlarm } from '../../../assets/svgs/alarm-light.svg';
import theme from '../../../theme/MainTheme';

const MenuWrapper = styled.div`
  display: grid;
  row-gap: 10px;
  justify-items: start;
`;

const LogoWrapper = styled.div`
  display: inline-grid;
  grid-template-columns: 50px auto;
  justify-items: center;
  align-items: center;
  width: 100%;
  height: 100px;
  margin-bottom: 15px;
`;
const SpaceLine = styled.hr`
  height: 1px;
  width: 100%;
  background-color: ${theme.grey400};
  border: none;
  margin: 40px 0;
`;

const Wrapper = styled.nav`
width: 250px;
height: 100%;
position: fixed;
display: flex;
flex-direction: column;
border: ${theme.color.primary} 1px solid;
align-items: center;
font-weight: bolder;
text-transform: uppercase;
`;
const Logo = styled.div`
  width: 50px;
  height: 50px;
  background-image: url(${LogoImage});
  background-repeat: no-repeat;
  background-position: 50% 50%;
  background-size: 100%;
  `;
const NavigationLink = styled(NavLink)`
  text-decoration: none;
`;

// eslint-disable-next-line react/prefer-stateless-function
class Sidebar extends React.Component {
  render() {
    return (
      <Wrapper>
        <LogoWrapper>
          <Logo />
        </LogoWrapper>
        <MenuWrapper>
          <NavigationLink exact to="/">
            <IconButton label="dupa" icon={<IconAlarm />} />
          </NavigationLink>
          <NavigationLink to="/link">
            <IconButton label="dupa1" icon={<IconAlarm />} />
          </NavigationLink>
          <SpaceLine />
        </MenuWrapper>
      </Wrapper>
    );
  }
}

export default Sidebar;
