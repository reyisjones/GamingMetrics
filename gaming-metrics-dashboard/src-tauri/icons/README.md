# Tauri Icon Placeholder

To generate proper icons for your Tauri application, you'll need to:

1. Create a source icon (PNG, at least 512x512px recommended)
2. Use the Tauri icon generator:

```bash
npm run tauri icon path/to/your-icon.png
```

This will automatically generate all required icon sizes for Windows, macOS, and Linux.

## Required Icon Sizes:

- **Windows**: icon.ico (multi-size)
- **macOS**: icon.icns (multi-size)
- **Linux**: 32x32.png, 128x128.png, 128x128@2x.png

## Temporary Setup:

For now, you can use a placeholder or download free gaming-themed icons from:
- https://www.flaticon.com/
- https://www.iconfinder.com/
- https://iconmonstr.com/

Place your source icon in this directory and run the command above to generate all sizes.
