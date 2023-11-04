import { lazy } from 'react';

// project import
import Loadable from 'components/Loadable';
import MinimalLayout from 'layout/MinimalLayout';

// render - Roles
const Students = Loadable(lazy(() => import('pages/roles/Students')));
const Teachers = Loadable(lazy(() => import('pages/roles/Teachers')));
const Administration = Loadable(lazy(() => import('pages/roles/Administration')));
const Classes = Loadable(lazy(() => import('pages/roles/Classes')));
const Modules = Loadable(lazy(() => import('pages/roles/Modules')));
// ==============================|| Roles ROUTING ||============================== //

const RolesRoutes = {
  path: '/',
  element: <MinimalLayout />,
  children: [
    {
      path: 'students',
      element: <Students />
    },
    {
      path: 'teachers',
      element: <Teachers />
    },
    {
      path: 'administration',
      element: <Administration />
    },
    {
      path: 'classes',
      element: <Classes />
    }
    // {
    //   path: 'modules',
    //   element: <Modules />
    // }
  ]
};

export default RolesRoutes;
