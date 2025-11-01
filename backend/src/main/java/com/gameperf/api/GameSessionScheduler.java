package com.gameperf.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Scheduled task for periodic metrics aggregation.
 * Runs every 10 seconds to log and monitor system metrics.
 */
@Component
public class GameSessionScheduler {

    @Autowired
    private GameMetricsService metricsService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Aggregate and log metrics summary every 10 seconds.
     * This scheduled task monitors overall system performance.
     */
    @Scheduled(fixedRate = 10000)
    public void aggregateMetrics() {
        Map<String, Object> summary = metricsService.getAnalysisSummary();
        String timestamp = LocalDateTime.now().format(formatter);
        
        System.out.println("=== Metrics Aggregation [" + timestamp + "] ===");
        System.out.println("Total Games: " + summary.get("totalGames"));
        System.out.println("Avg Performance: " + summary.get("averagePerformance"));
        System.out.println("Avg Stability: " + summary.get("averageStability"));
        System.out.println("Avg FPS: " + summary.get("averageFps"));
        System.out.println("Avg CPU: " + summary.get("averageCpuUsage") + "%");
        System.out.println("Avg GPU: " + summary.get("averageGpuUsage") + "%");
        System.out.println("Avg Latency: " + summary.get("averageLatency") + "ms");
        System.out.println("Avg Temperature: " + summary.get("averageTemperature") + "°C");
        System.out.println("==========================================");
    }

    /**
     * Periodic health check every 30 seconds.
     * Logs a heartbeat message to confirm scheduler is running.
     */
    @Scheduled(fixedRate = 30000)
    public void healthCheck() {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] Scheduler heartbeat - System monitoring active");
    }
}
