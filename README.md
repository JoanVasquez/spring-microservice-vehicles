# Spring Microservices - Vehicle Management System

A microservices-based vehicle management system built with Spring Boot and Spring Cloud, demonstrating service discovery, configuration management, API gateway, and inter-service communication.

## Architecture

This project consists of 5 microservices:

- **Config Service** (Port: 8888) - Centralized configuration management using Spring Cloud Config
- **Eureka Service** (Port: 8761) - Service discovery and registration
- **Gateway Service** (Port: 8080) - API Gateway for routing requests to microservices
- **User Service** (Dynamic Port) - Manages users and aggregates vehicle data
- **Car Service** (Dynamic Port) - Manages car entities
- **Motor Service** (Dynamic Port) - Manages motor/motorcycle entities

## Technology Stack

- **Java 11**
- **Spring Boot 2.5.4**
- **Spring Cloud 2020.0.3**
- **Spring Data JPA**
- **H2 Database** (In-memory)
- **Netflix Eureka** (Service Discovery)
- **Spring Cloud Gateway**
- **OpenFeign** (Service Communication)
- **Maven** (Build Tool)

## Prerequisites

- Java 11 or higher
- Maven 3.6+

## Getting Started

### 1. Start Services in Order

**Step 1: Start Config Service**
```bash
cd config-service
./mvnw spring-boot:run
```

**Step 2: Start Eureka Service**
```bash
cd eureka-service
./mvnw spring-boot:run
```

**Step 3: Start Gateway Service**
```bash
cd gateway-service
./mvnw spring-boot:run
```

**Step 4: Start Business Services**
```bash
# Terminal 1
cd car-service
./mvnw spring-boot:run

# Terminal 2
cd motor-service
./mvnw spring-boot:run

# Terminal 3
cd user-service
./mvnw spring-boot:run
```

### 2. Verify Services

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080

## API Endpoints

All requests go through the Gateway Service (http://localhost:8080):

### User Service
- `GET /user` - List all users
- `GET /user/{id}` - Get user by ID
- `POST /user` - Create new user
- `GET /user/cars/{userId}` - Get cars by user ID
- `GET /user/motors/{userId}` - Get motors by user ID
- `POST /user/car/{userId}` - Add car to user
- `POST /user/motor/{userId}` - Add motor to user
- `GET /user/vehicles/{userId}` - Get user with all vehicles

### Car Service
- `GET /car` - List all cars
- `GET /car/{id}` - Get car by ID
- `POST /car` - Create new car
- `GET /car/user/{userId}` - Get cars by user ID

### Motor Service
- `GET /motor` - List all motors
- `GET /motor/{id}` - Get motor by ID
- `POST /motor` - Create new motor
- `GET /motor/user/{userId}` - Get motors by user ID

## Example Usage

### Create a User
```bash
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe", "email": "john@example.com"}'
```

### Add a Car to User
```bash
curl -X POST http://localhost:8080/user/car/1 \
  -H "Content-Type: application/json" \
  -d '{"brand": "Toyota", "model": "Camry"}'
```

### Get User with All Vehicles
```bash
curl http://localhost:8080/user/vehicles/1
```

## Configuration

Each service has its configuration in the `config-data/` directory:
- `car-service.yaml`
- `motor-service.yaml`
- `user-service.yaml`
- `gateway-service.yaml`
- `eureka-service.yaml`

## Service Communication

- **User Service** communicates with Car and Motor services using **OpenFeign** clients
- All services register with **Eureka** for service discovery
- **Gateway Service** routes requests based on path patterns
- Configuration is centralized in **Config Service**

## Database

Each service uses H2 in-memory database for simplicity. Data is reset on service restart.

## Development

### Build All Services
```bash
# From project root
for service in config-service eureka-service gateway-service car-service motor-service user-service; do
  cd $service && ./mvnw clean package && cd ..
done
```

### Run Tests
```bash
# From each service directory
./mvnw test
```

## Troubleshooting

1. **Services not registering with Eureka**: Ensure Config Service and Eureka Service are running first
2. **Gateway routing issues**: Check that target services are registered in Eureka
3. **Port conflicts**: Services use dynamic ports except Gateway (8080) and Eureka (8761)