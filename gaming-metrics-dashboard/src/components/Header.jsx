import { AppBar, Toolbar, Typography, Box } from '@mui/material';
import SportsEsportsIcon from '@mui/icons-material/SportsEsports';

export default function Header() {
  return (
    <AppBar position="static" elevation={0} sx={{ bgcolor: 'background.paper', mb: 4 }}>
      <Toolbar>
        <SportsEsportsIcon sx={{ mr: 2, color: 'primary.main' }} />
        <Typography variant="h5" component="div" sx={{ flexGrow: 1, fontWeight: 600 }}>
          Gaming Metrics Dashboard
        </Typography>
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
          <Box
            sx={{
              width: 8,
              height: 8,
              borderRadius: '50%',
              bgcolor: 'success.main',
              animation: 'pulse 2s infinite',
            }}
          />
          <Typography variant="body2" color="text.secondary">
            Live
          </Typography>
        </Box>
      </Toolbar>
    </AppBar>
  );
}
