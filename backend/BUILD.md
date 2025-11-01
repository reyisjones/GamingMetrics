# Backend Build and Test Instructions

## Quick Start

### Option 1: Using Maven (if installed)

```bash
# Build and run tests
cd backend
mvn clean test

# Start the server
mvn spring-boot:run
```

### Option 2: Using Scripts

```bash
# Run tests
cd backend
chmod +x run-tests.sh
./run-tests.sh

# Start server
chmod +x start-server.sh
./start-server.sh
```

### Option 3: Manual Java Build

If Maven is not available, you can compile manually (requires Java 17+):

```bash
cd backend

# Create target directory
mkdir -p target/classes

# Compile source files (requires all dependencies)
javac -d target/classes -cp "path/to/spring-boot-jars/*" \
  src/main/java/com/gameperf/api/*.java \
  src/main/java/com/gameperf/api/model/*.java
```

## Maven Installation

### Windows (via Chocolatey)
```bash
choco install maven
```

### Windows (Manual)
1. Download Maven from https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add `C:\Program Files\Apache\maven\bin` to PATH
4. Verify: `mvn -version`

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install maven
```

### macOS (via Homebrew)
```bash
brew install maven
```

## Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=GameMetricsServiceTest
```

### Run with Coverage Report
```bash
mvn test jacoco:report
# View report at: target/site/jacoco/index.html
```

### Test Output
```
[INFO] Tests run: 37, Failures: 0, Errors: 0, Skipped: 0
```

## Building

### Build JAR (skip tests)
```bash
mvn clean package -DskipTests
```

### Build JAR (with tests)
```bash
mvn clean package
```

Output: `target/gaming-metrics-backend-1.0.0.jar`

## Running

### Development Mode
```bash
mvn spring-boot:run
```

### Production Mode (JAR)
```bash
java -jar target/gaming-metrics-backend-1.0.0.jar
```

### With Custom Port
```bash
java -jar target/gaming-metrics-backend-1.0.0.jar --server.port=9090
```

## Verification

Once the server is running, test the endpoints:

```bash
# Health check
curl http://localhost:8080/api/metrics/test

# Get metrics
curl http://localhost:8080/api/metrics

# Get analysis
curl http://localhost:8080/api/metrics/analyze

# Stream live updates (Ctrl+C to stop)
curl -N http://localhost:8080/api/metrics/live
```

Expected response from `/api/metrics/test`:
```json
{
  "status": "running",
  "message": "Backend is running - Java Metrics API ready!",
  "version": "1.0.0"
}
```

## Troubleshooting

### Maven Not Found
- Install Maven using instructions above
- Or use Maven Wrapper: `./mvnw` (requires wrapper to be generated first)

### Java Version Error
- Ensure Java 17+ is installed: `java -version`
- Set JAVA_HOME environment variable

### Port Already in Use
- Change port in `application.properties`:
  ```properties
  server.port=9090
  ```
- Or use command line:
  ```bash
  mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
  ```

### Test Failures
- Check logs in `target/surefire-reports/`
- Run individual test for debugging:
  ```bash
  mvn test -Dtest=GameMetricsServiceTest#testGetMetrics
  ```

## IDE Setup

### IntelliJ IDEA
1. Open `backend` folder as Maven project
2. Trust the project
3. Let IDEA import dependencies
4. Right-click `GamingMetricsApplication.java` → Run

### VS Code
1. Install Java Extension Pack
2. Open `backend` folder
3. VS Code will detect Maven project
4. Use Run/Debug buttons in editor

### Eclipse
1. File → Import → Maven → Existing Maven Projects
2. Browse to `backend` folder
3. Finish import
4. Right-click project → Run As → Spring Boot App

## Integration with Frontend

Update frontend API configuration:

File: `gaming-metrics-dashboard/src/api/config.json`
```json
{
  "apis": "http://localhost:8080/api/metrics"
}
```

Start both servers:
1. Backend: `mvn spring-boot:run` (port 8080)
2. Frontend: `npm run dev` (port 5173)

The frontend will consume data from the backend API.

## Test Summary

The backend includes 37+ comprehensive tests:

### GameMetricsServiceTest (9 tests)
- ✓ Returns non-empty metrics list
- ✓ Calculates performance scores correctly
- ✓ Calculates stability indexes correctly
- ✓ Returns valid analysis summary
- ✓ Validates FPS ranges
- ✓ Validates usage ranges
- ✓ Validates latency ranges
- ✓ Validates temperature ranges
- ✓ Tests data randomization

### GameAnalyticsServiceTest (12 tests)
- ✓ Analyzes stable sessions
- ✓ Detects unstable FPS
- ✓ Handles empty data
- ✓ Handles null data
- ✓ Calculates variance
- ✓ Provides recommendations
- ✓ Detects temperature anomalies
- ✓ Detects FPS anomalies
- ✓ Detects latency anomalies
- ✓ Validates normal metrics
- ✓ Includes game names
- ✓ Full anomaly detection

### GameMetricsControllerTest (11 tests)
- ✓ GET /api/metrics returns data
- ✓ GET /api/metrics/analyze returns summary
- ✓ GET /api/metrics/test health check
- ✓ Validates performance score ranges
- ✓ Validates stability index ranges
- ✓ Validates positive FPS
- ✓ Validates CPU usage range
- ✓ Validates GPU usage range
- ✓ Validates latency
- ✓ Validates temperature
- ✓ Validates game count

### GameMetricTest (5 tests)
- ✓ No-args constructor
- ✓ All-args constructor
- ✓ Setters and getters
- ✓ Equals and hashCode
- ✓ ToString method

## Next Steps

1. ✅ Build completed successfully
2. ✅ All 37 tests passing
3. ✅ API endpoints documented
4. 🔄 Start server and verify endpoints
5. 🔄 Integrate with frontend
6. 🔄 Deploy to production

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/guides/)
- [JUnit 5 Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Backend README](README.md)
