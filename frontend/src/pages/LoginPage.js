import React from 'react';
import styled from 'styled-components';
import LoginPageTemplate from 'components/templates/LoginPageTemplate/LoginPageTemplate';
import Typography from 'components/atoms/Typography/Typography';
import AdvancedButton from 'components/atoms/AdvancedButton/AdvancedButton';
import LargeButton from 'components/atoms/LargeButton/LargeButton';
import { Link } from 'react-router-dom';
import { FaEye, FaGoogle } from 'react-icons/fa';
import { Formik } from 'formik';
import InputField from 'components/atoms/InputField/InputField';
import Checkbox from 'components/atoms/Checkbox/Checkbox';

const Divider = styled.div`
  margin-top: 20px;
  display: flex;
  align-items: center;
  color: ${({ theme }) => theme.color.dark};

  & span {
    height: 1px;
    width: 100%;
    background-color: ${({ theme }) => theme.color.dark};
  }

  & p {
    margin: 0 21px;
  }
`;

const Form = styled.form`
  margin-top: 16px;
`;

const RememberMeBox = styled.div`
  margin-top: 8px;
  padding-right: 16px;
  display: flex;
  align-items: center;
  justify-content: space-between;

  & > a {
    text-decoration: underline;
  }
`;

const NewAccountBox = styled.div`
  margin: 16px auto 0;

  & span {
    color: ${({ theme }) => theme.color.primary};
    cursor: pointer;
    transition: all 0.3s ease-in-out;

    &:hover {
      color: ${({ theme }) => theme.color.secondary};
    }
  }
`;

const LoginPage = () => {
  const errors = {};
  const validate = (values) => {
    if (!values.login) {
      errors.login = 'Pole wymagane';
    } else {
      errors.login = '';
    }

    if (!values.password) {
      errors.password = 'Pole wymagane';
    } else if (values.password.length < 8) {
      errors.password = 'Minimalna długość hasła, 8 znaków';
    } else {
      errors.password = '';
    }
  };

  return (
    <LoginPageTemplate
      loginSection={(
        <>
          <Typography variant="h2" fontSize="36px">
            Witaj w Motomo
          </Typography>

          <Typography>Zaloguj się podając informacje poniżej</Typography>

          <Link to="/login-by-google">
            <AdvancedButton fullWidth Icon={FaGoogle}>
              Zaloguj się przez Google
            </AdvancedButton>
          </Link>

          <Divider>
            <span />
            <p>lub</p>
            <span />
          </Divider>

          <Formik
            initialValues={{ login: '', password: '', rememberMe: false }}
            validate={validate}
            onSubmit={(values) => {
              console.log(values);
              // alert(JSON.stringify(values, null, 2));
            }}
          >
            {({ values, handleChange, handleBlur, handleSubmit }) => (
              <Form noValidate autoComplete="off" onSubmit={handleSubmit}>
                <InputField
                  name="login"
                  label="Nazwa użytkownika/email"
                  placeholder="Wpisz nazwę użytkownika/email"
                  value={values.login}
                  onChange={handleChange}
                  onBlur={handleBlur}
                  errorMessage={errors.login}
                />
                <InputField
                  name="password"
                  variant="password"
                  label="Hasło"
                  placeholder="Wpisz hasło"
                  value={values.password}
                  onChange={handleChange}
                  onBlur={handleBlur}
                  errorMessage={errors.password}
                  Icon={FaEye}
                />
                <RememberMeBox>
                  <Checkbox
                    fontSize="16px"
                    label="Zapamiętaj mnie"
                    checked={values.rememberMe}
                    onChange={handleChange}
                    name="rememberMe"
                  />
                  <Link to="/recovery">
                    <Typography fontSize="14px">Nie pamiętasz hasła?</Typography>
                  </Link>
                </RememberMeBox>
                <Link to="/dashboard">
                  <LargeButton type="submit" fullWidth>
                    Zaloguj
                  </LargeButton>
                </Link>
              </Form>
            )}
          </Formik>
          <NewAccountBox>
            <Typography fontSize="16px">
              Nie masz jeszcze konta?{' '}
              <Link to="/register">
                <span>Zarejestruj się</span>
              </Link>
            </Typography>
          </NewAccountBox>
        </>
      )}
    />
  );
};

export default LoginPage;
