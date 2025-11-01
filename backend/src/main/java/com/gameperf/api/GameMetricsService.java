package com.gameperf.api;

import com.gameperf.api.model.GameMetric;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Service for managing game metrics data.
 * Provides sample data generation, score calculations, and analysis summaries.
 */
@Service
public class GameMetricsService {

    private final Random random = new Random();

    /**
     * Get all game metrics with calculated scores.
     * Data is refreshed with slight variations to simulate real-time updates.
     * 
     * @return List of GameMetric objects with calculated performance scores
     */
    public List<GameMetric> getMetrics() {
        return generateSampleData().stream()
                .map(this::calculateScores)
                .toList();
    }

    /**
     * Generate sample game data with slight randomization for realistic simulation.
     * 
     * @return List of GameMetric objects with raw measurements
     */
    private List<GameMetric> generateSampleData() {
        List<GameMetric> games = new ArrayList<>();
        
        // Base metrics for each game (with slight variations for realism)
        games.add(new GameMetric(
            "Cyber Drift X", 
            144 + random.nextInt(11) - 5,  // 139-149 fps
            62 + random.nextInt(7) - 3,     // 59-65% CPU
            75 + random.nextInt(7) - 3,     // 72-78% GPU
            21 + random.nextInt(5) - 2,     // 19-23ms latency
            68 + random.nextInt(5) - 2,     // 66-70°C
            0, 0
        ));
        
        games.add(new GameMetric(
            "StarForge Arena", 
            119 + random.nextInt(9) - 4,    // 115-123 fps
            70 + random.nextInt(7) - 3,     // 67-73% CPU
            81 + random.nextInt(7) - 3,     // 78-84% GPU
            30 + random.nextInt(7) - 3,     // 27-33ms latency
            72 + random.nextInt(5) - 2,     // 70-74°C
            0, 0
        ));
        
        games.add(new GameMetric(
            "Legends Reborn", 
            165 + random.nextInt(11) - 5,   // 160-170 fps
            55 + random.nextInt(7) - 3,     // 52-58% CPU
            64 + random.nextInt(7) - 3,     // 61-67% GPU
            17 + random.nextInt(5) - 2,     // 15-19ms latency
            63 + random.nextInt(5) - 2,     // 61-65°C
            0, 0
        ));
        
        games.add(new GameMetric(
            "Shadow Protocol", 
            98 + random.nextInt(9) - 4,     // 94-102 fps
            78 + random.nextInt(7) - 3,     // 75-81% CPU
            85 + random.nextInt(7) - 3,     // 82-88% GPU
            42 + random.nextInt(7) - 3,     // 39-45ms latency
            75 + random.nextInt(5) - 2,     // 73-77°C
            0, 0
        ));
        
        games.add(new GameMetric(
            "Neon Havoc", 
            132 + random.nextInt(11) - 5,   // 127-137 fps
            64 + random.nextInt(7) - 3,     // 61-67% CPU
            71 + random.nextInt(7) - 3,     // 68-74% GPU
            25 + random.nextInt(5) - 2,     // 23-27ms latency
            69 + random.nextInt(5) - 2,     // 67-71°C
            0, 0
        ));
        
        games.add(new GameMetric(
            "Quantum Raiders", 
            156 + random.nextInt(11) - 5,   // 151-161 fps
            58 + random.nextInt(7) - 3,     // 55-61% CPU
            68 + random.nextInt(7) - 3,     // 65-71% GPU
            19 + random.nextInt(5) - 2,     // 17-21ms latency
            65 + random.nextInt(5) - 2,     // 63-67°C
            0, 0
        ));
        
        return games;
    }

    /**
     * Calculate performance score and stability index for a game metric.
     * 
     * Performance Score Formula:
     * - (fps * 0.6) - (cpu * 0.15) - (gpu * 0.15) - (latency * 0.1)
     * - Normalized to 0-100 range
     * 
     * Stability Index Formula:
     * - 100 - |cpu - gpu| * 0.6
     * - Measures load balance between CPU and GPU
     * - Normalized to 0-100 range
     * 
     * @param g GameMetric with raw measurements
     * @return GameMetric with calculated scores
     */
    private GameMetric calculateScores(GameMetric g) {
        // Performance score: weighted calculation favoring high FPS and low resource usage
        double perf = (g.getAvgFps() * 0.6)
                    - (g.getCpuUsage() * 0.15)
                    - (g.getGpuUsage() * 0.15)
                    - (g.getLatencyMs() * 0.1);
        
        // Stability index: measures balance between CPU and GPU usage
        // Lower difference = more stable performance
        double stab = 100 - Math.abs(g.getCpuUsage() - g.getGpuUsage()) * 0.6;
        
        // Normalize scores to 0-100 range
        g.setPerformanceScore(Math.max(0, Math.min(perf, 100)));
        g.setStabilityIndex(Math.max(0, Math.min(stab, 100)));
        
        return g;
    }

    /**
     * Get analysis summary of all metrics.
     * Includes averages for performance and stability across all games.
     * 
     * @return Map containing analysis summary statistics
     */
    public Map<String, Object> getAnalysisSummary() {
        List<GameMetric> metrics = getMetrics();
        
        double avgPerf = metrics.stream()
                .mapToDouble(GameMetric::getPerformanceScore)
                .average()
                .orElse(0);
        
        double avgStab = metrics.stream()
                .mapToDouble(GameMetric::getStabilityIndex)
                .average()
                .orElse(0);
        
        double avgFps = metrics.stream()
                .mapToDouble(GameMetric::getAvgFps)
                .average()
                .orElse(0);
        
        double avgCpu = metrics.stream()
                .mapToDouble(GameMetric::getCpuUsage)
                .average()
                .orElse(0);
        
        double avgGpu = metrics.stream()
                .mapToDouble(GameMetric::getGpuUsage)
                .average()
                .orElse(0);
        
        double avgLatency = metrics.stream()
                .mapToDouble(GameMetric::getLatencyMs)
                .average()
                .orElse(0);
        
        double avgTemp = metrics.stream()
                .mapToDouble(GameMetric::getTemperatureC)
                .average()
                .orElse(0);
        
        return Map.of(
            "totalGames", metrics.size(),
            "averagePerformance", Math.round(avgPerf * 100.0) / 100.0,
            "averageStability", Math.round(avgStab * 100.0) / 100.0,
            "averageFps", Math.round(avgFps * 100.0) / 100.0,
            "averageCpuUsage", Math.round(avgCpu * 100.0) / 100.0,
            "averageGpuUsage", Math.round(avgGpu * 100.0) / 100.0,
            "averageLatency", Math.round(avgLatency * 100.0) / 100.0,
            "averageTemperature", Math.round(avgTemp * 100.0) / 100.0
        );
    }
}
