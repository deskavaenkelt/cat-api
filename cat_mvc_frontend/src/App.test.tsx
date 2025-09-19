import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';
import { ThemeProvider } from './theme/ThemeProvider';

test('renders page header Katter', () => {
  render(
    <ThemeProvider>
      <App />
    </ThemeProvider>
  );
  const headings = screen.getAllByRole('heading', { name: /Katter/i });
  expect(headings.length).toBeGreaterThan(0);
});
