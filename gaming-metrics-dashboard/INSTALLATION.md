# Installation Guide for End Users

This guide is for users who want to install and use the Gaming Metrics Dashboard application.

## 📥 Desktop Application Installation

### Windows

#### Option 1: MSI Installer (Recommended)

1. **Download the installer**
   - Go to the [Releases](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/releases) page
   - Download the latest `.msi` file (e.g., `gaming-metrics-dashboard_1.0.0_x64_en-US.msi`)

2. **Run the installer**
   - Double-click the downloaded `.msi` file
   - If Windows SmartScreen appears, click "More info" → "Run anyway"
   - Follow the installation wizard
   - Choose installation location (default: `C:\Program Files\Gaming Metrics Dashboard`)

3. **Launch the app**
   - Find "Gaming Metrics Dashboard" in your Start Menu
   - Or use the desktop shortcut (if created during installation)

#### Option 2: EXE Installer

1. Download the `.exe` installer from the Releases page
2. Run the installer and follow the prompts
3. Launch from Start Menu or Desktop

### macOS

#### Installation Steps

1. **Download the DMG**
   - Go to the [Releases](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/releases) page
   - Download the latest `.dmg` file (e.g., `gaming-metrics-dashboard_1.0.0_x64.dmg`)

2. **Install the app**
   - Double-click the downloaded `.dmg` file
   - Drag the "Gaming Metrics Dashboard" icon to the Applications folder
   - Eject the DMG

3. **First launch**
   - Open Applications folder
   - Right-click "Gaming Metrics Dashboard" → "Open"
   - Click "Open" in the security dialog (first time only)
   - Subsequent launches: double-click normally

> **Note**: macOS may show a security warning for unsigned applications. This is normal for community-built apps.

### Linux

#### Option 1: Debian/Ubuntu (.deb package)

1. **Download the package**
   ```bash
   wget https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/releases/download/v1.0.0/gaming-metrics-dashboard_1.0.0_amd64.deb
   ```

2. **Install**
   ```bash
   sudo dpkg -i gaming-metrics-dashboard_1.0.0_amd64.deb
   ```

3. **Fix dependencies (if needed)**
   ```bash
   sudo apt-get install -f
   ```

4. **Launch**
   ```bash
   gaming-metrics-dashboard
   ```
   Or find it in your applications menu.

#### Option 2: AppImage (Universal)

1. **Download AppImage**
   ```bash
   wget https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/releases/download/v1.0.0/gaming-metrics-dashboard_1.0.0_amd64.AppImage
   ```

2. **Make it executable**
   ```bash
   chmod +x gaming-metrics-dashboard_1.0.0_amd64.AppImage
   ```

3. **Run**
   ```bash
   ./gaming-metrics-dashboard_1.0.0_amd64.AppImage
   ```

#### Option 3: RPM (Fedora/RHEL)

1. **Download RPM**
   ```bash
   wget https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/releases/download/v1.0.0/gaming-metrics-dashboard-1.0.0.x86_64.rpm
   ```

2. **Install**
   ```bash
   sudo rpm -i gaming-metrics-dashboard-1.0.0.x86_64.rpm
   ```

## 🌐 Web Application Access

### Access the Live Demo

Simply open your browser and go to:
```
https://YOUR_USERNAME.github.io/gaming-metrics-dashboard/
```

No installation required! Works on any device with a modern web browser.

### Supported Browsers

- ✅ Google Chrome (recommended)
- ✅ Microsoft Edge
- ✅ Firefox
- ✅ Safari
- ✅ Brave
- ✅ Opera

Minimum versions:
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

## 🔧 Configuration

### Connecting to Your Gaming PC

The desktop app needs to connect to a backend server that provides gaming metrics.

1. **Find the configuration file**
   - Windows: `%APPDATA%\com.gamingmetrics.dashboard\config.json`
   - macOS: `~/Library/Application Support/com.gamingmetrics.dashboard/config.json`
   - Linux: `~/.config/com.gamingmetrics.dashboard/config.json`

2. **Edit the configuration**
   ```json
   {
     "activeApi": "local",
     "apis": {
       "local": "http://localhost:8080/api/metrics",
       "remote": "http://YOUR_PC_IP:8080/api/metrics"
     }
   }
   ```

3. **Restart the application**

### Setting up the Backend (Developer Guide)

If you need to set up a backend server, see the [Developer Documentation](README.md#-java-backend-integration).

## 🚀 First Use

### 1. Launch the Application

After installation, launch the app using your preferred method (Start Menu, Applications folder, terminal, etc.).

### 2. View Sample Data

By default, the dashboard shows sample data for three games:
- Cyber Drift X
- StarForge Arena
- Legends Reborn

This lets you explore the interface before connecting to real metrics.

### 3. Understanding the Dashboard

The dashboard displays:

#### Game Cards
Each card shows:
- **FPS** (Frames Per Second)
  - 🟢 Green: ≥144 FPS (Excellent)
  - 🟡 Yellow: 60-143 FPS (Good)
  - 🔴 Red: <60 FPS (Poor)
- **CPU Usage** (percentage)
- **GPU Usage** (percentage)
- **Latency** (milliseconds)
- **Temperature** (Celsius)

#### Charts
Four interactive charts show metrics across all games:
- Average FPS comparison
- CPU Usage comparison
- GPU Usage comparison
- Latency comparison

### 4. Live Updates

When connected to a real backend:
- Metrics update every 30 seconds
- Live indicator shows connection status
- Automatic fallback to sample data if connection fails

## 🛠️ Troubleshooting

### App Won't Start

**Windows:**
- Ensure you have the latest Windows updates
- Try running as Administrator
- Check Windows Defender/antivirus isn't blocking it

**macOS:**
- Go to System Preferences → Security & Privacy
- Click "Open Anyway" for the app

**Linux:**
- Ensure required dependencies are installed:
  ```bash
  sudo apt install libwebkit2gtk-4.0-37 libgtk-3-0
  ```

### Connection Issues

If "Using sample data" appears:
1. Verify your backend server is running
2. Check the API endpoint in configuration
3. Ensure firewall isn't blocking the connection
4. Try accessing the API URL directly in a browser

### Performance Issues

- **Close unnecessary background apps**
- **Ensure your system meets minimum requirements**:
  - Windows 10+ / macOS 10.15+ / Ubuntu 20.04+
  - 4GB RAM minimum
  - Modern CPU (2015+)

## 🔄 Updating

### Desktop App

1. Download the latest version from Releases
2. Run the new installer
3. It will automatically upgrade the existing installation
4. Your settings will be preserved

### Web App

The web version updates automatically. Just refresh your browser to get the latest version.

## 🗑️ Uninstallation

### Windows

1. Open Settings → Apps → Apps & features
2. Find "Gaming Metrics Dashboard"
3. Click Uninstall

Or use Control Panel → Programs → Uninstall a program

### macOS

1. Open Applications folder
2. Drag "Gaming Metrics Dashboard" to Trash
3. Empty Trash

Optional: Remove config files:
```bash
rm -rf ~/Library/Application\ Support/com.gamingmetrics.dashboard
```

### Linux (Debian/Ubuntu)

```bash
sudo apt remove gaming-metrics-dashboard
```

### Linux (RPM)

```bash
sudo rpm -e gaming-metrics-dashboard
```

### Linux (AppImage)

Simply delete the `.AppImage` file.

## 📞 Support

### Getting Help

- 📧 Email: support@example.com
- 🐛 Report bugs: [GitHub Issues](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/issues)
- 💬 Community: [Discussions](https://github.com/YOUR_USERNAME/gaming-metrics-dashboard/discussions)
- 📖 Documentation: [README](README.md)

### FAQ

**Q: Is this app safe?**
A: Yes! The code is open source and can be audited. No data is collected or sent anywhere except your configured backend.

**Q: Why does my antivirus flag it?**
A: Some antivirus software flags unsigned applications. You can safely allow this app.

**Q: Does it work offline?**
A: Yes! The app shows sample data when offline. For live metrics, you need a backend connection.

**Q: Is it free?**
A: Yes! This is open-source software under the MIT license.

**Q: Can I customize it?**
A: Yes! The source code is available. See the [Contributing Guide](CONTRIBUTING.md).

## 🎮 Enjoy!

You're all set to monitor your gaming performance! If you encounter any issues, don't hesitate to reach out for support.

---

**Happy Gaming! 🚀**
