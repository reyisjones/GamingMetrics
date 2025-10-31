# Create Gaming Performance Dashboard (React + MUI + Preact + Tauri ready)

You are an AI coding assistant in Visual Studio Code.
Follow these steps to create a fast, responsive **Gaming Performance Dashboard** using **React + Material UI (MUI)** and make it compatible with **Preact** optimizations and **Tauri** desktop wrapping.

---

## 🧱 PROJECT STRUCTURE
Create the following folders and files:

```

gaming-metrics-dashboard/
│
├── src/
│   ├── api/
│   │   └── config.json
│   ├── components/
│   │   ├── GameCard.jsx
│   │   ├── MetricsChart.jsx
│   │   └── Header.jsx
│   ├── pages/
│   │   └── Dashboard.jsx
│   ├── data/
│   │   └── sampleGames.json
│   ├── App.jsx
│   ├── main.jsx
│   └── theme.js
│
├── package.json
└── vite.config.js

````

---

## ⚙️ TECH STACK
- React + Material-UI (Dashboard Template)
- Optionally replace React with Preact for lighter bundles (`preact/compat`)
- Chart.js or Recharts for visualizations
- Fetch configurable API endpoints from `api/config.json`
- Compatible with Java backend (REST / WebSocket)
- Optional Tauri integration (for desktop build)

---

## 📦 SAMPLE JSON DATA (`/src/data/sampleGames.json`)
```json
{
  "games": [
    {
      "id": 1,
      "name": "Cyber Drift X",
      "avgFps": 144,
      "cpuUsage": 62,
      "gpuUsage": 75,
      "latencyMs": 21,
      "temperatureC": 68
    },
    {
      "id": 2,
      "name": "StarForge Arena",
      "avgFps": 119,
      "cpuUsage": 70,
      "gpuUsage": 81,
      "latencyMs": 30,
      "temperatureC": 72
    },
    {
      "id": 3,
      "name": "Legends Reborn",
      "avgFps": 165,
      "cpuUsage": 55,
      "gpuUsage": 64,
      "latencyMs": 17,
      "temperatureC": 63
    }
  ]
}
````

---

## 🔌 API CONFIGURATION (`/src/api/config.json`)

```json
{
  "activeApi": "local",
  "apis": {
    "local": "http://localhost:8080/api/metrics",
    "prod": "https://api.mygamingmetrics.com/v1/data"
  }
}
```

In `Dashboard.jsx`, load the active API dynamically:

```js
import config from "../api/config.json";
import sampleData from "../data/sampleGames.json";

const [games, setGames] = useState([]);

useEffect(() => {
  fetch(config.apis[config.activeApi])
    .then(res => res.json())
    .then(data => setGames(data))
    .catch(() => setGames(sampleData.games));
}, []);
```

---

## 📊 METRICS CHART COMPONENT (`/src/components/MetricsChart.jsx`)

```jsx
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from "recharts";
import { Card, CardContent, Typography } from "@mui/material";

export default function MetricsChart({ title, dataKey, games }) {
  return (
    <Card sx={{ m: 2 }}>
      <CardContent>
        <Typography variant="h6">{title}</Typography>
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={games}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line type="monotone" dataKey={dataKey} stroke="#1976d2" />
          </LineChart>
        </ResponsiveContainer>
      </CardContent>
    </Card>
  );
}
```

---

## 🧩 DASHBOARD PAGE (`/src/pages/Dashboard.jsx`)

```jsx
import { Grid, Container } from "@mui/material";
import GameCard from "../components/GameCard";
import MetricsChart from "../components/MetricsChart";
import sampleData from "../data/sampleGames.json";

export default function Dashboard({ games = sampleData.games }) {
  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Grid container spacing={2}>
        {games.map(g => (
          <Grid item xs={12} sm={6} md={4} key={g.id}>
            <GameCard game={g} />
          </Grid>
        ))}
      </Grid>

      <MetricsChart title="Average FPS" dataKey="avgFps" games={games} />
      <MetricsChart title="CPU Usage (%)" dataKey="cpuUsage" games={games} />
      <MetricsChart title="GPU Usage (%)" dataKey="gpuUsage" games={games} />
      <MetricsChart title="Latency (ms)" dataKey="latencyMs" games={games} />
    </Container>
  );
}
```

---

## 🧠 OPTIMIZATION STEPS

1. Use **Preact** via alias in `vite.config.js`:

   ```js
   resolve: {
     alias: {
       react: "preact/compat",
       "react-dom/test-utils": "preact/test-utils",
       "react-dom": "preact/compat",
       "react/jsx-runtime": "preact/jsx-runtime"
     }
   }
   ```
2. Lazy-load heavy charts:

   ```js
   const MetricsChart = React.lazy(() => import("../components/MetricsChart"));
   ```
3. Enable `code splitting` and `gzip` compression for production builds.

---

## 🧱 BONUS: Tauri integration

To wrap as a desktop app later:

```bash
npm create tauri-app
# or integrate into existing Vite project
npm install --save-dev @tauri-apps/cli
```

---

## 🚀 OUTPUT EXPECTATION

The generated app should:

* Launch a Material-UI dashboard.
* Display sample game metrics (FPS, CPU/GPU, Latency).
* Allow API endpoint selection via `config.json`.
* Be ready for Tauri desktop wrapping and Java backend connection.
* Run via:

  ```bash
  npm install
  npm run dev
  ```

---

> 💡 *Optional enhancement*: Add WebSocket listener in `/src/api/socket.js` for live metric updates from your Java backend.

 
 
