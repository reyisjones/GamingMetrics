# Gaming Metrics Dashboard

[![CI/CD Pipeline](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/workflows/CI%2FCD%20Pipeline/badge.svg)](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Node Version](https://img.shields.io/badge/node-%3E%3D18.0.0-brightgreen)](https://nodejs.org)

A fast, responsive **Gaming Performance Dashboard** built with **React + Material UI (MUI)** and optimized for performance with **Vite**. Available as both a **web application** and **cross-platform desktop app** (Windows, macOS, Linux) via **Tauri**.

![Dashboard Preview](docs/screenshot.png)

## ✨ Features

- 🎮 **Real-time Gaming Metrics Display**: FPS, CPU/GPU Usage, Latency, Temperature
- 📊 **Interactive Charts**: Visualize performance metrics with Recharts
- 🌙 **Dark Theme**: Modern gaming-themed UI with Material-UI
- 🔌 **API Integration**: Configurable endpoints for Java backend connection
- 💾 **Fallback Data**: Uses sample data when API is unavailable
- ⚡ **Live Updates**: Polls API every 30 seconds for fresh metrics
- 📱 **Responsive Design**: Works on desktop and mobile devices
- 🚀 **Performance Optimized**: Code splitting, lazy loading support
- 🪶 **Preact Ready**: Uncomment aliases in vite.config.js for lighter bundle
- 💻 **Desktop App**: Cross-platform desktop application via Tauri
- ✅ **Fully Tested**: Comprehensive unit tests with Vitest
- 🔄 **CI/CD Ready**: Automated testing, building, and deployment

## 📦 Tech Stack

- **React 18** - UI framework
- **Material-UI (MUI) v5** - Component library
- **Recharts** - Data visualization
- **Vite** - Build tool and dev server
- **Vitest** - Unit testing framework
- **Tauri** - Desktop app framework
- **Preact** (optional) - Lighter React alternative
- **Emotion** - CSS-in-JS styling

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
│   │   └── config.json         # API endpoint configuration
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
└── README.md
```

## 🚀 Quick Start

### Prerequisites

- **Node.js** 18+ and npm
- **Rust** (for Tauri desktop builds) - [Install Rust](https://www.rust-lang.org/tools/install)

### Web Application

1. **Clone the repository**:
```bash
git clone https://github.com/YOUR_USERNAME/gaming-metrics-dashboard.git
cd gaming-metrics-dashboard
```

2. **Install dependencies**:
```bash
npm install
```

3. **Start the development server**:
```bash
npm run dev
```

4. **Open your browser** to `http://localhost:5173`

### Desktop Application

1. **Follow steps 1-2 from Web Application**

2. **Install Tauri CLI** (if not already installed):
```bash
npm install -g @tauri-apps/cli
```

3. **Run in development mode**:
```bash
npm run tauri:dev
```

4. **Build for production**:
```bash
npm run tauri:build
```

Installers will be created in `src-tauri/target/release/bundle/`

## 🧪 Testing

### Run Unit Tests

```bash
npm test
```

### Run Tests in Watch Mode

```bash
npm test -- --watch
```

### Run Tests with UI

```bash
npm run test:ui
```

### Generate Coverage Report

```bash
npm run test:coverage
```

Coverage reports will be available in the `coverage/` directory.

## 🔧 Configuration

### API Endpoints

Edit `src/api/config.json` to configure your backend:

```json
{
  "activeApi": "local",
  "apis": {
    "local": "http://localhost:8080/api/metrics",
    "prod": "https://api.mygamingmetrics.com/v1/data"
  }
}
```

Change `activeApi` to switch between endpoints.

### Expected API Response Format

Your backend API should return JSON in this format:

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

## 📱 Build for Production

### Web Application

```bash
npm run build
```

Output will be in the `dist/` directory. Deploy to any static hosting service.

### Desktop Application

#### Windows

```bash
npm run tauri:build:win
```

Creates `.msi` and `.exe` installers in `src-tauri/target/release/bundle/`

#### macOS

```bash
npm run tauri:build:mac
```

Creates `.dmg` and `.app` in `src-tauri/target/release/bundle/`

#### Linux

```bash
npm run tauri:build:linux
```

Creates `.deb` and `.AppImage` in `src-tauri/target/release/bundle/`

## 🔄 CI/CD Pipeline

This project includes three GitHub Actions workflows:

### 1. CI/CD Pipeline (`.github/workflows/ci.yml`)

Runs on every push and pull request:
- ✅ Runs unit tests
- 📊 Generates coverage reports
- 🏗️ Builds web application
- 📤 Uploads artifacts

### 2. Desktop Release (`.github/workflows/release.yml`)

Triggered by version tags (e.g., `v1.0.0`):
- 🖥️ Builds for Windows, macOS, and Linux
- ✅ Runs tests before building
- 📦 Creates GitHub release with installers
- 📤 Uploads platform-specific bundles

**To create a release**:

```bash
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

### 3. GitHub Pages Deployment (`.github/workflows/pages.yml`)

Deploys web app to GitHub Pages on main branch updates:
- 🌐 Automatically deploys to `https://YOUR_USERNAME.github.io/gaming-metrics-dashboard/`

**Setup**:
1. Go to repository Settings → Pages
2. Source: GitHub Actions
3. The workflow will deploy on the next push to main

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
- ✅ Code splitting by vendor (React, MUI, Charts)
- ✅ Minification with Terser
- ✅ Console stripping in production
- ✅ Compression ready

## 🔌 Java Backend Integration

### REST API

Your Java backend should expose an endpoint matching your config:

```java
@RestController
@RequestMapping("/api")
public class MetricsController {
    
    @GetMapping("/metrics")
    public ResponseEntity<GameMetricsResponse> getMetrics() {
        // Return game metrics
        return ResponseEntity.ok(metricsService.getMetrics());
    }
}
```

### WebSocket (Future Enhancement)

For live updates, implement WebSocket in `src/api/socket.js`:

```js
const ws = new WebSocket('ws://localhost:8080/ws/metrics');
ws.onmessage = (event) => {
  const data = JSON.parse(event.data);
  // Update state with new metrics
};
```

## 📥 Installation Instructions (for End Users)

### Windows

1. Download the `.msi` installer from the [Releases](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/releases) page
2. Run the installer
3. Follow the installation wizard
4. Launch from Start Menu or Desktop shortcut

### macOS

1. Download the `.dmg` file from the [Releases](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/releases) page
2. Open the `.dmg` file
3. Drag the app to Applications folder
4. Launch from Applications or Launchpad

### Linux

**Debian/Ubuntu (using .deb)**:
```bash
sudo dpkg -i gaming-metrics-dashboard_1.0.0_amd64.deb
```

**AppImage**:
```bash
chmod +x gaming-metrics-dashboard_1.0.0_amd64.AppImage
./gaming-metrics-dashboard_1.0.0_amd64.AppImage
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
    // ... more customization
  },
});
```

### Sample Data

Modify `src/data/sampleGames.json` to add more games or change metrics.

### Charts

Customize chart appearance in `src/components/MetricsChart.jsx`.

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

### Adding New Metrics

1. Update `src/data/sampleGames.json` with new field
2. Update TypeScript interfaces (if using TypeScript)
3. Add display logic to `GameCard.jsx`
4. Add new chart in `Dashboard.jsx`
5. Write tests for new features

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

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [React](https://react.dev/) - UI Framework
- [Material-UI](https://mui.com/) - Component Library
- [Recharts](https://recharts.org/) - Charting Library
- [Vite](https://vitejs.dev/) - Build Tool
- [Tauri](https://tauri.app/) - Desktop Framework
- [Vitest](https://vitest.dev/) - Testing Framework

## 📞 Support

- 📧 Email: your-email@example.com
- 🐛 Issues: [GitHub Issues](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/discussions)

## 🗺️ Roadmap

- [ ] WebSocket support for real-time updates
- [ ] Historical data tracking and trends
- [ ] Custom alert thresholds
- [ ] Export metrics to CSV/JSON
- [ ] Multi-language support
- [ ] Auto-update for desktop app
- [ ] System tray integration
- [ ] Performance analytics and recommendations

---

**Built with ❤️ for gamers by gamers**
