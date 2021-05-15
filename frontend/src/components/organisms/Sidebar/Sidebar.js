import React from 'react';
import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import LogoImage from '../../../assets/logo.png';
import IconButton from '../../atoms/IconButton/IconButton';
import { ReactComponent as IconAlarm } from '../../../assets/svgs/alarm-light.svg';

const MenuWrapper = styled.div`
  display: grid;
  row-gap: 10px;
  justify-items: start;
`;

const LogoWrapper = styled.div`
  display: inline-grid;
  grid-template-columns: 250px auto;
  justify-items: center;
  align-items: center;
  width: 100%;
  height: 100px;
  margin-bottom: 15px;
`;

const SpaceLine = styled.hr`
  height: 1px;
  width: 100%;
  border: none;
  margin: 40px 0;
`;

const Wrapper = styled.nav`
  background-color: #FBFBFB;
  width: 250px;
  height: 100%;
  position: fixed;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-weight: bolder;
  text-transform: uppercase;
`;

const Logo = styled.div`
  width: 180px;
  height: 100%;
  background-image: url(${LogoImage});
  background-repeat: no-repeat;
  background-position: 50% 50%;
  background-size: 100%;
  justify-self: center;
  `;

const NavigationLink = styled(NavLink)`
  text-decoration: none;
`;

class Sidebar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <Wrapper>
        <LogoWrapper>
          <Logo />
        </LogoWrapper>
        <MenuWrapper>
          <SpaceLine />
          <NavigationLink exact to="dashboard">
            <IconButton label="Dashboard" icon={<IconAlarm />} />
          </NavigationLink>
          <NavigationLink to="/customers">
            <IconButton label="Customers" icon={<IconAlarm />} />
          </NavigationLink>
          <NavigationLink to="/cars">
            <IconButton label="Cars" icon={<IconAlarm />} />
          </NavigationLink>
          <SpaceLine />
          <NavigationLink to="/notifications">
            <IconButton label="Notifications" icon={<IconAlarm />} />
          </NavigationLink>
          <NavigationLink to="/profile">
            <IconButton label="Profile" icon={<IconAlarm />} />
          </NavigationLink>
          <SpaceLine />
          <NavigationLink to="/logout">
            <IconButton label="Logout" icon={<IconAlarm />} />
          </NavigationLink>
        </MenuWrapper>
      </Wrapper>
    );
  }
}

export default Sidebar;
