import { describe, it, expect, vi } from 'vitest';
import { render, screen } from '@testing-library/react';
import { ThemeProvider } from '@mui/material';
import MetricsChart from '../components/MetricsChart';
import theme from '../theme';

const renderWithTheme = (component) => {
  return render(<ThemeProvider theme={theme}>{component}</ThemeProvider>);
};

const mockGames = [
  { id: 1, name: 'Game 1', avgFps: 144 },
  { id: 2, name: 'Game 2', avgFps: 119 },
  { id: 3, name: 'Game 3', avgFps: 165 },
];

describe('MetricsChart Component', () => {
  it('renders chart title', () => {
    renderWithTheme(
      <MetricsChart title="Average FPS" dataKey="avgFps" games={mockGames} />
    );
    expect(screen.getByText('Average FPS')).toBeInTheDocument();
  });

  it('renders with empty games array', () => {
    renderWithTheme(
      <MetricsChart title="Test Chart" dataKey="avgFps" games={[]} />
    );
    expect(screen.getByText('Test Chart')).toBeInTheDocument();
  });

  it('renders chart container', () => {
    const { container } = renderWithTheme(
      <MetricsChart title="CPU Usage" dataKey="cpuUsage" games={mockGames} />
    );
    const chartContainer = container.querySelector('.recharts-responsive-container');
    expect(chartContainer).toBeTruthy();
  });
});
