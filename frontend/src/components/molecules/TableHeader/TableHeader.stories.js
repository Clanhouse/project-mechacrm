import React from 'react';
import FingerprintIcon from '@material-ui/icons/Fingerprint';
import PersonIcon from '@material-ui/icons/Person';
import PortraitIcon from '@material-ui/icons/Portrait';
import PhoneIcon from '@material-ui/icons/Phone';
import HomeIcon from '@material-ui/icons/Home';
import DriveEtaIcon from '@material-ui/icons/DriveEta';
import TableHeader from './TableHeader';

export default {
  title: 'Molecules/TableHeader',
  component: TableHeader,
};

const columns = [
  {
    label: 'id',
    icon: <FingerprintIcon />,
    color: 'blue',
  },
  {
    label: 'name',
    icon: <PersonIcon />,
    color: 'blue',
  },
  {
    label: 'surname',
    icon: <PortraitIcon />,
    color: 'blue',
  },
  {
    label: 'phone',
    icon: <PhoneIcon />,
    color: 'blue',
  },
  {
    label: 'address',
    icon: <HomeIcon />,
    color: 'blue',
  },
  {
    label: 'cars',
    icon: <DriveEtaIcon />,
    color: 'blue',
  },
];

export const TableHeaderStory = () => (
  <table style={{ width: '100%' }}><TableHeader columns={columns} /></table>
);
