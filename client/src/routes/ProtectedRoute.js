import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';

export const ProtectedRoute = ({ element }) => {
  const isAuthenticated = localStorage.getItem('token') !== null;
  const location = useLocation();

  return isAuthenticated ? element : <Navigate to="/login" state={{ from: location }} replace />;
};
