import React, { useState } from 'react';
import styled from 'styled-components';
import LoginPageTemplate from 'components/templates/LoginPageTemplate/LoginPageTemplate';
import Typography from 'components/atoms/Typography/Typography';
import AdvancedButton from 'components/atoms/AdvancedButton/AdvancedButton';
import InputField from 'components/atoms/InputField/InputField';
import { ReactComponent as GoogleIcon } from 'assets/svgs/google-brands.svg';
import eyeIcon from 'assets/svgs/eye-solid.svg';
import Checkbox from 'components/atoms/Checkbox/Checkbox';
import LargeButton from 'components/atoms/LargeButton/LargeButton';
import { Link } from 'react-router-dom';

const Wrapper = styled.div`
  margin: 178px 0 0 20px;
`;

const Divider = styled.div`
  display: flex;
  align-items: center;
`;

const Line = styled.span`
  width: 231px;
  height: 1px;
  background-color: #979797;
`;

const LoginPage = () => {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);

  const handleSubmit = () => {
    setLogin('');
    setPassword('');
    setRememberMe(false);
  };

  return (
    <LoginPageTemplate
      slogan={(
        <>
          <div style={{ margin: '52px 0 0 0' }}>
            <Typography
              variant="h2"
              text="Kontrola biznesu w zasięgu ręki"
              fontSize="38px"
            />
          </div>
          <div style={{ margin: '52px 0 0 0' }}>
            <Typography
              variant="text"
              text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
              fontSize="24px"
            />
          </div>
        </>
      )}
      loginSection={(
        <Wrapper>
          <Typography
            variant="h2"
            text="Witaj w Motomo"
            fontSize="42px"
          />

          <div style={{ margin: '25px 0 0 10px' }}>
            <Typography
              variant="text"
              text="Zaloguj się podając informacje poniżej"
              fontSize="18px"
            />
          </div>

          <Link style={{ textDecoration: 'none' }} to="/login-by-google">
            <div style={{ margin: '52px 0 0 0' }}>
              <AdvancedButton
                text="Zaloguj się przez Google"
                fullWidth
                icon={<GoogleIcon />}
              />
            </div>
          </Link>
          <div style={{ margin: '40px 0 0 0' }}>
            <Divider>
              <Line />
              <div style={{ margin: '0 21px' }}>
                <Typography variant="text" text="lub" fontSize="18px" />
              </div>
              <Line />
            </Divider>
          </div>
          <div style={{ margin: '40px 0 0 0' }}>
            <InputField
              value={login}
              onChange={(e) => setLogin(e.target.value)}
              label="Nazwa użytkownika/email"
              placeholder="Wpisz nazwę użytkownika/email"
            />
          </div>
          <div style={{ margin: '45px 0 0 0' }}>
            <InputField
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              label="Hasło"
              placeholder="Wpisz hasło"
              variant="password"
              icon={eyeIcon}
            />
          </div>
          <div style={{ margin: '25px 15px 0', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <Checkbox
              checked={rememberMe}
              onChange={() => setRememberMe(!rememberMe)}
              labelText="Zapamiętaj mnie"
            />
            <Link style={{ textDecoration: 'none' }} to="/password-recovery">
              <Typography variant="text" text="Nie pamiętasz hasła?" fontSize="18px" />
            </Link>
          </div>
          <div style={{ margin: '45px 0 0 0' }}>
            <LargeButton text="Zaloguj" background="#ffb400" fullWidth onClick={() => handleSubmit()} />
          </div>
          <div style={{ margin: '24px 0 0 0', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
            <Typography variant="text" text="Nie masz jeszcze konta?&nbsp;" fontSize="18px" />
            <Link style={{ textDecoration: 'none' }} to="/register">
              <Typography variant="text" text="Zarejestruj się" fontSize="18px" color="#ffb400" />
            </Link>
          </div>
        </Wrapper>
      )}
    />
  );
};

export default LoginPage;
