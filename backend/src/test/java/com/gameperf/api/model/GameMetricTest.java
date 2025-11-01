package com.gameperf.api.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GameMetric model.
 */
@DisplayName("GameMetric Model Tests")
class GameMetricTest {

    @Test
    @DisplayName("Should create GameMetric with no-args constructor")
    void testNoArgsConstructor() {
        GameMetric metric = new GameMetric();
        assertNotNull(metric, "GameMetric should be created");
    }

    @Test
    @DisplayName("Should create GameMetric with all-args constructor")
    void testAllArgsConstructor() {
        GameMetric metric = new GameMetric(
            "TestGame", 144, 60, 70, 20, 65, 85.5, 92.3
        );
        
        assertEquals("TestGame", metric.getName());
        assertEquals(144, metric.getAvgFps());
        assertEquals(60, metric.getCpuUsage());
        assertEquals(70, metric.getGpuUsage());
        assertEquals(20, metric.getLatencyMs());
        assertEquals(65, metric.getTemperatureC());
        assertEquals(85.5, metric.getPerformanceScore());
        assertEquals(92.3, metric.getStabilityIndex());
    }

    @Test
    @DisplayName("Should set and get all properties")
    void testSettersAndGetters() {
        GameMetric metric = new GameMetric();
        
        metric.setName("TestGame");
        metric.setAvgFps(144);
        metric.setCpuUsage(60);
        metric.setGpuUsage(70);
        metric.setLatencyMs(20);
        metric.setTemperatureC(65);
        metric.setPerformanceScore(85.5);
        metric.setStabilityIndex(92.3);
        
        assertEquals("TestGame", metric.getName());
        assertEquals(144, metric.getAvgFps());
        assertEquals(60, metric.getCpuUsage());
        assertEquals(70, metric.getGpuUsage());
        assertEquals(20, metric.getLatencyMs());
        assertEquals(65, metric.getTemperatureC());
        assertEquals(85.5, metric.getPerformanceScore());
        assertEquals(92.3, metric.getStabilityIndex());
    }

    @Test
    @DisplayName("Should support equals and hashCode")
    void testEqualsAndHashCode() {
        GameMetric metric1 = new GameMetric(
            "TestGame", 144, 60, 70, 20, 65, 85.5, 92.3
        );
        
        GameMetric metric2 = new GameMetric(
            "TestGame", 144, 60, 70, 20, 65, 85.5, 92.3
        );
        
        assertEquals(metric1, metric2, "Metrics with same values should be equal");
        assertEquals(metric1.hashCode(), metric2.hashCode(), "Hash codes should match");
    }

    @Test
    @DisplayName("Should support toString")
    void testToString() {
        GameMetric metric = new GameMetric(
            "TestGame", 144, 60, 70, 20, 65, 85.5, 92.3
        );
        
        String toString = metric.toString();
        assertNotNull(toString, "toString should not return null");
        assertTrue(toString.contains("TestGame"), "toString should contain game name");
    }
}
