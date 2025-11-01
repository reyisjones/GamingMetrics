package com.gameperf.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Integration tests for GameMetricsController.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("GameMetricsController Integration Tests")
class GameMetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/metrics should return list of metrics")
    void testGetAllMetrics() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].name", notNullValue()))
                .andExpect(jsonPath("$[0].avgFps", notNullValue()))
                .andExpect(jsonPath("$[0].cpuUsage", notNullValue()))
                .andExpect(jsonPath("$[0].gpuUsage", notNullValue()))
                .andExpect(jsonPath("$[0].latencyMs", notNullValue()))
                .andExpect(jsonPath("$[0].temperatureC", notNullValue()))
                .andExpect(jsonPath("$[0].performanceScore", notNullValue()))
                .andExpect(jsonPath("$[0].stabilityIndex", notNullValue()));
    }

    @Test
    @DisplayName("GET /api/metrics/analyze should return analysis summary")
    void testAnalyzeAll() throws Exception {
        mockMvc.perform(get("/api/metrics/analyze"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalGames", is(6)))
                .andExpect(jsonPath("$.averagePerformance", notNullValue()))
                .andExpect(jsonPath("$.averageStability", notNullValue()))
                .andExpect(jsonPath("$.averageFps", notNullValue()))
                .andExpect(jsonPath("$.averageCpuUsage", notNullValue()))
                .andExpect(jsonPath("$.averageGpuUsage", notNullValue()))
                .andExpect(jsonPath("$.averageLatency", notNullValue()))
                .andExpect(jsonPath("$.averageTemperature", notNullValue()));
    }

    @Test
    @DisplayName("GET /api/metrics/test should return health check")
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/metrics/test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("running")))
                .andExpect(jsonPath("$.message", containsString("Backend is running")))
                .andExpect(jsonPath("$.version", is("1.0.0")));
    }

    @Test
    @DisplayName("Performance scores should be in valid range")
    void testPerformanceScoreRange() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].performanceScore", everyItem(greaterThanOrEqualTo(0.0))))
                .andExpect(jsonPath("$[*].performanceScore", everyItem(lessThanOrEqualTo(100.0))));
    }

    @Test
    @DisplayName("Stability indexes should be in valid range")
    void testStabilityIndexRange() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].stabilityIndex", everyItem(greaterThanOrEqualTo(0.0))))
                .andExpect(jsonPath("$[*].stabilityIndex", everyItem(lessThanOrEqualTo(100.0))));
    }

    @Test
    @DisplayName("FPS values should be positive")
    void testFpsPositive() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].avgFps", everyItem(greaterThan(0))));
    }

    @Test
    @DisplayName("CPU usage should be in valid range")
    void testCpuUsageRange() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].cpuUsage", everyItem(greaterThanOrEqualTo(0))))
                .andExpect(jsonPath("$[*].cpuUsage", everyItem(lessThanOrEqualTo(100))));
    }

    @Test
    @DisplayName("GPU usage should be in valid range")
    void testGpuUsageRange() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].gpuUsage", everyItem(greaterThanOrEqualTo(0))))
                .andExpect(jsonPath("$[*].gpuUsage", everyItem(lessThanOrEqualTo(100))));
    }

    @Test
    @DisplayName("Latency should be non-negative")
    void testLatencyNonNegative() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].latencyMs", everyItem(greaterThanOrEqualTo(0))));
    }

    @Test
    @DisplayName("Temperature should be realistic")
    void testTemperatureRealistic() throws Exception {
        mockMvc.perform(get("/api/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].temperatureC", everyItem(greaterThan(0))))
                .andExpect(jsonPath("$[*].temperatureC", everyItem(lessThan(150))));
    }

    @Test
    @DisplayName("Analysis summary should have correct number of games")
    void testAnalysisSummaryGameCount() throws Exception {
        mockMvc.perform(get("/api/metrics/analyze"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalGames", is(6)));
    }
}
