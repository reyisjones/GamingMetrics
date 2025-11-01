package com.gameperf.api;

import com.gameperf.api.model.GameMetric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GameAnalyticsService.
 */
@DisplayName("GameAnalyticsService Tests")
class GameAnalyticsServiceTest {

    private GameAnalyticsService service;

    @BeforeEach
    void setUp() {
        service = new GameAnalyticsService();
    }

    @Test
    @DisplayName("Should analyze stable session correctly")
    void testAnalyzeStableSession() {
        List<GameMetric> stableData = Arrays.asList(
            new GameMetric("Game1", 144, 60, 65, 20, 65, 0, 0),
            new GameMetric("Game2", 145, 61, 66, 21, 66, 0, 0),
            new GameMetric("Game3", 143, 62, 64, 19, 64, 0, 0)
        );
        
        Map<String, Object> analysis = service.analyzeSession(stableData);
        
        assertNotNull(analysis, "Analysis should not be null");
        assertTrue((Boolean) analysis.get("sessionStable"), "Session should be stable");
        assertTrue(analysis.containsKey("fpsVariance"), "Should contain fpsVariance");
        assertTrue(analysis.containsKey("recommendation"), "Should contain recommendation");
    }

    @Test
    @DisplayName("Should detect unstable FPS session")
    void testAnalyzeUnstableFpsSession() {
        List<GameMetric> unstableData = Arrays.asList(
            new GameMetric("Game1", 144, 60, 65, 20, 65, 0, 0),
            new GameMetric("Game2", 90, 62, 67, 22, 66, 0, 0),
            new GameMetric("Game3", 160, 58, 63, 18, 64, 0, 0),
            new GameMetric("Game4", 75, 64, 69, 24, 68, 0, 0)
        );
        
        Map<String, Object> analysis = service.analyzeSession(unstableData);
        
        assertNotNull(analysis, "Analysis should not be null");
        assertFalse((Boolean) analysis.get("sessionStable"), "Session should be unstable");
        assertTrue((Double) analysis.get("fpsVariance") > 50, "FPS variance should be high");
    }

    @Test
    @DisplayName("Should handle empty session data")
    void testAnalyzeEmptySession() {
        List<GameMetric> emptyData = Arrays.asList();
        
        Map<String, Object> analysis = service.analyzeSession(emptyData);
        
        assertNotNull(analysis, "Analysis should not be null");
        assertTrue(analysis.containsKey("error"), "Should contain error message");
        assertFalse((Boolean) analysis.get("sessionStable"), "Empty session should not be stable");
    }

    @Test
    @DisplayName("Should handle null session data")
    void testAnalyzeNullSession() {
        Map<String, Object> analysis = service.analyzeSession(null);
        
        assertNotNull(analysis, "Analysis should not be null");
        assertTrue(analysis.containsKey("error"), "Should contain error message");
    }

    @Test
    @DisplayName("Should calculate variance correctly")
    void testVarianceCalculation() {
        List<GameMetric> data = Arrays.asList(
            new GameMetric("Game1", 100, 50, 50, 20, 65, 0, 0),
            new GameMetric("Game2", 100, 50, 50, 20, 65, 0, 0),
            new GameMetric("Game3", 100, 50, 50, 20, 65, 0, 0)
        );
        
        Map<String, Object> analysis = service.analyzeSession(data);
        
        // Variance should be 0 for identical values
        assertEquals(0.0, (Double) analysis.get("fpsVariance"), 0.01, "Variance should be 0 for identical values");
    }

    @Test
    @DisplayName("Should provide recommendations for unstable session")
    void testRecommendations() {
        List<GameMetric> unstableData = Arrays.asList(
            new GameMetric("Game1", 50, 95, 80, 20, 65, 0, 0),
            new GameMetric("Game2", 140, 40, 85, 22, 66, 0, 0),
            new GameMetric("Game3", 90, 70, 75, 18, 64, 0, 0)
        );
        
        Map<String, Object> analysis = service.analyzeSession(unstableData);
        
        String recommendation = (String) analysis.get("recommendation");
        assertNotNull(recommendation, "Recommendation should not be null");
        assertFalse(recommendation.isEmpty(), "Recommendation should not be empty");
    }

    @Test
    @DisplayName("Should detect high temperature anomaly")
    void testDetectHighTemperatureAnomaly() {
        GameMetric hotMetric = new GameMetric("HotGame", 144, 60, 65, 20, 85, 0, 0);
        
        Map<String, Object> anomalies = service.detectAnomalies(hotMetric);
        
        assertTrue((Boolean) anomalies.get("hasAnomalies"), "Should detect high temperature");
        assertTrue(((String) anomalies.get("issues")).contains("temperature"), "Issues should mention temperature");
    }

    @Test
    @DisplayName("Should detect low FPS anomaly")
    void testDetectLowFpsAnomaly() {
        GameMetric lowFpsMetric = new GameMetric("SlowGame", 45, 60, 65, 20, 65, 0, 0);
        
        Map<String, Object> anomalies = service.detectAnomalies(lowFpsMetric);
        
        assertTrue((Boolean) anomalies.get("hasAnomalies"), "Should detect low FPS");
        assertTrue(((String) anomalies.get("issues")).contains("FPS"), "Issues should mention FPS");
    }

    @Test
    @DisplayName("Should detect high latency anomaly")
    void testDetectHighLatencyAnomaly() {
        GameMetric highLatencyMetric = new GameMetric("LaggyGame", 144, 60, 65, 120, 65, 0, 0);
        
        Map<String, Object> anomalies = service.detectAnomalies(highLatencyMetric);
        
        assertTrue((Boolean) anomalies.get("hasAnomalies"), "Should detect high latency");
        assertTrue(((String) anomalies.get("issues")).contains("latency"), "Issues should mention latency");
    }

    @Test
    @DisplayName("Should not detect anomalies in normal metric")
    void testNoAnomaliesInNormalMetric() {
        GameMetric normalMetric = new GameMetric("NormalGame", 144, 60, 65, 20, 65, 85.5, 92.0);
        
        Map<String, Object> anomalies = service.detectAnomalies(normalMetric);
        
        assertFalse((Boolean) anomalies.get("hasAnomalies"), "Should not detect anomalies");
        assertTrue(((String) anomalies.get("issues")).isEmpty(), "Issues should be empty");
    }

    @Test
    @DisplayName("Should include game name in anomaly detection")
    void testAnomalyDetectionIncludesGameName() {
        GameMetric metric = new GameMetric("TestGame", 144, 60, 65, 20, 65, 0, 0);
        
        Map<String, Object> anomalies = service.detectAnomalies(metric);
        
        assertEquals("TestGame", anomalies.get("gameName"), "Should include correct game name");
    }
}
