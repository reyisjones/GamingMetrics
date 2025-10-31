import { useState, useEffect } from "react";
import { Grid, Container, Box, CircularProgress, Alert } from "@mui/material";
import GameCard from "../components/GameCard";
import MetricsChart from "../components/MetricsChart";
import config from "../api/config.json";
import sampleData from "../data/sampleGames.json";

export default function Dashboard() {
  const [games, setGames] = useState(sampleData.games);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchGames = async () => {
      try {
        setLoading(true);
        const response = await fetch(config.apis[config.activeApi]);
        
        if (!response.ok) {
          throw new Error('API not available, using sample data');
        }
        
        const data = await response.json();
        setGames(data.games || data);
        setError(null);
      } catch (err) {
        console.log('Using sample data:', err.message);
        setGames(sampleData.games);
        setError('Using sample data - API endpoint not available');
      } finally {
        setLoading(false);
      }
    };

    fetchGames();
    
    // Optional: Set up polling for live updates
    const interval = setInterval(fetchGames, 30000); // Poll every 30 seconds
    
    return () => clearInterval(interval);
  }, []);

  if (loading && games.length === 0) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh' }}>
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      {error && (
        <Alert severity="info" sx={{ mb: 3 }}>
          {error}
        </Alert>
      )}
      
      <Grid container spacing={3}>
        {games.map((g) => (
          <Grid item xs={12} sm={6} md={4} key={g.id}>
            <GameCard game={g} />
          </Grid>
        ))}
      </Grid>

      <Box sx={{ mt: 4 }}>
        <MetricsChart title="Average FPS" dataKey="avgFps" games={games} />
        <MetricsChart title="CPU Usage (%)" dataKey="cpuUsage" games={games} />
        <MetricsChart title="GPU Usage (%)" dataKey="gpuUsage" games={games} />
        <MetricsChart title="Latency (ms)" dataKey="latencyMs" games={games} />
      </Box>
    </Container>
  );
}
