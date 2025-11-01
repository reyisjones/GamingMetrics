# Gaming Metrics Backend API

Java Spring Boot backend for the Gaming Performance Dashboard. Provides REST APIs for game performance metrics, real-time updates via Server-Sent Events, analytics calculations, and scheduled monitoring.

## 🚀 Technology Stack

- **Java 17+**
- **Spring Boot 3.2.0**
- **Maven** for dependency management
- **Lombok** for reducing boilerplate
- **JUnit 5** for testing

### Dependencies

- `spring-boot-starter-web` - REST API framework
- `spring-boot-starter-actuator` - Health monitoring
- `spring-boot-starter-cache` - Caching support
- `spring-boot-starter-websocket` - WebSocket/SSE support
- `spring-boot-starter-test` - Testing framework

## 📁 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/gameperf/api/
│   │   │   ├── GamingMetricsApplication.java    # Main Spring Boot app
│   │   │   ├── GameMetricsController.java       # REST endpoints
│   │   │   ├── GameMetricsService.java          # Business logic
│   │   │   ├── GameAnalyticsService.java        # Analytics & recommendations
│   │   │   ├── GameSessionScheduler.java        # Scheduled tasks
│   │   │   └── model/
│   │   │       └── GameMetric.java              # Data model
│   │   └── resources/
│   │       └── application.properties           # Configuration
│   └── test/
│       └── java/com/gameperf/api/               # Unit & integration tests
└── pom.xml                                      # Maven configuration
```

## 🎯 Features

### 1. REST API Endpoints

#### `GET /api/metrics`
Returns all game metrics with calculated performance scores and stability indexes.

**Response:**
```json
[
  {
    "name": "Cyber Drift X",
    "avgFps": 144,
    "cpuUsage": 62,
    "gpuUsage": 75,
    "latencyMs": 21,
    "temperatureC": 68,
    "performanceScore": 72.45,
    "stabilityIndex": 92.2
  }
]
```

#### `GET /api/metrics/analyze`
Returns analysis summary with averages across all games.

**Response:**
```json
{
  "totalGames": 6,
  "averagePerformance": 75.23,
  "averageStability": 88.45,
  "averageFps": 137.5,
  "averageCpuUsage": 64.2,
  "averageGpuUsage": 73.8,
  "averageLatency": 24.3,
  "averageTemperature": 67.5
}
```

#### `GET /api/metrics/live` (SSE)
Streams real-time metric updates every 3 seconds using Server-Sent Events.

**Frontend Usage:**
```javascript
const evtSource = new EventSource("http://localhost:8080/api/metrics/live");
evtSource.onmessage = e => {
  const metrics = JSON.parse(e.data);
  setGames(metrics);
};
```

#### `GET /api/metrics/test`
Health check endpoint to verify backend availability.

**Response:**
```json
{
  "status": "running",
  "message": "Backend is running - Java Metrics API ready!",
  "version": "1.0.0"
}
```

### 2. Performance Calculations

#### Performance Score Formula
```
performanceScore = (fps × 0.6) - (cpu × 0.15) - (gpu × 0.15) - (latency × 0.1)
```
- Normalized to 0-100 range
- Favors high FPS and low resource usage

#### Stability Index Formula
```
stabilityIndex = 100 - |cpu - gpu| × 0.6
```
- Measures load balance between CPU and GPU
- Higher values indicate better balance

### 3. Analytics & Recommendations

The `GameAnalyticsService` provides:
- Session variance analysis (FPS, CPU, GPU, latency)
- Stability detection
- Performance recommendations
- Anomaly detection (high temp, low FPS, high latency)

### 4. Scheduled Monitoring

The `GameSessionScheduler` runs:
- **Every 10 seconds**: Aggregates and logs metrics summary
- **Every 30 seconds**: Health check heartbeat

## 🔧 Setup & Installation

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation Steps

1. **Navigate to backend directory:**
   ```bash
   cd backend
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Verify it's running:**
   ```bash
   curl http://localhost:8080/api/metrics/test
   ```

The server will start on `http://localhost:8080`.

## 🧪 Running Tests

### Run all tests:
```bash
mvn test
```

### Run with coverage report:
```bash
mvn test jacoco:report
```

### Test Coverage

The project includes comprehensive tests:
- **GameMetricsServiceTest**: 9 tests for service logic
- **GameAnalyticsServiceTest**: 12 tests for analytics
- **GameMetricsControllerTest**: 11 integration tests
- **GameMetricTest**: 5 tests for model

**Total: 37+ unit and integration tests**

## 🔌 Frontend Integration

### Update Frontend API Configuration

In `gaming-metrics-dashboard/src/api/config.json`:

```json
{
  "apis": "http://localhost:8080/api/metrics"
}
```

### Enable Real-Time Updates (Optional)

In your React component:

```javascript
useEffect(() => {
  const evtSource = new EventSource("http://localhost:8080/api/metrics/live");
  
  evtSource.onmessage = (event) => {
    const metrics = JSON.parse(event.data);
    setGames(metrics);
  };
  
  return () => evtSource.close();
}, []);
```

## 🌐 CORS Configuration

CORS is enabled for all origins with `@CrossOrigin(origins = "*")` on the controller.

For production, update to specific origins:
```java
@CrossOrigin(origins = "https://yourdomain.com")
```

## 📊 Sample Data

The backend generates 6 sample games with realistic metrics:
1. **Cyber Drift X** - High FPS racing game
2. **StarForge Arena** - Strategic combat game
3. **Legends Reborn** - Fast-paced MOBA
4. **Shadow Protocol** - Tactical shooter
5. **Neon Havoc** - Arcade action
6. **Quantum Raiders** - Sci-fi adventure

Metrics include slight randomization to simulate real-time variations.

## 🔍 Health Monitoring

Spring Boot Actuator endpoints are available at:
- `http://localhost:8080/actuator/health`
- `http://localhost:8080/actuator/info`
- `http://localhost:8080/actuator/metrics`

## 🛠 Configuration

### Application Properties

File: `src/main/resources/application.properties`

```properties
# Server Configuration
server.port=8080
spring.application.name=gaming-metrics-api

# Enable scheduling
spring.task.scheduling.pool.size=2

# Actuator endpoints
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always

# Logging
logging.level.com.gameperf=INFO
```

## 🚀 Deployment

### Package as JAR

```bash
mvn clean package
```

The executable JAR will be in `target/gaming-metrics-backend-1.0.0.jar`.

### Run the JAR

```bash
java -jar target/gaming-metrics-backend-1.0.0.jar
```

### Environment Variables

```bash
export SERVER_PORT=8080
export SPRING_PROFILES_ACTIVE=prod
java -jar target/gaming-metrics-backend-1.0.0.jar
```

## 📈 API Examples

### Get Metrics with curl
```bash
curl http://localhost:8080/api/metrics
```

### Get Analysis Summary
```bash
curl http://localhost:8080/api/metrics/analyze
```

### Stream Live Updates
```bash
curl -N http://localhost:8080/api/metrics/live
```

### Health Check
```bash
curl http://localhost:8080/api/metrics/test
```

## 🔮 Future Enhancements

- Database integration (PostgreSQL/MongoDB) for persistence
- User authentication with Spring Security
- Rate limiting for API endpoints
- ML-based FPS prediction using Java Smile library
- WebSocket support for bidirectional communication
- Metric history and trend analysis
- Custom alert thresholds and notifications

## 📝 License

This project is part of the Gaming Metrics Dashboard application.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📞 Support

For issues or questions:
- Create an issue in the GitHub repository
- Check the logs at `logs/spring.log`
- Review Spring Boot documentation at https://spring.io/projects/spring-boot
