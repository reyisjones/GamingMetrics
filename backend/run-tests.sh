#!/bin/bash

# Gaming Metrics Backend - Quick Start Script

echo "=========================================="
echo " Gaming Metrics Backend API"
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
echo "Building and running tests..."
echo ""

# Clean and test
$MVN_CMD clean test

if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo " ✓ All tests passed!"
    echo "=========================================="
    echo ""
    echo "To start the server, run:"
    echo "  $MVN_CMD spring-boot:run"
    echo ""
    echo "Or build and run the JAR:"
    echo "  $MVN_CMD clean package"
    echo "  java -jar target/gaming-metrics-backend-1.0.0.jar"
    echo ""
else
    echo ""
    echo "✗ Tests failed. Please check the output above."
    echo ""
    exit 1
fi
