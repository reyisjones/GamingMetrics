package com.gameperf.api;

import com.gameperf.api.model.GameMetric;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service for analyzing game session data.
 * Provides statistical analysis and performance recommendations.
 */
@Service
public class GameAnalyticsService {

    /**
     * Analyze a game session based on collected metrics.
     * Calculates variance and provides stability recommendations.
     * 
     * @param sessionData List of GameMetric objects from a gaming session
     * @return Map containing analysis results and recommendations
     */
    public Map<String, Object> analyzeSession(List<GameMetric> sessionData) {
        if (sessionData == null || sessionData.isEmpty()) {
            return Map.of(
                "error", "No session data provided",
                "sessionStable", false,
                "recommendation", "Unable to analyze empty session"
            );
        }
        
        // Extract FPS values for variance calculation
        int[] fpsValues = sessionData.stream()
                .mapToInt(GameMetric::getAvgFps)
                .toArray();
        
        // Extract CPU and GPU values for load analysis
        int[] cpuValues = sessionData.stream()
                .mapToInt(GameMetric::getCpuUsage)
                .toArray();
        
        int[] gpuValues = sessionData.stream()
                .mapToInt(GameMetric::getGpuUsage)
                .toArray();
        
        // Extract latency values
        int[] latencyValues = sessionData.stream()
                .mapToInt(GameMetric::getLatencyMs)
                .toArray();
        
        // Calculate statistics
        double fpsVariance = calculateVariance(fpsValues);
        double cpuVariance = calculateVariance(cpuValues);
        double gpuVariance = calculateVariance(gpuValues);
        double latencyVariance = calculateVariance(latencyValues);
        
        double avgFps = Arrays.stream(fpsValues).average().orElse(0);
        double avgCpu = Arrays.stream(cpuValues).average().orElse(0);
        double avgGpu = Arrays.stream(gpuValues).average().orElse(0);
        double avgLatency = Arrays.stream(latencyValues).average().orElse(0);
        
        // Determine stability based on variance thresholds
        boolean fpsUnstable = fpsVariance > 50;
        boolean cpuUnstable = cpuVariance > 30;
        boolean gpuUnstable = gpuVariance > 30;
        boolean latencyUnstable = latencyVariance > 20;
        
        boolean sessionStable = !fpsUnstable && !cpuUnstable && !gpuUnstable && !latencyUnstable;
        
        // Generate recommendation
        String recommendation = generateRecommendation(
            fpsUnstable, cpuUnstable, gpuUnstable, latencyUnstable,
            avgFps, avgCpu, avgGpu, avgLatency
        );
        
        return Map.of(
            "sessionStable", sessionStable,
            "fpsVariance", Math.round(fpsVariance * 100.0) / 100.0,
            "cpuVariance", Math.round(cpuVariance * 100.0) / 100.0,
            "gpuVariance", Math.round(gpuVariance * 100.0) / 100.0,
            "latencyVariance", Math.round(latencyVariance * 100.0) / 100.0,
            "averageFps", Math.round(avgFps * 100.0) / 100.0,
            "averageCpuUsage", Math.round(avgCpu * 100.0) / 100.0,
            "averageGpuUsage", Math.round(avgGpu * 100.0) / 100.0,
            "averageLatency", Math.round(avgLatency * 100.0) / 100.0,
            "recommendation", recommendation
        );
    }

    /**
     * Calculate variance for a set of values.
     * Variance measures how spread out the values are from the mean.
     * 
     * @param values Array of integer values
     * @return Variance value
     */
    private double calculateVariance(int[] values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        
        double mean = Arrays.stream(values).average().orElse(0);
        
        return Arrays.stream(values)
                .mapToDouble(v -> Math.pow(v - mean, 2))
                .average()
                .orElse(0);
    }

    /**
     * Generate performance recommendation based on analysis results.
     * 
     * @param fpsUnstable Whether FPS variance is high
     * @param cpuUnstable Whether CPU variance is high
     * @param gpuUnstable Whether GPU variance is high
     * @param latencyUnstable Whether latency variance is high
     * @param avgFps Average FPS
     * @param avgCpu Average CPU usage
     * @param avgGpu Average GPU usage
     * @param avgLatency Average latency
     * @return Recommendation string
     */
    private String generateRecommendation(
            boolean fpsUnstable, boolean cpuUnstable, boolean gpuUnstable, boolean latencyUnstable,
            double avgFps, double avgCpu, double avgGpu, double avgLatency) {
        
        StringBuilder rec = new StringBuilder();
        
        if (!fpsUnstable && !cpuUnstable && !gpuUnstable && !latencyUnstable) {
            return "Stable performance - System running optimally";
        }
        
        if (fpsUnstable) {
            rec.append("Unstable FPS detected. ");
            
            if (avgGpu > 90) {
                rec.append("GPU at high load - consider reducing graphics settings. ");
            } else if (avgCpu > 90) {
                rec.append("CPU bottleneck detected - close background applications. ");
            } else {
                rec.append("Check for GPU throttling or driver issues. ");
            }
        }
        
        if (cpuUnstable) {
            rec.append("CPU usage fluctuating - check for background processes. ");
        }
        
        if (gpuUnstable) {
            rec.append("GPU usage unstable - verify thermal performance and driver stability. ");
        }
        
        if (latencyUnstable) {
            rec.append("Network latency varying - check network connection stability. ");
        }
        
        // Additional recommendations based on averages
        if (avgLatency > 100) {
            rec.append("High latency detected - consider using wired connection. ");
        }
        
        if (avgCpu > 85 && avgGpu < 60) {
            rec.append("CPU-bound - upgrade processor or reduce game settings affecting CPU. ");
        }
        
        if (avgGpu > 85 && avgCpu < 60) {
            rec.append("GPU-bound - reduce resolution or graphics quality. ");
        }
        
        return rec.toString().trim();
    }

    /**
     * Analyze individual game metric for anomalies.
     * 
     * @param metric GameMetric to analyze
     * @return Map containing anomaly detection results
     */
    public Map<String, Object> detectAnomalies(GameMetric metric) {
        boolean highTemp = metric.getTemperatureC() > 80;
        boolean lowFps = metric.getAvgFps() < 60;
        boolean highLatency = metric.getLatencyMs() > 50;
        boolean highCpu = metric.getCpuUsage() > 90;
        boolean highGpu = metric.getGpuUsage() > 90;
        
        boolean hasAnomalies = highTemp || lowFps || highLatency || highCpu || highGpu;
        
        StringBuilder issues = new StringBuilder();
        if (highTemp) issues.append("High temperature. ");
        if (lowFps) issues.append("Low FPS. ");
        if (highLatency) issues.append("High latency. ");
        if (highCpu) issues.append("High CPU usage. ");
        if (highGpu) issues.append("High GPU usage. ");
        
        return Map.of(
            "gameName", metric.getName(),
            "hasAnomalies", hasAnomalies,
            "issues", issues.toString().trim(),
            "performanceScore", metric.getPerformanceScore(),
            "stabilityIndex", metric.getStabilityIndex()
        );
    }
}
