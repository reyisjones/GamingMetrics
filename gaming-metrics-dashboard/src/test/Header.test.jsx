import { describe, it, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import { ThemeProvider } from '@mui/material';
import Header from '../components/Header';
import theme from '../theme';

const renderWithTheme = (component) => {
  return render(<ThemeProvider theme={theme}>{component}</ThemeProvider>);
};

describe('Header Component', () => {
  it('renders the header with title', () => {
    renderWithTheme(<Header />);
    expect(screen.getByText('Gaming Metrics Dashboard')).toBeInTheDocument();
  });

  it('displays live indicator', () => {
    renderWithTheme(<Header />);
    expect(screen.getByText('Live')).toBeInTheDocument();
  });

  it('renders gaming icon', () => {
    const { container } = renderWithTheme(<Header />);
    const icon = container.querySelector('svg[data-testid="SportsEsportsIcon"]');
    expect(icon).toBeTruthy();
  });
});
