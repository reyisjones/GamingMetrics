#!/bin/bash

# Gaming Metrics Backend - Server Start Script

echo "=========================================="
echo " Starting Gaming Metrics Backend API"
echo "=========================================="
echo ""

# Check if Maven is installed
if command -v mvn &> /dev/null; then
    echo "✓ Maven found"
    MVN_CMD="mvn"
elif [ -f "./mvnw" ]; then
    echo "✓ Using Maven Wrapper"
    MVN_CMD="./mvnw"
else
    echo "✗ Maven not found!"
    echo ""
    echo "Please install Maven or run:"
    echo "  mvn -N io.takari:maven:wrapper"
    echo ""
    exit 1
fi

echo ""
echo "Starting Spring Boot application..."
echo "Server will be available at: http://localhost:8080"
echo ""
echo "API Endpoints:"
echo "  GET  http://localhost:8080/api/metrics"
echo "  GET  http://localhost:8080/api/metrics/analyze"
echo "  GET  http://localhost:8080/api/metrics/live"
echo "  GET  http://localhost:8080/api/metrics/test"
echo ""
echo "Press Ctrl+C to stop the server"
echo ""

$MVN_CMD spring-boot:run
