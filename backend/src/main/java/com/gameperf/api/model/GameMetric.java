package com.gameperf.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing game performance metrics.
 * Includes both raw measurements and calculated performance indicators.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMetric {
    
    /**
     * Name of the game
     */
    private String name;
    
    /**
     * Average frames per second
     */
    private int avgFps;
    
    /**
     * CPU usage percentage (0-100)
     */
    private int cpuUsage;
    
    /**
     * GPU usage percentage (0-100)
     */
    private int gpuUsage;
    
    /**
     * Network latency in milliseconds
     */
    private int latencyMs;
    
    /**
     * GPU temperature in Celsius
     */
    private int temperatureC;
    
    /**
     * Calculated performance score (0-100)
     * Based on weighted formula: (fps * 0.6) - (cpu + gpu + latency) * 0.1
     */
    private double performanceScore;
    
    /**
     * Calculated stability index (0-100)
     * Based on load balance between CPU and GPU
     */
    private double stabilityIndex;
}
