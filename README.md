# Gaming Metrics Dashboard

[![CI/CD Pipeline](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/workflows/CI%2FCD%20Pipeline/badge.svg)](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Node Version](https://img.shields.io/badge/node-%3E%3D18.0.0-brightgreen)](https://nodejs.org)

A fast, responsive **Gaming Performance Dashboard** built with **React + Material UI (MUI)** and optimized for performance with **Vite**. Monitor real-time gaming metrics (FPS, CPU/GPU usage, latency, temperature) from your **Java backend**. Available as both a **web application** and **cross-platform desktop app** (Windows, macOS, Linux) via **Tauri**.

## 🚀 Features

- 🎮 **Real-time Gaming Metrics Display**: FPS, CPU/GPU Usage, Latency, Temperature
- 📊 **Interactive Charts**: Visualize performance metrics with Recharts
- 🌙 **Dark Theme**: Modern gaming-themed UI with Material-UI
- 🔌 **Java Backend Integration**: RESTful API connection with configurable endpoints
- 💾 **Fallback Data**: Uses sample data when backend is unavailable
- ⚡ **Live Updates**: Polls Java backend every 30 seconds for fresh metrics
- 📱 **Responsive Design**: Works on desktop and mobile devices
- 🚀 **Performance Optimized**: Code splitting, lazy loading support
- 🪶 **Preact Ready**: Optional lightweight bundle (3KB vs 40KB)
- 💻 **Desktop App**: Cross-platform desktop application via Tauri
- ✅ **Fully Tested**: Comprehensive unit tests with Vitest (25/25 passing)
- 🔄 **CI/CD Ready**: Automated testing, building, and deployment

## 📦 Tech Stack

### Frontend
- **React 18** - UI framework
- **Material-UI (MUI) v5** - Component library
- **Recharts** - Data visualization
- **Vite** - Build tool and dev server
- **Vitest** - Unit testing framework
- **Tauri** - Desktop app framework
- **Preact** (optional) - Lighter React alternative
- **Emotion** - CSS-in-JS styling

### Backend (Java Integration)
- **Spring Boot** (recommended) - RESTful API server
- **Spring Web** - HTTP endpoints
- **Jackson** - JSON serialization
- **WebSocket** (optional) - Real-time updates
- Connects via configurable REST endpoints
- Expected response format: JSON with game metrics

## 🏗️ Project Structure

```
gaming-metrics-dashboard/
├── .github/
│   └── workflows/
│       ├── ci.yml              # CI/CD pipeline
│       ├── release.yml         # Desktop app builds
│       └── pages.yml           # GitHub Pages deployment
├── src/
│   ├── api/
│   │   └── config.json         # Java backend endpoint configuration
│   ├── components/
│   │   ├── GameCard.jsx        # Individual game metric card
│   │   ├── MetricsChart.jsx    # Reusable chart component
│   │   └── Header.jsx          # App header with live indicator
│   ├── pages/
│   │   └── Dashboard.jsx       # Main dashboard page
│   ├── data/
│   │   └── sampleGames.json    # Sample/fallback data
│   ├── test/
│   │   ├── setup.js            # Test configuration
│   │   ├── Header.test.jsx
│   │   ├── GameCard.test.jsx
│   │   ├── MetricsChart.test.jsx
│   │   └── Dashboard.test.jsx
│   ├── App.jsx                 # Root component
│   ├── main.jsx                # App entry point
│   └── theme.js                # MUI theme configuration
├── src-tauri/
│   ├── src/
│   │   └── main.rs             # Tauri main process
│   ├── icons/                  # App icons
│   ├── Cargo.toml              # Rust dependencies
│   └── tauri.conf.json         # Tauri configuration
├── package.json
├── vite.config.js              # Vite configuration
├── vitest.config.js            # Vitest configuration
├── README.md
├── QUICKSTART.md               # 5-minute setup guide
├── INSTALLATION.md             # End-user installation guide
├── CONTRIBUTING.md             # Developer contribution guide
└── CHANGELOG.md                # Version history
```

## 🚀 Quick Start

### Prerequisites

- **Node.js** 18+ and npm
- **Rust** (for Tauri desktop builds) - [Install Rust](https://www.rust-lang.org/tools/install)
- **Java Backend** (optional) - See [Java Backend Setup](#-java-backend-integration) below

### Web Application

1. **Clone the repository**:
```bash
git clone https://github.com/YOUR_USERNAME/gaming-metrics-dashboard.git
cd gaming-metrics-dashboard
```

2. **Navigate to the project directory**:
```bash
cd gaming-metrics-dashboard
```

3. **Install dependencies**:
```bash
npm install
```

4. **Start the development server**:
```bash
npm run dev
```

5. **Open your browser** to `http://localhost:5173`

The dashboard will initially show sample data. To connect to your Java backend, continue to the configuration section.

### Desktop Application

1. **Follow steps 1-3 from Web Application**

2. **Run in development mode**:
```bash
npm run tauri:dev
```

3. **Build for production**:
```bash
npm run tauri:build
```

Installers will be created in `src-tauri/target/release/bundle/`

## 🧪 Testing

### Run Unit Tests

```bash
npm test
```

All 25 tests should pass ✅

### Run Tests with UI

```bash
npm run test:ui
```

### Generate Coverage Report

```bash
npm run test:coverage
```

## 🔧 Configuration

### Connecting to Your Java Backend

Edit `gaming-metrics-dashboard/src/api/config.json`:

```json
{
  "activeApi": "local",
  "apis": {
    "local": "http://localhost:8080/api/metrics",
    "prod": "https://api.mygamingmetrics.com/v1/data"
  }
}
```

- **activeApi**: Which API endpoint to use (`local` or `prod`)
- **apis.local**: Your local Java backend URL (default: Spring Boot port 8080)
- **apis.prod**: Your production backend URL

Change `activeApi` to switch between endpoints without code changes.

### Expected Java Backend Response Format

Your Java backend API endpoint should return JSON in this format:

```json
{
  "games": [
    {
      "id": 1,
      "name": "Game Name",
      "avgFps": 144,
      "cpuUsage": 62,
      "gpuUsage": 75,
      "latencyMs": 21,
      "temperatureC": 68
    }
  ]
}
```

**Field Descriptions:**
- `id` (integer) - Unique game identifier
- `name` (string) - Game name/title
- `avgFps` (integer) - Average frames per second
- `cpuUsage` (integer) - CPU usage percentage (0-100)
- `gpuUsage` (integer) - GPU usage percentage (0-100)
- `latencyMs` (integer) - Network latency in milliseconds
- `temperatureC` (integer) - GPU/CPU temperature in Celsius

## ⚡ Performance Optimizations

### Enable Preact

For a lighter bundle (~3KB vs ~40KB), uncomment the Preact aliases in `vite.config.js`:

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

### Lazy Loading

Add lazy loading for heavy components:

```js
const MetricsChart = React.lazy(() => import("../components/MetricsChart"));
```

### Build Optimizations

The Vite config includes:
- Code splitting by vendor (React, MUI, Charts)
- Minification with Terser
- Console stripping in production

## 📱 Build for Production

### Web Application

```bash
npm run build
```

Output will be in the `dist/` directory. Deploy to any static hosting service (Netlify, Vercel, GitHub Pages, AWS S3, etc.).

Preview the production build locally:

```bash
npm run preview
```

### Desktop Application

#### Build for All Platforms

```bash
npm run tauri:build
```

#### Platform-Specific Builds

**Windows**:
```bash
npm run tauri:build:win
```
Creates `.msi` and `.exe` installers in `src-tauri/target/release/bundle/`

**macOS**:
```bash
npm run tauri:build:mac
```
Creates `.dmg` and `.app` in `src-tauri/target/release/bundle/`

**Linux**:
```bash
npm run tauri:build:linux
```
Creates `.deb` and `.AppImage` in `src-tauri/target/release/bundle/`

## 🔄 CI/CD Pipeline

This project includes automated workflows via GitHub Actions:

### 1. Continuous Integration (`ci.yml`)
Runs on every push and pull request:
- ✅ Runs all unit tests
- 📊 Generates code coverage
- 🏗️ Builds web application
- 📤 Uploads build artifacts

### 2. Release Builds (`release.yml`)
Triggered by version tags (e.g., `v1.0.0`):
- 🖥️ Builds desktop apps for Windows, macOS, Linux
- ✅ Runs tests before building
- 📦 Creates GitHub release with installers
- 📤 Uploads platform-specific bundles

**To create a release**:
```bash
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

### 3. GitHub Pages Deployment (`pages.yml`)
Auto-deploys web app on push to `main`:
- 🌐 Deploys to `https://YOUR_USERNAME.github.io/gaming-metrics-dashboard/`
- 🔄 Updates automatically on every commit to main

**Setup**: Enable GitHub Pages in repository settings → Source: GitHub Actions

## 🔌 Java Backend Integration

### Spring Boot REST API (Recommended)

Create a Spring Boot application to serve gaming metrics:

#### 1. Add Dependencies (Maven)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-json</artifactId>
    </dependency>
</dependencies>
```

#### 2. Create Data Model

```java
package com.gaming.metrics.model;

public class GameMetrics {
    private Long id;
    private String name;
    private Integer avgFps;
    private Integer cpuUsage;
    private Integer gpuUsage;
    private Integer latencyMs;
    private Integer temperatureC;
    
    // Constructors, getters, and setters
    public GameMetrics() {}
    
    public GameMetrics(Long id, String name, Integer avgFps, 
                      Integer cpuUsage, Integer gpuUsage, 
                      Integer latencyMs, Integer temperatureC) {
        this.id = id;
        this.name = name;
        this.avgFps = avgFps;
        this.cpuUsage = cpuUsage;
        this.gpuUsage = gpuUsage;
        this.latencyMs = latencyMs;
        this.temperatureC = temperatureC;
    }
    
    // Getters and setters...
}
```

#### 3. Create Response Wrapper

```java
package com.gaming.metrics.model;

import java.util.List;

public class MetricsResponse {
    private List<GameMetrics> games;
    
    public MetricsResponse() {}
    
    public MetricsResponse(List<GameMetrics> games) {
        this.games = games;
    }
    
    public List<GameMetrics> getGames() {
        return games;
    }
    
    public void setGames(List<GameMetrics> games) {
        this.games = games;
    }
}
```

#### 4. Create REST Controller

```java
package com.gaming.metrics.controller;

import com.gaming.metrics.model.GameMetrics;
import com.gaming.metrics.model.MetricsResponse;
import com.gaming.metrics.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from React app
public class MetricsController {
    
    @Autowired
    private MetricsService metricsService;
    
    @GetMapping("/metrics")
    public MetricsResponse getMetrics() {
        return new MetricsResponse(metricsService.getCurrentMetrics());
    }
    
    @GetMapping("/metrics/{gameId}")
    public GameMetrics getGameMetrics(@PathVariable Long gameId) {
        return metricsService.getMetricsById(gameId);
    }
}
```

#### 5. Create Service Layer

```java
package com.gaming.metrics.service;

import com.gaming.metrics.model.GameMetrics;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetricsService {
    
    public List<GameMetrics> getCurrentMetrics() {
        // TODO: Replace with actual metric collection logic
        // This could integrate with system monitoring tools, game APIs, etc.
        
        List<GameMetrics> metrics = new ArrayList<>();
        
        metrics.add(new GameMetrics(
            1L, "Cyber Drift X", 144, 62, 75, 21, 68
        ));
        metrics.add(new GameMetrics(
            2L, "StarForge Arena", 119, 70, 81, 30, 72
        ));
        metrics.add(new GameMetrics(
            3L, "Legends Reborn", 165, 55, 64, 17, 63
        ));
        
        return metrics;
    }
    
    public GameMetrics getMetricsById(Long id) {
        return getCurrentMetrics().stream()
            .filter(m -> m.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}
```

#### 6. Configure CORS (Optional)

```java
package com.gaming.metrics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:5173", "http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*");
            }
        };
    }
}
```

#### 7. Run Spring Boot Application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/metrics`

### Integrating with System Metrics

To collect real gaming metrics, integrate with:

- **JNA (Java Native Access)** - For Windows system metrics
- **OSHI (Operating System and Hardware Information)** - Cross-platform system monitoring
- **Game-specific APIs** - If the game provides telemetry APIs

Example with OSHI:

```xml
<dependency>
    <groupId>com.github.oshi</groupId>
    <artifactId>oshi-core</artifactId>
    <version>6.4.0</version>
</dependency>
```

```java
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

@Service
public class SystemMetricsService {
    
    private final SystemInfo systemInfo = new SystemInfo();
    private final HardwareAbstractionLayer hal = systemInfo.getHardware();
    
    public int getCpuUsage() {
        CentralProcessor processor = hal.getProcessor();
        double[] loads = processor.getSystemLoadAverage(1);
        return (int) (loads[0] * 100);
    }
    
    public int getGpuTemperature() {
        // Implementation depends on GPU monitoring library
        return 70; // Placeholder
    }
}
```

### WebSocket Support (Optional - Real-time Updates)

For live metric streaming instead of polling:

#### 1. Add WebSocket Dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

#### 2. Configure WebSocket

```java
package com.gaming.metrics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/metrics")
            .setAllowedOrigins("http://localhost:5173")
            .withSockJS();
    }
}
```

#### 3. Create WebSocket Controller

```java
package com.gaming.metrics.controller;

import com.gaming.metrics.model.MetricsResponse;
import com.gaming.metrics.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class MetricsWebSocketController {
    
    @Autowired
    private MetricsService metricsService;
    
    @MessageMapping("/metrics")
    @SendTo("/topic/metrics")
    public MetricsResponse sendMetrics() {
        return new MetricsResponse(metricsService.getCurrentMetrics());
    }
    
    @Scheduled(fixedRate = 5000) // Send updates every 5 seconds
    @SendTo("/topic/metrics")
    public MetricsResponse broadcastMetrics() {
        return new MetricsResponse(metricsService.getCurrentMetrics());
    }
}
```

### Testing the Backend

Test your API endpoint:

```bash
curl http://localhost:8080/api/metrics
```

Expected response:
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
    }
  ]
}
```

## 🎨 Customization

### Theme
Edit `src/theme.js` to customize colors, fonts, and component styles:

```js
const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#1976d2', // Change primary color
    },
    secondary: {
      main: '#dc004e', // Change secondary color
    },
    background: {
      default: '#0a1929',
      paper: '#1e293b',
    },
  },
});
```

### Sample Data
Modify `src/data/sampleGames.json` to add more games or change metrics.

### Adding New Metrics

1. Update `src/data/sampleGames.json`:
```json
{
  "id": 1,
  "name": "Game",
  "newMetric": 42
}
```

2. Update Java backend to include new field
3. Update `GameCard.jsx` to display it
4. Add chart in `Dashboard.jsx`
5. Write tests for the new feature

## 🛠️ Development

### Project Commands

| Command | Description |
|---------|-------------|
| `npm run dev` | Start Vite dev server |
| `npm run build` | Build for production |
| `npm run preview` | Preview production build |
| `npm test` | Run unit tests |
| `npm run test:ui` | Run tests with UI |
| `npm run test:coverage` | Generate coverage report |
| `npm run tauri:dev` | Run Tauri in dev mode |
| `npm run tauri:build` | Build desktop app |
| `npm run tauri:build:win` | Build for Windows |
| `npm run tauri:build:mac` | Build for macOS |
| `npm run tauri:build:linux` | Build for Linux |

## 📚 Documentation

- **[QUICKSTART.md](./gaming-metrics-dashboard/QUICKSTART.md)** - Get started in 5 minutes
- **[INSTALLATION.md](./gaming-metrics-dashboard/INSTALLATION.md)** - End-user installation guide
- **[CONTRIBUTING.md](./gaming-metrics-dashboard/CONTRIBUTING.md)** - How to contribute
- **[CHANGELOG.md](./gaming-metrics-dashboard/CHANGELOG.md)** - Version history

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

Please ensure:
- ✅ All tests pass (`npm test`)
- ✅ Code follows existing style
- ✅ Add tests for new features
- ✅ Update documentation as needed

See [CONTRIBUTING.md](./gaming-metrics-dashboard/CONTRIBUTING.md) for detailed guidelines.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](./gaming-metrics-dashboard/LICENSE) file for details.

## 🙏 Acknowledgments

- [React](https://react.dev/) - UI Framework
- [Material-UI](https://mui.com/) - Component Library
- [Recharts](https://recharts.org/) - Charting Library
- [Vite](https://vitejs.dev/) - Build Tool
- [Tauri](https://tauri.app/) - Desktop Framework
- [Vitest](https://vitest.dev/) - Testing Framework
- [Spring Boot](https://spring.io/projects/spring-boot) - Java Backend Framework

## 📞 Support

- 📧 Email: your-email@example.com
- 🐛 Issues: [GitHub Issues](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/discussions)

## 🗺️ Roadmap

- [x] React + MUI Dashboard
- [x] Interactive Charts
- [x] Java Backend Integration
- [x] Unit Tests (25/25 passing)
- [x] Tauri Desktop App
- [x] CI/CD Pipeline
- [x] Cross-platform Builds
- [ ] WebSocket Real-time Updates
- [ ] Historical Data Tracking
- [ ] Custom Alert Thresholds
- [ ] Export Metrics (CSV/JSON)
- [ ] Multi-language Support
- [ ] Auto-update for Desktop App
- [ ] System Tray Integration
- [ ] Performance Analytics & Recommendations

---

**Built with ❤️ for gamers by gamers** 🎮🚀
