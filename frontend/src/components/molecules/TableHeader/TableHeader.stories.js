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
  },
  {
    label: 'name',
    icon: <PersonIcon />,
  },
  {
    label: 'surname',
    icon: <PortraitIcon />,
  },
  {
    label: 'phone',
    icon: <PhoneIcon />,
  },
  {
    label: 'address',
    icon: <HomeIcon />,
  },
  {
    label: 'cars',
    icon: <DriveEtaIcon />,
  },
];

export const TableHeaderStory = () => (
  <table style={{ width: '100%' }}><TableHeader columns={columns} /></table>
);
