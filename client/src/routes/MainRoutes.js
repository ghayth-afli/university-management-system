import { lazy } from 'react';

// project import
import Loadable from 'components/Loadable';
import MainLayout from 'layout/MainLayout';

// render - dashboard
const DashboardDefault = Loadable(lazy(() => import('pages/dashboard')));

// render - sample page
const SamplePage = Loadable(lazy(() => import('pages/extra-pages/SamplePage')));

// render - utilities
const Typography = Loadable(lazy(() => import('pages/components-overview/Typography')));
const Color = Loadable(lazy(() => import('pages/components-overview/Color')));
const Shadow = Loadable(lazy(() => import('pages/components-overview/Shadow')));
const AntIcons = Loadable(lazy(() => import('pages/components-overview/AntIcons')));
const Students = Loadable(lazy(() => import('pages/roles/Students')));
const Teachers = Loadable(lazy(() => import('pages/roles/Teachers')));
const Administration = Loadable(lazy(() => import('pages/roles/Administration')));
const Classes = Loadable(lazy(() => import('pages/roles/Classes')));
const Modules = Loadable(lazy(() => import('pages/roles/Modules')));
// ==============================|| MAIN ROUTING ||============================== //

const MainRoutes = {
  path: '/',
  element: <MainLayout />,
  children: [
    {
      path: '/',
      element: <DashboardDefault />
    },
    {
      path: 'color',
      element: <Color />
    },
    {
      path: 'dashboard',
      children: [
        {
          path: 'default',
          element: <DashboardDefault />
        }
      ]
    },
    {
      path: 'sample-page',
      element: <SamplePage />
    },
    {
      path: 'shadow',
      element: <Shadow />
    },
    {
      path: 'typography',
      element: <Typography />
    },
    {
      path: 'icons/ant',
      element: <AntIcons />
    },
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

export default MainRoutes;
