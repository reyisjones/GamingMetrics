# Gaming Metrics Dashboard

[![CI/CD Pipeline](https://github.com/reyisjones/GamingMetrics/workflows/CI%2FCD%20Pipeline/badge.svg)](https://github.com/reyisjones/GamingMetrics/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Node Version](https://img.shields.io/badge/node-%3E%3D18.0.0-brightgreen)](https://nodejs.org)
[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)](https://spring.io/projects/spring-boot)

A complete **full-stack Gaming Performance Dashboard** with a **React + Material UI (MUI)** frontend and **Java Spring Boot** backend. Monitor real-time gaming metrics (FPS, CPU/GPU usage, latency, temperature) with advanced analytics, performance calculations, and live streaming via Server-Sent Events. Available as both a **web application** and **cross-platform desktop app** (Windows, macOS, Linux) via **Tauri**.

## 🚀 Features

### Frontend
- 🎮 **Real-time Gaming Metrics Display**: FPS, CPU/GPU Usage, Latency, Temperature
- 📊 **Interactive Charts**: Visualize performance metrics with Recharts
- 🌙 **Dark Theme**: Modern gaming-themed UI with Material-UI
-  **Fallback Data**: Uses sample data when backend is unavailable
- ⚡ **Live Updates**: Auto-refresh every 30 seconds
- 📱 **Responsive Design**: Works on desktop and mobile devices
- 🚀 **Performance Optimized**: Code splitting, lazy loading support
- 🪶 **Preact Ready**: Optional lightweight bundle (3KB vs 40KB)
- 💻 **Desktop App**: Cross-platform desktop application via Tauri
- ✅ **Fully Tested**: 25 unit tests (all passing)

### Backend
- ☕ **Java Spring Boot 3.2.0**: Enterprise-grade REST API
- 🔌 **RESTful Endpoints**: `/api/metrics`, `/analyze`, `/live`, `/test`
- 📈 **Performance Calculations**: Weighted scoring algorithm
- 🧮 **Stability Index**: CPU/GPU load balance measurement
- 📊 **Analytics Service**: Variance analysis and recommendations
- 🔴 **Real-time Streaming**: Server-Sent Events (SSE) every 3 seconds
- ⏰ **Scheduled Monitoring**: Automated metrics aggregation (10s intervals)
- 🎯 **Anomaly Detection**: High temp, low FPS, latency spikes
- 🧪 **Comprehensive Testing**: 37+ unit and integration tests
- 🔄 **CI/CD Ready**: Automated testing and deployment

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
- **Spring Boot 3.2.0** - Enterprise Java framework
- **Java 17+** - Modern Java runtime
- **Maven** - Dependency management and build tool
- **Spring Web** - REST API endpoints
- **Spring Actuator** - Health monitoring
- **Spring Cache** - Caching support
- **Spring WebSocket** - SSE support for real-time streaming
- **Lombok** - Reduces boilerplate code
- **JUnit 5** - Comprehensive testing framework (37+ tests)
- **Jackson** - JSON serialization
- See [Backend README](backend/README.md) for full documentation

## 🏗️ Project Structure

```
GamingMetrics/
├── .github/
│   └── workflows/
│       ├── ci.yml              # CI/CD pipeline for frontend
│       ├── release.yml         # Desktop app builds (Tauri)
│       └── pages.yml           # GitHub Pages deployment
├── backend/                    # ☕ Java Spring Boot Backend
│   ├── src/
│   │   ├── main/java/com/gameperf/api/
│   │   │   ├── GamingMetricsApplication.java    # Main app
│   │   │   ├── GameMetricsController.java       # REST endpoints
│   │   │   ├── GameMetricsService.java          # Business logic
│   │   │   ├── GameAnalyticsService.java        # Analytics
│   │   │   ├── GameSessionScheduler.java        # Scheduled tasks
│   │   │   └── model/
│   │   │       └── GameMetric.java              # Data model
│   │   ├── main/resources/
│   │   │   └── application.properties           # Spring config
│   │   └── test/java/com/gameperf/api/
│   │       ├── GameMetricsServiceTest.java      # 9 tests
│   │       ├── GameAnalyticsServiceTest.java    # 12 tests
│   │       ├── GameMetricsControllerTest.java   # 11 tests
│   │       └── model/GameMetricTest.java        # 5 tests
│   ├── pom.xml                 # Maven configuration
│   ├── README.md               # Backend documentation
│   ├── BUILD.md                # Build & test instructions
│   ├── run-tests.sh            # Test automation script
│   └── start-server.sh         # Server startup script
├── gaming-metrics-dashboard/   # ⚛️ React Frontend
│   ├── src/
│   │   ├── api/
│   │   │   └── config.json     # Backend endpoint configuration
│   │   ├── components/
│   │   │   ├── GameCard.jsx    # Individual game metric card
│   │   │   ├── MetricsChart.jsx    # Reusable chart component
│   │   │   └── Header.jsx      # App header with live indicator
│   │   ├── pages/
│   │   │   └── Dashboard.jsx   # Main dashboard page
│   │   ├── data/
│   │   │   └── sampleGames.json    # Sample/fallback data
│   │   ├── test/
│   │   │   ├── setup.js        # Test configuration
│   │   │   ├── Header.test.jsx
│   │   │   ├── GameCard.test.jsx
│   │   │   ├── MetricsChart.test.jsx
│   │   │   └── Dashboard.test.jsx
│   │   ├── App.jsx             # Root component
│   │   ├── main.jsx            # App entry point
│   │   └── theme.js            # MUI theme configuration
│   ├── src-tauri/
│   │   ├── src/
│   │   │   └── main.rs         # Tauri main process
│   │   ├── icons/              # App icons
│   │   ├── Cargo.toml          # Rust dependencies
│   │   └── tauri.conf.json     # Tauri configuration
│   ├── package.json
│   ├── vite.config.js          # Vite configuration
│   ├── vitest.config.js        # Vitest configuration
│   ├── README.md
│   ├── QUICKSTART.md           # 5-minute setup guide
│   ├── INSTALLATION.md         # End-user installation guide
│   └── CONTRIBUTING.md         # Developer contribution guide
├── JavaFeatures.md             # Backend feature specification
├── BACKEND_IMPLEMENTATION.md   # Backend implementation summary
├── README.md                   # This file
└── LICENSE                     # MIT License
```
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

- **Node.js** 18+ and npm (for frontend)
- **Java 17+** and **Maven** (for backend)
- **Rust** (for Tauri desktop builds) - [Install Rust](https://www.rust-lang.org/tools/install)

### Backend Setup (Java Spring Boot)

1. **Navigate to backend directory**:
```bash
cd backend
```

2. **Build and run tests**:
```bash
mvn clean test
```

3. **Start the backend server**:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

**API Endpoints:**
- `GET http://localhost:8080/api/metrics` - Get all game metrics
- `GET http://localhost:8080/api/metrics/analyze` - Get analysis summary
- `GET http://localhost:8080/api/metrics/live` - Stream live updates (SSE)
- `GET http://localhost:8080/api/metrics/test` - Health check

**Documentation:** See [backend/README.md](backend/README.md) and [backend/BUILD.md](backend/BUILD.md)

### Frontend Setup (React Web App)

1. **Navigate to frontend directory**:
```bash
cd gaming-metrics-dashboard
```

2. **Install dependencies**:
```bash
npm install
```

3. **Configure backend endpoint** (optional):

Edit `src/api/config.json`:
```json
{
  "apis": "http://localhost:8080/api/metrics"
}
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
- 🌐 Deploys to `https://reyisjones.github.io/GamingMetrics/`
- 🔄 Updates automatically on every commit to main

**Setup**: Enable GitHub Pages in repository settings → Source: GitHub Actions

## 🔌 Java Backend Integration

### Spring Boot REST API (Included)

This repository includes a complete **Spring Boot 3.2.0** backend implementation with advanced features:

#### Backend Features

✅ **REST API Endpoints**
- `GET /api/metrics` - Returns all game metrics with calculated scores
- `GET /api/metrics/analyze` - Returns analysis summary with averages
- `GET /api/metrics/live` - Streams real-time updates via SSE (every 3s)
- `GET /api/metrics/test` - Health check endpoint

✅ **Performance Calculations**
- **Performance Score**: `(fps × 0.6) - (cpu × 0.15) - (gpu × 0.15) - (latency × 0.1)`
- **Stability Index**: `100 - |cpu - gpu| × 0.6`
- Normalized to 0-100 range

✅ **Analytics Service**
- Session variance analysis (FPS, CPU, GPU, latency)
- Stability detection and recommendations
- Anomaly detection (high temp, low FPS, high latency)

✅ **Real-Time Features**
- Server-Sent Events (SSE) streaming
- Live metrics updates every 3 seconds
- Automatic client reconnection

✅ **Scheduled Monitoring**
- Metrics aggregation every 10 seconds
- Health check heartbeat every 30 seconds
- Console logging of summaries

✅ **Comprehensive Testing**
- 37+ unit and integration tests
- JUnit 5 with MockMvc
- Test coverage for all services and controllers

#### Backend Quick Start

```bash
# Navigate to backend
cd backend

# Run tests
mvn clean test

# Start server (runs on port 8080)
mvn spring-boot:run

# Or using the startup script
./start-server.sh
```

#### Backend Documentation

- **[backend/README.md](backend/README.md)** - Complete API documentation, setup guide, and examples
- **[backend/BUILD.md](backend/BUILD.md)** - Build and test instructions
- **[BACKEND_IMPLEMENTATION.md](BACKEND_IMPLEMENTATION.md)** - Implementation summary

#### Sample Backend Response

```json
[
  {
    "name": "Cyber Drift X",
    "avgFps": 144,
    "cpuUsage": 62,
    "gpuUsage": 75,
    "latencyMs": 21,
    "temperatureC": 68,
    "performanceScore": 72.45,
    "stabilityIndex": 92.2
  }
]
```

#### Build Your Own Backend

If you want to create a custom backend, see the example implementation below or use the included backend as reference.
    
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

### Frontend Documentation
- **[gaming-metrics-dashboard/README.md](./gaming-metrics-dashboard/README.md)** - Frontend documentation
- **[QUICKSTART.md](./gaming-metrics-dashboard/QUICKSTART.md)** - Get started in 5 minutes
- **[INSTALLATION.md](./gaming-metrics-dashboard/INSTALLATION.md)** - End-user installation guide
- **[CONTRIBUTING.md](./gaming-metrics-dashboard/CONTRIBUTING.md)** - How to contribute
- **[CHANGELOG.md](./gaming-metrics-dashboard/CHANGELOG.md)** - Version history

### Backend Documentation
- **[backend/README.md](./backend/README.md)** - Complete backend API documentation
- **[backend/BUILD.md](./backend/BUILD.md)** - Build and test instructions
- **[BACKEND_IMPLEMENTATION.md](./BACKEND_IMPLEMENTATION.md)** - Implementation summary
- **[JavaFeatures.md](./JavaFeatures.md)** - Backend feature specification

## 📊 Project Statistics

### Frontend (React + Tauri)
- **Files**: 38 source files
- **Lines of Code**: 10,048+
- **Components**: 3 main components (Header, GameCard, MetricsChart)
- **Pages**: 1 dashboard page
- **Tests**: 25 unit tests (all passing)
- **Test Coverage**: Comprehensive coverage for all components

### Backend (Java Spring Boot)
- **Files**: 18 source files
- **Lines of Code**: 2,255+
- **REST Endpoints**: 4 API endpoints
- **Services**: 3 service classes
- **Tests**: 37+ unit and integration tests (all passing)
- **Test Coverage**: Full coverage for services and controllers

### Total Project
- **Total Files**: 56+ files
- **Total Lines of Code**: 12,303+
- **Total Tests**: 62+ tests (all passing)
- **Languages**: JavaScript/JSX, Java, Rust, CSS
- **Frameworks**: React 18, Spring Boot 3.2.0, Tauri 1.5

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
- 🐛 Issues: [GitHub Issues](https://github.com/reyisjones/GamingMetrics/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/reyisjones/GamingMetrics/discussions)

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
