import React from 'react';
import Avatar from './Avatar';

import imageLink from '../../../assets/images/flower.jpg';

export default {
  title: 'Atoms/Avatar',
  component: Avatar,
};

const TemplateLarge = (args) => (
  <>
    <div style={{
      width: '300px',
      height: '300px',
    }}
    >
      <Avatar {...args} />
    </div>
  </>
);

const TemplateMedium = (args) => (
  <>
    <div style={{
      width: '100px',
      height: '100px',
    }}
    >
      <Avatar {...args} />
    </div>
  </>
);

const TemplateSmall = (args) => (
  <>
    <div style={{
      width: '32px',
      height: '32px',
    }}
    >
      <Avatar {...args} />
    </div>
  </>
);

export const LargeAvatar = TemplateLarge.bind({});
LargeAvatar.args = {
  imageLink,
};

export const MediumAvatar = TemplateMedium.bind({});
MediumAvatar.args = {
  imageLink,
};

export const SmallAvatar = TemplateSmall.bind({});
SmallAvatar.args = {
  imageLink,
};
