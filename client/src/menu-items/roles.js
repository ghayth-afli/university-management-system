// assets
import { LoginOutlined, ProfileOutlined, BarcodeOutlined } from '@ant-design/icons';

// icons
const icons = {
  LoginOutlined,
  ProfileOutlined,
  BarcodeOutlined
};

// ==============================|| MENU ITEMS - EXTRA PAGES ||============================== //

const roles = {
  id: 'roles',
  title: 'Roles',
  type: 'group',
  children: [
    {
      id: 'students',
      title: 'Students',
      type: 'item',
      url: '/students',
      icon: icons.LoginOutlined,
      breadcrumbs: false
    },
    {
      id: 'teachers',
      title: 'Teachers',
      type: 'item',
      url: '/teachers',
      icon: icons.ProfileOutlined,
      breadcrumbs: false
    },
    {
      id: 'administration',
      title: 'Administration',
      type: 'item',
      url: '/administration',
      icon: icons.BarcodeOutlined,
      breadcrumbs: false
    }
  ]
};

export default roles;
