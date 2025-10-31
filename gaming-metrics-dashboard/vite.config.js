import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      // Uncomment the following lines to enable Preact optimization
      // react: "preact/compat",
      // "react-dom/test-utils": "preact/test-utils",
      // "react-dom": "preact/compat",
      // "react/jsx-runtime": "preact/jsx-runtime"
    }
  },
  build: {
    // Enable code splitting
    rollupOptions: {
      output: {
        manualChunks: {
          'react-vendor': ['react', 'react-dom'],
          'mui-vendor': ['@mui/material', '@mui/icons-material'],
          'chart-vendor': ['recharts']
        }
      }
    },
    // Enable compression
    minify: 'terser',
    terserOptions: {
      compress: {
        drop_console: true
      }
    }
  }
});
