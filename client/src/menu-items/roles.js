// assets
import { LoginOutlined, ProfileOutlined, BarcodeOutlined, AntDesignOutlined, EditOutlined } from '@ant-design/icons';

// icons
const icons = {
  LoginOutlined,
  ProfileOutlined,
  BarcodeOutlined,
  AntDesignOutlined,
  EditOutlined
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
    },
    {
      id: 'classes',
      title: 'Classes',
      type: 'item',
      url: '/classes',
      icon: icons.AntDesignOutlined,
      breadcrumbs: false
    }
    // {
    //   id: 'modules',
    //   title: 'Modules',
    //   type: 'item',
    //   url: '/modules',
    //   icon: icons.EditOutlined,
    //   breadcrumbs: false
    // }
  ]
};

export default roles;
