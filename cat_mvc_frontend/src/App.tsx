import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Header from './components/Header';
import CatsPage from './pages/CatsPage';
import AuthPage from './pages/AuthPage';
import ProfilePage from './pages/DashboardPage';
import CatManagementPage from './pages/CatManagementPage';
import ProtectedRoute from './components/ProtectedRoute';
import { AuthProvider } from './contexts/AuthContext';

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="min-h-screen bg-background text-foreground">
          <Header />
          <main className="mx-auto max-w-5xl px-3 sm:px-4 py-4 sm:py-6">
            <Routes>
              <Route path="/" element={<CatsPage />} />
              <Route path="/auth" element={<AuthPage />} />
              <Route 
                path="/profile" 
                element={
                  <ProtectedRoute>
                    <ProfilePage />
                  </ProtectedRoute>
                } 
              />
              <Route 
                path="/cat-management" 
                element={
                  <ProtectedRoute>
                    <CatManagementPage />
                  </ProtectedRoute>
                } 
              />
              <Route 
                path="/cats" 
                element={
                  <ProtectedRoute>
                    <CatsPage />
                  </ProtectedRoute>
                } 
              />
              <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
          </main>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
