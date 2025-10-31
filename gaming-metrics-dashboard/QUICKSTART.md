# Quick Start Guide

Get started with Gaming Metrics Dashboard in 5 minutes!

## 🌐 Web Application

### 1. Clone & Install

```bash
git clone https://github.com/YOUR_USERNAME/gaming-metrics-dashboard.git
cd gaming-metrics-dashboard
npm install
```

### 2. Start Development Server

```bash
npm run dev
```

Open http://localhost:5173 in your browser. You'll see the dashboard with sample data.

### 3. Connect Your Backend (Optional)

Edit `src/api/config.json`:

```json
{
  "activeApi": "local",
  "apis": {
    "local": "http://localhost:8080/api/metrics"
  }
}
```

Your API should return:

```json
{
  "games": [
    {
      "id": 1,
      "name": "Your Game",
      "avgFps": 144,
      "cpuUsage": 62,
      "gpuUsage": 75,
      "latencyMs": 21,
      "temperatureC": 68
    }
  ]
}
```

## 💻 Desktop Application

### Prerequisites

- Install [Rust](https://www.rust-lang.org/tools/install)
- Install Node.js 18+

### 1. Clone & Install

```bash
git clone https://github.com/YOUR_USERNAME/gaming-metrics-dashboard.git
cd gaming-metrics-dashboard
npm install
```

### 2. Run Desktop App

```bash
npm run tauri:dev
```

The desktop window will open with the dashboard.

### 3. Build Installer

```bash
npm run tauri:build
```

Find your installer in `src-tauri/target/release/bundle/`:
- **Windows**: `.msi` or `.exe`
- **macOS**: `.dmg` or `.app`
- **Linux**: `.deb` or `.AppImage`

## 🧪 Run Tests

```bash
npm test
```

All tests should pass!

## 📦 Deploy to Production

### Web (GitHub Pages)

1. Push to GitHub
2. Enable GitHub Pages in repository settings
3. The app auto-deploys on push to `main`

### Desktop

1. Create a git tag: `git tag v1.0.0`
2. Push tag: `git push origin v1.0.0`
3. GitHub Actions builds installers automatically
4. Download from Releases page

## 🎨 Customize

### Change Theme

Edit `src/theme.js`:

```js
primary: {
  main: '#YOUR_COLOR', // Change primary color
}
```

### Add More Games

Edit `src/data/sampleGames.json`:

```json
{
  "games": [
    {
      "id": 4,
      "name": "New Game",
      "avgFps": 120,
      "cpuUsage": 55,
      "gpuUsage": 70,
      "latencyMs": 25,
      "temperatureC": 65
    }
  ]
}
```

## 🆘 Troubleshooting

### Port Already in Use

If port 5173 is busy:

```bash
npm run dev -- --port 3000
```

### Tests Failing

Clear cache and reinstall:

```bash
rm -rf node_modules package-lock.json
npm install
npm test
```

### Rust Not Found (Tauri)

Install Rust:

```bash
# Windows (PowerShell)
winget install --id Rustlang.Rustup

# macOS/Linux
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

## 📚 Next Steps

- Read the full [README.md](README.md)
- Check out [CONTRIBUTING.md](CONTRIBUTING.md) to contribute
- Browse [GitHub Issues](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/issues) for known issues

## 🎮 Enjoy!

You're all set! Monitor your gaming performance like a pro! 🚀
