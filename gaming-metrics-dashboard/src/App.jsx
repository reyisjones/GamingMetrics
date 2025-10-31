import { ThemeProvider, CssBaseline, Box } from '@mui/material';
import Header from './components/Header';
import Dashboard from './pages/Dashboard';
import theme from './theme';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Box sx={{ minHeight: '100vh', bgcolor: 'background.default' }}>
        <Header />
        <Dashboard />
      </Box>
    </ThemeProvider>
  );
}

export default App;
