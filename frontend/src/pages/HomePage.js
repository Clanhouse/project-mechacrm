import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { BsFileEarmarkSpreadsheetFill, BsShieldFill } from 'react-icons/bs';
import { MdLaptopChromebook } from 'react-icons/md';
import { Link as ScrollLink } from 'react-scroll';

const menuItems = [{ label: 'features' }, { label: 'pricing' }, { label: 'contact' }];

const MenuButton = styled.button`
  background-color: transparent;
  border: none;
  color: #e5e5e5;
  margin: 0 32px;
  text-transform: capitalize;
  cursor: pointer;
  transition: all 0.5s ease-in-out;

  &:hover {
    color: #ffb400;
  }
`;

const Button = styled.button`
  margin: 0 8px;
  padding: 6px 24px;
  border: ${({ secondary }) => (secondary ? '1px solid #fff' : '1px solid #ffb400')};
  background-color: ${({ secondary }) => (secondary ? 'transparent' : '#ffb400')};
  color: ${({ secondary }) => (secondary ? '#fff' : '#000')};
  border-radius: 18px;
  font-size: 16px;
  font-weight: bold;
  text-transform: capitalize;
  cursor: pointer;
  transition: all 0.5s ease-in-out;

  &:hover {
    border: ${({ secondary }) => (secondary ? '1px solid #fff' : '1px solid #014386')};
    background-color: ${({ secondary }) => (secondary ? '#fff' : '#014386')};
    color: ${({ secondary }) => (secondary ? '#000' : '#fff')};
  }
`;

const Container = styled.div`
  position: relative;
  width: 60%;
  margin: 0 auto;
  color: #c4c4c4;
`;

const Header = styled.header`
  height: 100px;
  display: flex;
  justify-content: flex-start;
  align-items: center;

  & li {
    flex-grow: 1;
    display: flex;
    justify-content: center;
  }
`;

const HeroSection = styled.section`
  margin-top: 100px;
  width: 60%;

  & h1 {
    font-size: 48px;
  }

  & p {
    margin-top: 50px;
    font-size: 24px;
  }

  & button {
    margin: 50px auto 0;
  }
`;

const FeatureSection = styled.section`
  margin-top: 100px;
  display: grid;
  gap: 80px;
  grid-template-columns: repeat(3, 1fr);
`;

const FeatureCard = styled.div`
  background-color: #014386;
  border-radius: 8px;
  padding: 30px;
  display: flex;
  flex-direction: column;

  & div {
    margin: 0 auto;
    width: 60px;
    height: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;

    & svg {
      width: 40px;
      height: 40px;
    }
  }

  & p {
    margin: 30px auto 0;
    font-size: 14px;
    text-align: center;
  }

  & button {
    margin: 30px auto 0;
  }
`;

const PricingSection = styled.section`
  margin-top: 100px;

  & > h2 {
    margin: 0 auto 50px;
    text-align: center;
    font-size: 40px;
    width: 80%;
  }

  & > div {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 100px;
  }
`;

const PricingCard = styled.div`
  padding: 30px;
  border: 1px solid #014386;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  font-size: 13px;
  text-align: center;
  width: 40%;

  & > div {
    width: 50%;
    margin: 0 auto 16px;
  }
`;

const Footer = styled.footer`
  position: relative;

  & > div {
    color: #c4c4c4;
    position: absolute;
    width: 100%;
    left: 0;
    right: 0;
    top: 70%;
    font-size: 40px;
    text-align: center;
  }
`;

const HomePage = () => (
  <div style={{ background: 'linear-gradient(180deg, #04294F 0%, #050F1A 17.16%)' }}>
    <Container>
      <Header>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="187"
          height="40"
          fill="none"
          viewBox="0 0 187 40"
        >
          <path
            fill="#fff"
            d="M18.675 13.815c.517-.629 1.71-1.183 2.583-1.183h4.57c.874 0 1.59.665 1.59 1.479v24.41c0 .813-.716 1.479-1.59 1.479h-3.576c-.874 0-1.59-.666-1.59-1.48V23.358c0-.814-.437-.962-.953-.296l-5.007 5.992a1.24 1.24 0 01-1.947 0L7.748 23.06c-.556-.629-.993-.518-.993.296v15.164c0 .813-.715 1.479-1.59 1.479H1.59C.715 40 0 39.334 0 38.52V14.112c0-.814.715-1.48 1.59-1.48h4.49c.874 0 2.026.518 2.582 1.147l4.053 4.808c.557.629 1.43.629 1.947 0l4.013-4.77zM61.162 26.28c0 7.553-6.637 13.72-14.783 13.72-8.107 0-14.744-6.13-14.744-13.72 0-7.518 6.637-13.648 14.744-13.648 8.186 0 14.783 6.13 14.783 13.647zm-7.074 0c0-4.124-3.418-7.481-7.67-7.481-4.212 0-7.63 3.357-7.63 7.48 0 4.16 3.418 7.518 7.63 7.518 4.213 0 7.67-3.358 7.67-7.518zM65.38 14.131c0-.825.698-1.5 1.551-1.5h19.394c.853 0 1.55.675 1.55 1.5v3.374c0 .825-.697 1.5-1.55 1.5h-5.043c-.853 0-1.551.675-1.551 1.5V38.5c0 .825-.698 1.5-1.552 1.5h-3.49c-.854 0-1.552-.675-1.552-1.5V20.505c0-.825-.698-1.5-1.551-1.5H66.93c-.853 0-1.551-.675-1.551-1.5v-3.374z"
          />
          <path
            fill="#FFB400"
            d="M121.62 26.28c0 7.553-6.636 13.72-14.783 13.72-8.106 0-14.743-6.13-14.743-13.72 0-7.518 6.637-13.648 14.743-13.648 8.147 0 14.783 6.13 14.783 13.647zm-7.113 0c0-4.124-3.418-7.481-7.67-7.481-4.212 0-7.63 3.357-7.63 7.48 0 4.16 3.418 7.518 7.63 7.518 4.252 0 7.67-3.358 7.67-7.518z"
          />
          <path
            fill="#fff"
            d="M144.573 13.815c.508-.629 1.682-1.183 2.542-1.183h4.576c.861 0 1.565.665 1.565 1.479v24.41c0 .813-.704 1.479-1.565 1.479h-3.52c-.86 0-1.564-.666-1.564-1.48V23.358c0-.814-.431-.962-.978-.333l-5.085 6.029c-.547.628-1.408.628-1.955 0l-5.124-6.029c-.547-.629-.978-.48-.978.333v15.164c0 .813-.704 1.479-1.564 1.479h-3.52c-.861 0-1.565-.666-1.565-1.48V14.112c0-.814.704-1.48 1.565-1.48h4.732c.861 0 1.995.518 2.543 1.147l3.989 4.808c.548.629 1.408.629 1.916 0l3.99-4.77z"
          />
          <path
            fill="#FFB400"
            d="M187 26.28c0 7.553-6.636 13.72-14.783 13.72-8.107 0-14.743-6.13-14.743-13.72 0-7.518 6.636-13.648 14.743-13.648 8.147 0 14.783 6.13 14.783 13.647zm-7.074 0c0-4.124-3.417-7.481-7.669-7.481-4.213 0-7.63 3.357-7.63 7.48 0 4.16 3.417 7.518 7.63 7.518 4.212 0 7.669-3.358 7.669-7.518zM184.065 8.421H95.063c-2.955 0-3.913-2.076-1.917-4.499v.039C94.743 1.69 97.978 0 100.573 0h77.942c2.596 0 6.069 1.73 7.667 3.999v-.077c1.597 2.423.838 4.499-2.117 4.499z"
          />
        </svg>
        <li>
          {menuItems.map((item) => (
            <ScrollLink key={item.label} to={item.label} smooth offset={-30}>
              <MenuButton>{item.label}</MenuButton>
            </ScrollLink>
          ))}
        </li>
        <div>
          <Link to="/register">
            <Button secondary>sign up</Button>
          </Link>
          <Link to="/login">
            <Button>log in</Button>
          </Link>
        </div>
      </Header>

      <HeroSection>
        <h1>Lorem ipsum dolor sit amet, adipisicing.</h1>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores dolor ducimus error
          fugit itaque maxime mollitia officia optio sequi suscipit, tenetur ut vero!
        </p>
        <Button>button text</Button>
      </HeroSection>

      <FeatureSection name="features">
        <FeatureCard>
          <div style={{ backgroundColor: '#4d9a51' }}>
            <BsShieldFill />
          </div>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aspernatur consequatur eos
            facere harum!
          </p>
          <Button>button text</Button>
        </FeatureCard>
        <FeatureCard>
          <div style={{ backgroundColor: '#0060c1' }}>
            <MdLaptopChromebook />
          </div>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aspernatur consequatur eos
            facere harum!
          </p>
          <Button>button text</Button>
        </FeatureCard>
        <FeatureCard>
          <div style={{ backgroundColor: '#d74545' }}>
            <BsFileEarmarkSpreadsheetFill />
          </div>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aspernatur consequatur eos
            facere harum!
          </p>
          <Button>button text</Button>
        </FeatureCard>
      </FeatureSection>

      <PricingSection name="pricing">
        <h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas.</h2>
        <div>
          <PricingCard>
            <div>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 300 300">
                <path
                  fill="#E5E5E5"
                  d="M87.5 187.5V175h125v12.5c0 6.875 5.625 12.5 12.5 12.5s12.5-5.625 12.5-12.5v-78.875L218 50c-.625-2-1.5-3.625-2.375-5-.25-.25-.375-.5-.625-.875-4.75-6.5-11.5-6.625-11.5-6.625h-107s-6.75.125-11.5 6.75c-.25.25-.375.5-.625.75-.875 1.375-1.75 3-2.375 5l-19.5 58.625V187.5c0 6.875 5.625 12.5 12.5 12.5s12.5-5.625 12.5-12.5zm25-43.75c-6.875 0-12.5-5.625-12.5-12.5s5.625-12.5 12.5-12.5 12.5 5.625 12.5 12.5-5.625 12.5-12.5 12.5zm75 0c-6.875 0-12.5-5.625-12.5-12.5s5.625-12.5 12.5-12.5 12.5 5.625 12.5 12.5-5.625 12.5-12.5 12.5zM104.125 62.5h91.75l2.875 8.625 5.375 16.375H95.875l8.25-25zM50 225.125c0 6.75 5.625 12.375 12.375 12.375H137.5v25.125c0 6.875 5.625 12.375 12.375 12.375H150c6.875 0 12.375-5.625 12.375-12.375V237.5H237.5c6.875 0 12.375-5.625 12.375-12.375 0-6.875-5.625-12.375-12.375-12.375H62.375a12.31 12.31 0 00-4.743.929A12.321 12.321 0 0050 225.125z"
                />
              </svg>
            </div>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto asperiores
              nesciunt perspiciatis porro temporibus vero!
            </p>
          </PricingCard>
          <PricingCard>
            <div>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 250 250">
                <path
                  fill="#E5E5E5"
                  d="M187.5 10.417c-28.75 0-52.083 23.333-52.083 52.083s23.333 52.083 52.083 52.083S239.583 91.25 239.583 62.5 216.25 10.417 187.5 10.417zm0 62.5c-2.917 0-5.208-2.292-5.208-5.209v-31.25c0-2.916 2.291-5.208 5.208-5.208 2.917 0 5.208 2.292 5.208 5.208v31.25c0 2.917-2.291 5.209-5.208 5.209zm5.208 15.625c0 2.916-2.291 5.208-5.208 5.208-2.917 0-5.208-2.292-5.208-5.208 0-2.917 2.291-5.209 5.208-5.209 2.917 0 5.208 2.292 5.208 5.209zm10.417 119.791c8.542 0 15.625-6.979 15.625-15.625v-64.375c-10.938 5.313-22.5 7.188-32.188 7.084.625 1.666.938 3.437.938 5.208a15.604 15.604 0 01-15.625 15.625 15.604 15.604 0 01-15.625-15.625c0-4.063 1.562-7.708 4.062-10.521a73.247 73.247 0 01-38.645-36.354H60.521L71.354 62.5h43.229c0-7.188 1.042-14.27 3.021-20.833H67.708c-6.875 0-12.604 4.375-14.791 10.52L32.396 111.25c-.73 2.188-1.146 4.479-1.146 6.875v74.583c0 8.646 6.98 15.625 15.625 15.625A15.604 15.604 0 0062.5 192.708V187.5h125v5.208c0 8.646 7.083 15.625 15.625 15.625zm-125-52.083A15.604 15.604 0 0162.5 140.625c0-8.646 6.98-15.625 15.625-15.625a15.604 15.604 0 0115.625 15.625c0 8.646-6.98 15.625-15.625 15.625z"
                />
              </svg>
            </div>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto asperiores
              nesciunt perspiciatis porro temporibus vero!
            </p>
          </PricingCard>
          <PricingCard>
            <div>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 250 250">
                <path
                  fill="#E5E5E5"
                  d="M228.333 68.958a10.415 10.415 0 00-5.625-5.625 10.417 10.417 0 00-3.958-.833h-52.083a10.418 10.418 0 000 20.833h26.979l-58.229 58.229-34.271-34.375a10.431 10.431 0 00-7.396-3.081 10.432 10.432 0 00-7.396 3.081l-62.5 62.501a10.412 10.412 0 00-2.28 11.401 10.405 10.405 0 002.28 3.39 10.423 10.423 0 007.396 3.081 10.413 10.413 0 007.396-3.081l55.104-55.208 34.271 34.375a10.414 10.414 0 0011.402 2.28 10.436 10.436 0 003.39-2.28l65.52-65.625V125a10.42 10.42 0 0010.417 10.417A10.42 10.42 0 00229.167 125V72.917a10.423 10.423 0 00-.834-3.959z"
                />
              </svg>
            </div>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto asperiores
              nesciunt perspiciatis porro temporibus vero!
            </p>
          </PricingCard>
        </div>
      </PricingSection>
    </Container>
    <Footer name="contact">
      <div>&#169; Copyright 2022 Clanhouse</div>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="1920"
        height="645"
        fill="none"
        viewBox="0 0 1920 645"
      >
        <path
          fill="#014386"
          d="M372.5 101.174c-165.181 11.767-317.333 69-372.5 103.5v434.999l2339-31c-58.67-151.833.2-535.545-41-577.145-51.5-52-246.84-24-380-24-509.5 0-434.5 169.15-629 210.972-143.5 30.856-207 21.674-331.5-13.826-99.533-28.381-225-129.146-585-103.5z"
        />
        <path
          fill="#0060C1"
          d="M402 111.673c-165.181 11.768-363.333 65-418.5 99.5L0 671.875l2339-31c-58.67-151.833.2-535.545-41-577.145-51.5-52-195.34-62.057-328.5-62.057-509.5 0-474.42 190.783-681 235.202C1145 267.731 935 215.673 806 162.673c-69.253-28.452-158-68.524-404-51z"
        />
      </svg>
    </Footer>
  </div>
);

export default HomePage;
