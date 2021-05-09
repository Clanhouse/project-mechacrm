import React from 'react';
import FingerprintIcon from '@material-ui/icons/Fingerprint';
import PersonIcon from '@material-ui/icons/Person';
import PortraitIcon from '@material-ui/icons/Portrait';
import PhoneIcon from '@material-ui/icons/Phone';
import HomeIcon from '@material-ui/icons/Home';
import DriveEtaIcon from '@material-ui/icons/DriveEta';
import theme from '../../../theme/MainTheme';
import TableHeaderCell from './TableHeaderCell';

const icons = [
  <FingerprintIcon />,
  <PersonIcon />,
  <PortraitIcon />,
  <PhoneIcon />,
  <HomeIcon />,
  <DriveEtaIcon />,
];

export default {
  title: 'Atoms/TableHeaderCell',
  component: TableHeaderCell,
  argTypes: {
    label: {
      control: 'text',
      defaultValue: 'header',
    },
    color: {
      options: ['primary', 'secondary', 'success', 'info', 'warning', 'danger'],
      control: {
        type: 'select',
      },
    },
    icon: {
      defaultValue: '0',
      options: Object.keys(icons),
      mapping: icons,
      control: {
        type: 'select',
      },
    },
  },
};

export const HeaderCell = (args) => <TableHeaderCell {...args} theme={theme} />;
