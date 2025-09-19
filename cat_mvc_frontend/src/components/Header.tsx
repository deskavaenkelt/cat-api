import React from 'react';
import Button from './Button';
import { useTheme } from '../theme/ThemeProvider';
import { useAuth } from '../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

const Header: React.FC = () => {
  const { theme, toggle } = useTheme();
  const { isAuthenticated, logout, user, isAdmin } = useAuth();
  const navigate = useNavigate();
  const isDark = theme === 'dark';

  const handleAuthAction = () => {
    if (isAuthenticated) {
      logout();
      navigate('/');
    } else {
      navigate('/auth');
    }
  };

  return (
    <header className="w-full bg-primary text-primary-foreground">
      <div className="mx-auto max-w-5xl px-3 py-2 sm:px-4 sm:py-3 flex flex-wrap items-center justify-between gap-2">
        <div className="font-bold text-base sm:text-lg">Cat MVC</div>
        <nav className="flex items-center gap-1 sm:gap-2">
          <Button 
            variant="ghost" 
            className="text-sm sm:text-base px-3 sm:px-4 py-2" 
            onClick={() => navigate('/')}
          >
            Hem
          </Button>
          {isAuthenticated && (
            <>
              <Button 
                variant="ghost" 
                className="text-sm sm:text-base px-3 sm:px-4 py-2" 
                onClick={() => navigate('/cat-management')}
              >
                Katt-admin
              </Button>
              <Button 
                variant="ghost" 
                className="text-sm sm:text-base px-3 sm:px-4 py-2" 
                onClick={() => navigate('/profile')}
              >
                Profil
              </Button>
            </>
          )}
          {isAuthenticated && user && (
            <span className="text-sm text-white/80 px-2">
              {user.fullName || user.email}
              {isAdmin && (
                <span className="ml-2 text-xs bg-yellow-600 text-yellow-100 px-2 py-0.5 rounded">
                  ADMIN
                </span>
              )}
            </span>
          )}
          <Button 
            variant="ghost" 
            className="text-sm sm:text-base px-3 sm:px-4 py-2" 
            onClick={handleAuthAction}
          >
            {isAuthenticated ? 'Logga ut' : 'Logga in'}
          </Button>
          <button
            onClick={toggle}
            aria-pressed={isDark}
            title={isDark ? 'Växla till ljust läge' : 'Växla till mörkt läge'}
            className="ml-1 sm:ml-2 inline-flex h-9 w-9 items-center justify-center rounded-full border border-white/20 bg-white/10 text-white/90 hover:bg-white/20 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary focus:ring-offset-[hsl(var(--background))]"
          >
            {isDark ? (
              // Sun icon for light mode target
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="h-5 w-5">
                <path d="M12 18a6 6 0 1 0 0-12 6 6 0 0 0 0 12Zm0 4a1 1 0 0 1-1-1v-1a1 1 0 1 1 2 0v1a1 1 0 0 1-1 1Zm0-20a1 1 0 0 1 1 1v1a1 1 0 1 1-2 0V3a1 1 0 0 1 1-1Zm10 11a1 1 0 0 1-1 1h-1a1 1 0 1 1 0-2h1a1 1 0 0 1 1 1ZM4 12a1 1 0 0 1-1 1H2a1 1 0 1 1 0-2h1a1 1 0 0 1 1 1Zm14.95 7.536a1 1 0 0 1-1.415 0l-.707-.707a1 1 0 0 1 1.414-1.415l.708.708a1 1 0 0 1 0 1.414ZM6.172 6.879A1 1 0 0 1 4.757 5.464l.707-.707A1 1 0 1 1 6.88 6.172l-.707.707Zm12.02-2.122a1 1 0 0 1 0 1.415l-.708.707a1 1 0 0 1-1.414-1.414l.707-.708a1 1 0 0 1 1.415 0ZM6.172 17.828a1 1 0 0 1-1.415 0l-.707-.707A1 1 0 0 1 6.464 15.707l.707.707a1 1 0 0 1 0 1.414Z"/>
              </svg>
            ) : (
              // Moon icon for dark mode target
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="h-5 w-5">
                <path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79Z" />
              </svg>
            )}
          </button>
        </nav>
      </div>
    </header>
  );
};

export default Header;
