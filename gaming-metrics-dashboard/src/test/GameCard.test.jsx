import { describe, it, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import { ThemeProvider } from '@mui/material';
import GameCard from '../components/GameCard';
import theme from '../theme';

const renderWithTheme = (component) => {
  return render(<ThemeProvider theme={theme}>{component}</ThemeProvider>);
};

const mockGame = {
  id: 1,
  name: 'Test Game',
  avgFps: 144,
  cpuUsage: 62,
  gpuUsage: 75,
  latencyMs: 21,
  temperatureC: 68,
};

describe('GameCard Component', () => {
  it('renders game name', () => {
    renderWithTheme(<GameCard game={mockGame} />);
    expect(screen.getByText('Test Game')).toBeInTheDocument();
  });

  it('displays FPS metric', () => {
    renderWithTheme(<GameCard game={mockGame} />);
    expect(screen.getByText('FPS')).toBeInTheDocument();
    expect(screen.getByText('144')).toBeInTheDocument();
  });

  it('displays CPU usage', () => {
    renderWithTheme(<GameCard game={mockGame} />);
    expect(screen.getByText('CPU')).toBeInTheDocument();
    expect(screen.getByText('62%')).toBeInTheDocument();
  });

  it('displays GPU usage', () => {
    renderWithTheme(<GameCard game={mockGame} />);
    expect(screen.getByText('GPU')).toBeInTheDocument();
    expect(screen.getByText('75%')).toBeInTheDocument();
  });

  it('displays latency', () => {
    renderWithTheme(<GameCard game={mockGame} />);
    expect(screen.getByText('Latency')).toBeInTheDocument();
    expect(screen.getByText('21ms')).toBeInTheDocument();
  });

  it('displays temperature', () => {
    renderWithTheme(<GameCard game={mockGame} />);
    expect(screen.getByText('Temp')).toBeInTheDocument();
    expect(screen.getByText('68°C')).toBeInTheDocument();
  });

  it('shows success color for high FPS (>= 144)', () => {
    const { container } = renderWithTheme(<GameCard game={mockGame} />);
    const fpsChip = screen.getByText('144').closest('.MuiChip-root');
    expect(fpsChip).toHaveClass('MuiChip-colorSuccess');
  });

  it('shows warning color for medium FPS (60-143)', () => {
    const mediumFpsGame = { ...mockGame, avgFps: 90 };
    renderWithTheme(<GameCard game={mediumFpsGame} />);
    const fpsChip = screen.getByText('90').closest('.MuiChip-root');
    expect(fpsChip).toHaveClass('MuiChip-colorWarning');
  });

  it('shows error color for low FPS (< 60)', () => {
    const lowFpsGame = { ...mockGame, avgFps: 30 };
    renderWithTheme(<GameCard game={lowFpsGame} />);
    const fpsChip = screen.getByText('30').closest('.MuiChip-root');
    expect(fpsChip).toHaveClass('MuiChip-colorError');
  });

  it('shows error color for high CPU usage (>= 80%)', () => {
    const highCpuGame = { ...mockGame, cpuUsage: 85 };
    renderWithTheme(<GameCard game={highCpuGame} />);
    const cpuChip = screen.getByText('85%').closest('.MuiChip-root');
    expect(cpuChip).toHaveClass('MuiChip-colorError');
  });

  it('shows warning color for medium CPU usage (60-79%)', () => {
    renderWithTheme(<GameCard game={mockGame} />);
    const cpuChip = screen.getByText('62%').closest('.MuiChip-root');
    expect(cpuChip).toHaveClass('MuiChip-colorWarning');
  });

  it('shows success color for low CPU usage (< 60%)', () => {
    const lowCpuGame = { ...mockGame, cpuUsage: 45 };
    renderWithTheme(<GameCard game={lowCpuGame} />);
    const cpuChip = screen.getByText('45%').closest('.MuiChip-root');
    expect(cpuChip).toHaveClass('MuiChip-colorSuccess');
  });
});
