import React from 'react';
import styled from 'styled-components';
import { Formik } from 'formik';
import RegisterPageTemplate from 'components/templates/RegisterPageTemplate/RegisterPageTemplate';
import Typography from 'components/atoms/Typography/Typography';
import { Link } from 'react-router-dom';
import AdvancedButton from 'components/atoms/AdvancedButton/AdvancedButton';
import {
  FaEnvelope, FaEye, FaFacebookF, FaGoogle, FaPhone,
} from 'react-icons/fa';
import InputField from 'components/atoms/InputField/InputField';
import Checkbox from 'components/atoms/Checkbox/Checkbox';
import LargeButton from 'components/atoms/LargeButton/LargeButton';

const SocialButtons = styled.div`
  margin-top: 32px;
  //margin-left: 16px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-column-gap: 20px;
`;

const Divider = styled.div`
  margin: 32px 0 16px;
  padding-left: 16px;
  display: flex;
  align-items: center;
  color: ${({ theme }) => theme.color.dark};

  & span {
    height: 1px;
    background-color: ${({ theme }) => theme.color.dark};
    width: 100%;
  }

  & p {
    margin: 0 16px;
  }
`;

const Form = styled.form`
`;

const FormRow = styled(SocialButtons)`
  margin: 0;
`;

const AlreadyHaveAccountBox = styled.div`
  margin-top: 16px;
  
  & span {
    color: ${({ theme }) => theme.color.primary};
    transition: all 0.3s ease-in-out;

    &:hover {
      color: ${({ theme }) => theme.color.secondary};
    }
  }
`;

const AcceptTermsBox = styled.div`
  margin-top: 8px;
  display: flex;

  & > a {
    text-decoration: underline;
  }
`;

const RegisterPage = () => {
  const errors = {};

  const validate = (values) => {
    if (!values.firstname) {
      errors.firstname = 'Pole wymagane';
    } else {
      errors.firstname = '';
    }

    if (!values.lastname) {
      errors.lastname = 'Pole wymagane';
    } else {
      errors.lastname = '';
    }

    if (!values.phone) {
      errors.phone = 'Pole wymagane';
    } else if (!/^\+48\d{9}/i.test(values.phone)) {
      errors.phone = 'Błędy numer telefonu';
    } else {
      errors.phone = '';
    }

    if (!values.email) {
      errors.email = 'Pole wymagane';
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.email)) {
      errors.email = 'Invalid email address';
    } else {
      errors.email = '';
    }

    if (!values.password) {
      errors.password = 'Pole wymagane';
    } else if (values.password.length < 8) {
      errors.password = 'Minimalna długość hasła, 8 znaków';
    } else if (!values.password2) {
      errors.password2 = 'Pole wymagane';
    } else if (values.password2.length < 8) {
      errors.password2 = 'Minimalna długość hasła, 8 znaków';
    } else if (values.password !== values.password2) {
      errors.password = 'Hasła się nie zgadzają';
      errors.password2 = 'Hasła się nie zgadzają';
    } else {
      errors.password = '';
      errors.password2 = '';
    }
  };

  return (
    <RegisterPageTemplate
      formSection={(
        <>
          <Typography variant="h2" fontSize="32px">
            Zarejestruj się
          </Typography>
          <Typography fontSize="16px">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          </Typography>
          <SocialButtons>
            <Link to="google">
              <AdvancedButton Icon={FaGoogle} fullWidth>
                Zarejestruj się przez Google
              </AdvancedButton>
            </Link>
            <Link to="facebook">
              <AdvancedButton Icon={FaFacebookF} fullWidth>
                Zarejestruj się przez Facebook
              </AdvancedButton>
            </Link>
          </SocialButtons>
          <Divider>
            <span />
            <p>lub</p>
            <span />
          </Divider>

          <Formik
            initialValues={{
              firstname: '',
              lastname: '',
              phone: '',
              email: '',
              password: '',
              password2: '',
              isAccepted: false,
            }}
            validate={validate}
            onSubmit={(values) => alert(JSON.stringify(values, null, 2))}
          >
            {({ values, handleChange, handleBlur, handleSubmit }) => (
              <Form noValidate autoComplete="off" onSubmit={handleSubmit}>
                <FormRow>
                  <InputField
                    name="firstname"
                    label="Imię"
                    placeholder="Wpisz swoje imię"
                    value={values.firstname}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    errorMessage={errors.firstname}
                  />
                  <InputField
                    name="lastname"
                    label="Nazwisko"
                    placeholder="Wpisz swoje imię"
                    value={values.lastname}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    errorMessage={errors.lastname}
                  />
                  <InputField
                    name="phone"
                    label="Numer telefonu"
                    placeholder="+48 ___ ___ ___"
                    value={values.phone}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    errorMessage={errors.phone}
                    Icon={FaPhone}
                  />
                  <InputField
                    name="email"
                    label="Email"
                    placeholder="Wpisz email"
                    value={values.email}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    errorMessage={errors.email}
                    Icon={FaEnvelope}
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
                  <InputField
                    name="password2"
                    variant="password"
                    label="Potwierdź hasło"
                    placeholder="Wpisz hasło"
                    value={values.password2}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    errorMessage={errors.password2}
                    Icon={FaEye}
                  />
                </FormRow>

                <AcceptTermsBox>
                  <Checkbox
                    name="isAccepted"
                    label="Akceptuję"
                    checked={values.isAccepted}
                    onChange={handleChange}
                    fontSize="16px"
                  />
                  <Link to="\terms-of-use">
                    <Typography fontSize="16px">
                      Regulamin serwisu
                    </Typography>
                  </Link>
                </AcceptTermsBox>

                <LargeButton type="submit" fullWidth>
                  Zarejestruj się
                </LargeButton>
              </Form>
            )}
          </Formik>

          <AlreadyHaveAccountBox>
            <Typography align="center" fontSize="16px">
              Masz już konto?{' '}
              <Link to="/login">
                <span>Zaloguj się</span>
              </Link>
            </Typography>
          </AlreadyHaveAccountBox>
        </>
      )}
    />
  );
};

export default RegisterPage;
