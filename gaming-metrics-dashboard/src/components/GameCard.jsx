import { Card, CardContent, Typography, Box, Chip } from '@mui/material';
import SpeedIcon from '@mui/icons-material/Speed';
import MemoryIcon from '@mui/icons-material/Memory';
import DeviceHubIcon from '@mui/icons-material/DeviceHub';
import NetworkCheckIcon from '@mui/icons-material/NetworkCheck';
import ThermostatIcon from '@mui/icons-material/Thermostat';

export default function GameCard({ game }) {
  const getPerformanceColor = (fps) => {
    if (fps >= 144) return 'success';
    if (fps >= 60) return 'warning';
    return 'error';
  };

  const getUsageColor = (usage) => {
    if (usage >= 80) return 'error';
    if (usage >= 60) return 'warning';
    return 'success';
  };

  return (
    <Card sx={{ height: '100%', transition: 'transform 0.2s', '&:hover': { transform: 'translateY(-4px)' } }}>
      <CardContent>
        <Typography variant="h6" component="div" gutterBottom sx={{ fontWeight: 600 }}>
          {game.name}
        </Typography>

        <Box sx={{ mt: 2, display: 'flex', flexDirection: 'column', gap: 1.5 }}>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
              <SpeedIcon fontSize="small" color="primary" />
              <Typography variant="body2" color="text.secondary">
                FPS
              </Typography>
            </Box>
            <Chip
              label={game.avgFps}
              color={getPerformanceColor(game.avgFps)}
              size="small"
              sx={{ fontWeight: 600 }}
            />
          </Box>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
              <MemoryIcon fontSize="small" color="primary" />
              <Typography variant="body2" color="text.secondary">
                CPU
              </Typography>
            </Box>
            <Chip
              label={`${game.cpuUsage}%`}
              color={getUsageColor(game.cpuUsage)}
              size="small"
              sx={{ fontWeight: 600 }}
            />
          </Box>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
              <DeviceHubIcon fontSize="small" color="primary" />
              <Typography variant="body2" color="text.secondary">
                GPU
              </Typography>
            </Box>
            <Chip
              label={`${game.gpuUsage}%`}
              color={getUsageColor(game.gpuUsage)}
              size="small"
              sx={{ fontWeight: 600 }}
            />
          </Box>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
              <NetworkCheckIcon fontSize="small" color="primary" />
              <Typography variant="body2" color="text.secondary">
                Latency
              </Typography>
            </Box>
            <Typography variant="body2" sx={{ fontWeight: 600 }}>
              {game.latencyMs}ms
            </Typography>
          </Box>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
              <ThermostatIcon fontSize="small" color="primary" />
              <Typography variant="body2" color="text.secondary">
                Temp
              </Typography>
            </Box>
            <Typography variant="body2" sx={{ fontWeight: 600 }}>
              {game.temperatureC}°C
            </Typography>
          </Box>
        </Box>
      </CardContent>
    </Card>
  );
}
