package com.gameperf.api;

import com.gameperf.api.model.GameMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * REST Controller for game metrics API.
 * Provides endpoints for retrieving metrics, analysis, and real-time updates.
 */
@RestController
@RequestMapping("/api/metrics")
@CrossOrigin(origins = "*")
public class GameMetricsController {

    @Autowired
    private GameMetricsService metricsService;

    /**
     * Get all game metrics with calculated performance scores.
     * 
     * @return List of game metrics
     */
    @GetMapping
    public List<GameMetric> getAllMetrics() {
        return metricsService.getMetrics();
    }

    /**
     * Get analysis summary of all metrics.
     * Includes averages for performance, stability, and other key metrics.
     * 
     * @return Map containing analysis statistics
     */
    @GetMapping("/analyze")
    public Map<String, Object> analyzeAll() {
        return metricsService.getAnalysisSummary();
    }

    /**
     * Stream live metrics using Server-Sent Events (SSE).
     * Pushes updated metrics every 3 seconds.
     * 
     * Frontend usage:
     * const evtSource = new EventSource("http://localhost:8080/api/metrics/live");
     * evtSource.onmessage = e => setGames(JSON.parse(e.data));
     * 
     * @return SseEmitter for streaming updates
     */
    @GetMapping(value = "/live", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamMetrics() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                while (true) {
                    // Send updated metrics every 3 seconds
                    List<GameMetric> metrics = metricsService.getMetrics();
                    emitter.send(SseEmitter.event()
                            .data(metrics)
                            .name("metrics-update"));
                    
                    Thread.sleep(3000);
                }
            } catch (IOException e) {
                emitter.completeWithError(e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                emitter.complete();
            }
        });
        
        // Handle client disconnect
        emitter.onCompletion(() -> System.out.println("SSE connection completed"));
        emitter.onTimeout(() -> System.out.println("SSE connection timed out"));
        emitter.onError(e -> System.out.println("SSE error: " + e.getMessage()));
        
        return emitter;
    }

    /**
     * Health check endpoint to verify backend availability.
     * 
     * @return Status message
     */
    @GetMapping("/test")
    public Map<String, String> healthCheck() {
        return Map.of(
            "status", "running",
            "message", "Backend is running - Java Metrics API ready!",
            "version", "1.0.0"
        );
    }
}
