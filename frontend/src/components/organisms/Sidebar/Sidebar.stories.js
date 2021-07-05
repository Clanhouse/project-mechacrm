import React from 'react';
import StoryRouter from 'storybook-react-router';
import SidebarComponent from './Sidebar';

export default {
  title: 'Organisms/Sidebar',
  component: SidebarComponent,
  decorators: [StoryRouter()],
};

export const Sidebar = () => <SidebarComponent />;
