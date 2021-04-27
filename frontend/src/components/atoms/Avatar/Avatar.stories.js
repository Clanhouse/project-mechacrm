import React from 'react';
import Avatar from './Avatar';
import imageLink from '../../../assets/images/flower.jpg';

export default {
  title: 'Atoms/Avatar',
  component: Avatar,
};

export const Large = () => <div style={{ width: '90px', height: '90px' }}><Avatar imageLink={imageLink} /></div>;
export const Medium = () => <div style={{ width: '60px', height: '60px' }}><Avatar imageLink={imageLink} /></div>;
export const Small = () => <div style={{ width: '30px', height: '30px' }}><Avatar imageLink={imageLink} /></div>;
