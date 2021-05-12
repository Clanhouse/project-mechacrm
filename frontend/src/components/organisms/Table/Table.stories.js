import React from 'react';
import FingerprintIcon from '@material-ui/icons/Fingerprint';
import PersonIcon from '@material-ui/icons/Person';
import PortraitIcon from '@material-ui/icons/Portrait';
import PhoneIcon from '@material-ui/icons/Phone';
import HomeIcon from '@material-ui/icons/Home';
import DriveEtaIcon from '@material-ui/icons/DriveEta';
import Table from './Table';

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

const rowsData = [
  {
    id: '1',
    name: 'John',
    surname: 'Doe',
    phone: '000-000-000',
    address: 'lorem ipsum dolor',
    cars: 'view',
  },
  {
    id: '2',
    name: 'Anna',
    surname: 'Smith',
    phone: '000-000-000',
    address: 'lorem ipsum dolor',
    cars: 'view',
  },
  {
    id: '3',
    name: 'Alan',
    surname: 'Wright',
    phone: '000-000-000',
    address: 'lorem ipsum dolor',
    cars: 'view',
  },
  {
    id: '4',
    name: 'Stanley',
    surname: 'Brown',
    phone: '000-000-000',
    address: 'lorem ipsum dolor',
    cars: 'view',
  },
  {
    id: '5',
    name: 'Ellen',
    surname: 'Johnson',
    phone: '000-000-000',
    address: 'lorem ipsum dolor',
    cars: 'view',
  },
];

export default {
  title: 'Organisms/Table',
  component: Table,
};

export const TableStory = () => <Table columns={columns} rowsData={rowsData} />;
