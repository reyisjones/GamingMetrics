package com.gameperf.api;

import com.gameperf.api.model.GameMetric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GameMetricsService.
 */
@DisplayName("GameMetricsService Tests")
class GameMetricsServiceTest {

    private GameMetricsService service;

    @BeforeEach
    void setUp() {
        service = new GameMetricsService();
    }

    @Test
    @DisplayName("Should return non-empty list of metrics")
    void testGetMetrics() {
        List<GameMetric> metrics = service.getMetrics();
        
        assertNotNull(metrics, "Metrics list should not be null");
        assertFalse(metrics.isEmpty(), "Metrics list should not be empty");
        assertEquals(6, metrics.size(), "Should return 6 games");
    }

    @Test
    @DisplayName("Should calculate performance scores correctly")
    void testPerformanceScoreCalculation() {
        List<GameMetric> metrics = service.getMetrics();
        
        for (GameMetric metric : metrics) {
            assertNotNull(metric.getName(), "Game name should not be null");
            assertTrue(metric.getPerformanceScore() >= 0, "Performance score should be >= 0");
            assertTrue(metric.getPerformanceScore() <= 100, "Performance score should be <= 100");
        }
    }

    @Test
    @DisplayName("Should calculate stability index correctly")
    void testStabilityIndexCalculation() {
        List<GameMetric> metrics = service.getMetrics();
        
        for (GameMetric metric : metrics) {
            assertTrue(metric.getStabilityIndex() >= 0, "Stability index should be >= 0");
            assertTrue(metric.getStabilityIndex() <= 100, "Stability index should be <= 100");
        }
    }

    @Test
    @DisplayName("Should return valid analysis summary")
    void testGetAnalysisSummary() {
        Map<String, Object> summary = service.getAnalysisSummary();
        
        assertNotNull(summary, "Summary should not be null");
        assertTrue(summary.containsKey("totalGames"), "Should contain totalGames");
        assertTrue(summary.containsKey("averagePerformance"), "Should contain averagePerformance");
        assertTrue(summary.containsKey("averageStability"), "Should contain averageStability");
        assertTrue(summary.containsKey("averageFps"), "Should contain averageFps");
        assertTrue(summary.containsKey("averageCpuUsage"), "Should contain averageCpuUsage");
        assertTrue(summary.containsKey("averageGpuUsage"), "Should contain averageGpuUsage");
        assertTrue(summary.containsKey("averageLatency"), "Should contain averageLatency");
        assertTrue(summary.containsKey("averageTemperature"), "Should contain averageTemperature");
        
        assertEquals(6, summary.get("totalGames"), "Should report 6 games");
    }

    @Test
    @DisplayName("Should have valid FPS ranges")
    void testFpsRanges() {
        List<GameMetric> metrics = service.getMetrics();
        
        for (GameMetric metric : metrics) {
            assertTrue(metric.getAvgFps() > 0, "FPS should be positive");
            assertTrue(metric.getAvgFps() <= 200, "FPS should be realistic");
        }
    }

    @Test
    @DisplayName("Should have valid CPU and GPU usage ranges")
    void testUsageRanges() {
        List<GameMetric> metrics = service.getMetrics();
        
        for (GameMetric metric : metrics) {
            assertTrue(metric.getCpuUsage() >= 0, "CPU usage should be >= 0");
            assertTrue(metric.getCpuUsage() <= 100, "CPU usage should be <= 100");
            assertTrue(metric.getGpuUsage() >= 0, "GPU usage should be >= 0");
            assertTrue(metric.getGpuUsage() <= 100, "GPU usage should be <= 100");
        }
    }

    @Test
    @DisplayName("Should have valid latency ranges")
    void testLatencyRanges() {
        List<GameMetric> metrics = service.getMetrics();
        
        for (GameMetric metric : metrics) {
            assertTrue(metric.getLatencyMs() >= 0, "Latency should be positive");
            assertTrue(metric.getLatencyMs() <= 200, "Latency should be realistic");
        }
    }

    @Test
    @DisplayName("Should have valid temperature ranges")
    void testTemperatureRanges() {
        List<GameMetric> metrics = service.getMetrics();
        
        for (GameMetric metric : metrics) {
            assertTrue(metric.getTemperatureC() >= 40, "Temperature should be realistic");
            assertTrue(metric.getTemperatureC() <= 100, "Temperature should be realistic");
        }
    }

    @Test
    @DisplayName("Should generate different data on multiple calls (randomization)")
    void testDataRandomization() {
        List<GameMetric> metrics1 = service.getMetrics();
        List<GameMetric> metrics2 = service.getMetrics();
        
        // At least some values should differ due to randomization
        boolean hasDifference = false;
        for (int i = 0; i < metrics1.size(); i++) {
            if (metrics1.get(i).getAvgFps() != metrics2.get(i).getAvgFps()) {
                hasDifference = true;
                break;
            }
        }
        
        assertTrue(hasDifference, "Metrics should have some variation due to randomization");
    }
}
