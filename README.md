# Event Ticket Platform

A Spring Boot backend service for managing events, tickets, and user authentication with OAuth2 security.

## Quick Start

### Prerequisites
- Java 21+
- Maven 3.8.0+
- Docker & Docker Compose

### Setup

1. **Start services**:
   ```bash
   docker-compose up -d
   ```

2. **Build and run**:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

3. **Access**:
   - Application: `http://localhost:8080`
   - Adminer (Database UI): `http://localhost:8888`
   - Keycloak (Auth): `http://localhost:9090`

## Tech Stack

- **Spring Boot 4.0.3** with Java 21
- **Spring Security & OAuth2** (Keycloak)
- **Spring Data JPA** with PostgreSQL
- **Lombok** - Code generation
- **MapStruct** - Object mapping
- **Docker Compose** - Infrastructure

## Prerequisites

- Java 21+
- Maven 3.8.0+
- Docker & Docker Compose

## Getting Started

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd tickets
   ```

2. Start infrastructure:
   ```bash
   docker-compose up -d
   ```

3. Build and run:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```


## Project Structure

```
tickets/
├── src/main/java/com/areddin/tickets/
│   ├── TicketsApplication.java
│   └── domain/
│       ├── EventStatusEnum.java
│       ├── TicketStatusEnum.java
│       └── TicketValidationStatusEnum.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
├── docker-compose.yml
└── mvnw / mvnw.cmd
```

## Configuration

```properties
# Database Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=changemeinprod!

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# OAuth2 Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/event-ticket-platform
```


## Database

### Docker Services
- **PostgreSQL** (port 5432) - Default user: `postgres`, password: `changemeinprod!`
- **Adminer** (port 8888) - Database management UI

## Security

OAuth2 authentication via Keycloak. Access Keycloak admin console at `http://localhost:9090` with credentials `admin`/`admin`.

API endpoints require a valid JWT token:
```
Authorization: Bearer <your-jwt-token>
```

## Build & Development

```bash
# Build project
./mvnw clean install

# Run application
./mvnw spring-boot:run

# Run tests
./mvnw test
```

### IntelliJ IDEA Shortcuts

**Navigation:**
- `Alt + 1` - Project view
- `Ctrl + N` - Go to class
- `Ctrl + Shift + N` - Go to file

**Code Generation:**
- `Alt + 1` then `Alt + Insert` - Create new Java class
- `Ctrl + Alt + L` - Reformat code
- `Ctrl + Alt + O` - Optimize imports

**Editing:**
- `Ctrl + D` - Duplicate line
- `Alt + J` - Select next occurrence
- `Shift + F6` - Rename




