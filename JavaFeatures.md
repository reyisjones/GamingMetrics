# Prompt: Add Java Backend Features for Gaming Metrics Dashboard

You are an AI coding assistant inside Visual Studio Code.  
The React + MUI frontend for the Gaming Metrics Dashboard is already built.  
Now, create a **Java backend module** that provides APIs, analytics calculations, and simulated real-time data for the frontend.

---

## ☕️ TECHNOLOGY STACK
Use **Java 17+** with **Spring Boot 3.x**  
Dependencies:  
- `spring-boot-starter-web`  
- `spring-boot-starter-actuator`  
- `spring-boot-starter-cache`  
- `spring-boot-starter-websocket` (optional for real-time updates)

---

## 🧱 PROJECT STRUCTURE

```
backend/
├── src/main/java/com/gameperf/api/
│   ├── GameMetricsController.java
│   ├── GameMetricsService.java
│   ├── GameAnalyticsService.java
│   ├── GameSessionScheduler.java
│   └── model/GameMetric.java
└── pom.xml
```

---

## 🧩 FEATURES TO IMPLEMENT

### 1. **REST API for Metrics**
Create a `/api/metrics` endpoint returning a JSON array of game metrics with attributes:
- `name`
- `avgFps`
- `cpuUsage`
- `gpuUsage`
- `latencyMs`
- `temperatureC`
- `performanceScore`
- `stabilityIndex`

### Example: `GameMetricsController.java`
```java
@RestController
@RequestMapping("/api/metrics")
@CrossOrigin(origins = "*")
public class GameMetricsController {

    @Autowired
    private GameMetricsService metricsService;

    @GetMapping
    public List<GameMetric> getAllMetrics() {
        return metricsService.getMetrics();
    }

    @GetMapping("/analyze")
    public Map<String, Object> analyzeAll() {
        return metricsService.getAnalysisSummary();
    }
}
```

---

### 2. **Metric Model**
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMetric {
    private String name;
    private int avgFps;
    private int cpuUsage;
    private int gpuUsage;
    private int latencyMs;
    private int temperatureC;
    private double performanceScore;
    private double stabilityIndex;
}
```

---

### 3. **Calculation Logic**
Add weighted calculations for:
- `performanceScore` → `(fps * 0.6) - (cpu + gpu + latency) * 0.1`
- `stabilityIndex` → based on difference between CPU and GPU load
- Include normalization to keep all scores between 0–100

```java
@Service
public class GameMetricsService {

    public List<GameMetric> getMetrics() {
        return generateSampleData().stream().map(this::calculateScores).toList();
    }

    private List<GameMetric> generateSampleData() {
        return List.of(
            new GameMetric("Cyber Drift X", 144, 62, 75, 21, 68, 0, 0),
            new GameMetric("StarForge Arena", 119, 70, 81, 30, 72, 0, 0),
            new GameMetric("Legends Reborn", 165, 55, 64, 17, 63, 0, 0)
        );
    }

    private GameMetric calculateScores(GameMetric g) {
        double perf = (g.getAvgFps() * 0.6)
                    - (g.getCpuUsage() * 0.15)
                    - (g.getGpuUsage() * 0.15)
                    - (g.getLatencyMs() * 0.1);
        double stab = 100 - Math.abs(g.getCpuUsage() - g.getGpuUsage()) * 0.6;
        g.setPerformanceScore(Math.max(0, Math.min(perf, 100)));
        g.setStabilityIndex(Math.max(0, Math.min(stab, 100)));
        return g;
    }

    public Map<String, Object> getAnalysisSummary() {
        List<GameMetric> metrics = getMetrics();
        double avgPerf = metrics.stream().mapToDouble(GameMetric::getPerformanceScore).average().orElse(0);
        double avgStab = metrics.stream().mapToDouble(GameMetric::getStabilityIndex).average().orElse(0);
        return Map.of("averagePerformance", avgPerf, "averageStability", avgStab, "totalGames", metrics.size());
    }
}
```

---

### 4. **Optional Real-Time Simulation (SSE or WebSocket)**

Use **Server-Sent Events** to push updates every few seconds to `/api/metrics/live`.

```java
@GetMapping("/live")
public SseEmitter streamMetrics() {
    SseEmitter emitter = new SseEmitter();
    Executors.newSingleThreadExecutor().execute(() -> {
        try {
            while (true) {
                emitter.send(getMetrics());
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            emitter.completeWithError(e);
        }
    });
    return emitter;
}
```

Your React dashboard can listen to this live endpoint using:
```js
const evtSource = new EventSource("http://localhost:8080/api/metrics/live");
evtSource.onmessage = e => setGames(JSON.parse(e.data));
```

---

### 5. **Scheduled Aggregation**
Add a scheduler that aggregates metrics over time to calculate trends:

```java
@Component
@EnableScheduling
public class GameSessionScheduler {

    @Autowired
    private GameMetricsService service;

    @Scheduled(fixedRate = 10000)
    public void aggregateMetrics() {
        var summary = service.getAnalysisSummary();
        System.out.println("Metrics summary: " + summary);
    }
}
```

---

### 6. **Optional Analytics Service**
Implement a `GameAnalyticsService` that accepts uploaded logs and computes averages or anomalies:

```java
@Service
public class GameAnalyticsService {

    public Map<String, Object> analyzeSession(List<GameMetric> sessionData) {
        double variance = calculateVariance(sessionData.stream().mapToInt(GameMetric::getAvgFps).toArray());
        boolean unstable = variance > 50;
        return Map.of(
            "fpsVariance", variance,
            "sessionStable", !unstable,
            "recommendation", unstable ? "Check GPU throttling" : "Stable performance"
        );
    }

    private double calculateVariance(int[] values) {
        double mean = Arrays.stream(values).average().orElse(0);
        return Arrays.stream(values).mapToDouble(v -> Math.pow(v - mean, 2)).average().orElse(0);
    }
}
```

---

### 7. **Test Endpoint**
Add `/api/test` to verify backend availability:
```java
@GetMapping("/test")
public String healthCheck() {
    return "Backend is running - Java Metrics API ready!";
}
```

---

### ✅ OUTPUT EXPECTATION
When complete:
- `http://localhost:8080/api/metrics` → Returns real-time game metrics  
- `http://localhost:8080/api/metrics/analyze` → Returns summary averages  
- `http://localhost:8080/api/metrics/live` → Streams updates every 3 seconds  
- The frontend React dashboard updates with calculated metrics and charts  

---

> 💡 *Enhancement idea:* add a `/api/predict` endpoint using a lightweight ML model (e.g., Java Smile library) to predict future FPS based on trends.
````
 
