import { describe, it, expect, vi, beforeEach, afterEach } from 'vitest';
import { render, screen, waitFor } from '@testing-library/react';
import { ThemeProvider } from '@mui/material';
import Dashboard from '../pages/Dashboard';
import theme from '../theme';

const renderWithTheme = (component) => {
  return render(<ThemeProvider theme={theme}>{component}</ThemeProvider>);
};

// Mock fetch
global.fetch = vi.fn();

describe('Dashboard Component', () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('renders dashboard container', async () => {
    fetch.mockRejectedValueOnce(new Error('API Error'));
    
    const { container } = renderWithTheme(<Dashboard />);
    
    await waitFor(() => {
      expect(container.querySelector('.MuiContainer-root')).toBeTruthy();
    });
  });

  it('displays sample data when API fails', async () => {
    fetch.mockRejectedValueOnce(new Error('API Error'));
    
    renderWithTheme(<Dashboard />);
    
    await waitFor(() => {
      expect(screen.getByText('Cyber Drift X')).toBeInTheDocument();
      expect(screen.getByText('StarForge Arena')).toBeInTheDocument();
      expect(screen.getByText('Legends Reborn')).toBeInTheDocument();
    });
  });

  it('shows info alert when using sample data', async () => {
    fetch.mockRejectedValueOnce(new Error('API Error'));
    
    renderWithTheme(<Dashboard />);
    
    await waitFor(() => {
      expect(screen.getByText(/Using sample data/i)).toBeInTheDocument();
    });
  });

  it('displays API data when fetch succeeds', async () => {
    const apiData = {
      games: [
        {
          id: 1,
          name: 'API Game',
          avgFps: 200,
          cpuUsage: 50,
          gpuUsage: 60,
          latencyMs: 15,
          temperatureC: 65,
        },
      ],
    };

    fetch.mockResolvedValueOnce({
      ok: true,
      json: async () => apiData,
    });

    renderWithTheme(<Dashboard />);

    await waitFor(() => {
      expect(screen.getByText('API Game')).toBeInTheDocument();
    });
  });

  it('renders all game cards', async () => {
    fetch.mockRejectedValueOnce(new Error('API Error'));
    
    const { container } = renderWithTheme(<Dashboard />);
    
    await waitFor(() => {
      const gameCards = container.querySelectorAll('.MuiCard-root');
      // 3 game cards + 4 chart cards = 7 total cards
      expect(gameCards.length).toBeGreaterThanOrEqual(3);
    });
  });

  it('renders all metric charts', async () => {
    fetch.mockRejectedValueOnce(new Error('API Error'));
    
    renderWithTheme(<Dashboard />);
    
    await waitFor(() => {
      expect(screen.getByText('Average FPS')).toBeInTheDocument();
      expect(screen.getByText('CPU Usage (%)')).toBeInTheDocument();
      expect(screen.getByText('GPU Usage (%)')).toBeInTheDocument();
      expect(screen.getByText('Latency (ms)')).toBeInTheDocument();
    });
  });

  it('fetches data from API on mount', async () => {
    const apiData = {
      games: [
        {
          id: 1,
          name: 'API Game',
          avgFps: 200,
          cpuUsage: 50,
          gpuUsage: 60,
          latencyMs: 15,
          temperatureC: 65,
        },
      ],
    };

    fetch.mockResolvedValueOnce({
      ok: true,
      json: async () => apiData,
    });

    renderWithTheme(<Dashboard />);

    await waitFor(() => {
      expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/metrics');
    });
  });
});
