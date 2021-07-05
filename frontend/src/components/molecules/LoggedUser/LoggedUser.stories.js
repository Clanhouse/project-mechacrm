import React from 'react';
import avatarImage from 'assets/images/flower.jpg';
import LoggedUser from './LoggedUser';

export default {
  title: 'Molecules/LoggerUser',
  component: LoggedUser,
  argTypes: {
    username: {
      defaultValue: 'Mark Twain',
    },
  },
};

export const LoggedUserStory = (args) => {
  const { username } = args;
  return (
    <div style={{ display: 'inline-block' }}>
      <LoggedUser icon={avatarImage} username={username} />
    </div>
  );
};
