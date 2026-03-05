# Event Ticket Platform

A Spring Boot-based RESTful API for managing event ticketing, including event creation, ticket sales, validation, and user management with OAuth2 security.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Database](#database)
- [Security](#security)
- [Development Tools](#development-tools)
- [Contributing](#contributing)

## 📖 Project Overview

The Event Ticket Platform is a comprehensive ticketing system that enables:
- **Event Management**: Create and manage events with various statuses
- **Ticket Management**: Issue, sell, and validate event tickets
- **User Management**: Handle user accounts and roles
- **Security**: OAuth2 authentication with Keycloak integration
- **Validation**: Ticket validation with multiple validation statuses

## 🛠 Tech Stack

### Backend Framework
- **Spring Boot 4.0.3** - Modern Java application framework
- **Spring Security** - Authentication and authorization
- **Spring OAuth2 Resource Server** - OAuth2 integration with Keycloak
- **Spring Data JPA** - Object-relational mapping and database access

### Database
- **PostgreSQL** - Primary production database
- **H2** - In-memory database for testing

### Additional Libraries
- **Lombok 1.18.36** - Boilerplate code generation (getters, setters, constructors)
- **MapStruct 1.6.3** - Object mapping between entities and DTOs
- **Java 21** - Latest LTS Java version

### Infrastructure
- **Docker & Docker Compose** - Containerization and orchestration
- **Keycloak** - OAuth2/OIDC authentication server
- **Adminer** - Database management UI

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21** or higher
- **Maven 3.8.0** or higher
- **Docker** and **Docker Compose**
- **Git**
- **IntelliJ IDEA** (recommended) or any Java IDE

### Verify Installation
```bash
java -version
mvn -version
docker --version
docker-compose --version
```

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd tickets
```

### 2. Start Infrastructure with Docker
```bash
docker-compose up -d
```

This will start:
- **PostgreSQL** (port 5432) - Main database
- **Adminer** (port 8888) - Database management UI
- **Keycloak** (port 9090) - Authentication server

### 3. Build the Project
```bash
./mvnw clean install
```

On Windows:
```bash
mvnw.cmd clean install
```

### 4. Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### 5. Verify Setup
- Application: `http://localhost:8080`
- Adminer (Database UI): `http://localhost:8888`
- Keycloak (Auth): `http://localhost:9090`

## 📁 Project Structure

```
tickets/
├── src/
│   ├── main/
│   │   ├── java/com/areddin/tickets/
│   │   │   ├── TicketsApplication.java          # Main Spring Boot entry point
│   │   │   └── domain/                          # Domain entities
│   │   │       ├── EventStatusEnum.java
│   │   │       ├── TicketStatusEnum.java
│   │   │       └── TicketValidationStatusEnum.java
│   │   └── resources/
│   │       └── application.properties           # Application configuration
│   └── test/
│       └── java/com/areddin/tickets/
│           └── TicketsApplicationTests.java    # Integration tests
├── pom.xml                                      # Maven configuration
├── docker-compose.yml                          # Docker infrastructure
├── mvnw / mvnw.cmd                            # Maven wrapper scripts
└── README.md                                    # This file
```

## ⚙️ Configuration

### Application Properties (`application.properties`)

```properties
# Application
spring.application.name=tickets

# Database Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=changemeinprod!

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update          # Auto-create/update schema
spring.jpa.show-sql=true                      # Log SQL queries
spring.jpa.properties.hibernate.format_sql=true

# OAuth2 Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/event-ticket-platform
```

### Important Notes
- 🔐 **Change the database password** in production (`changemeinprod!`)
- 🔑 **Configure Keycloak realm** before deployment
- 📊 **Set `ddl-auto=validate`** in production (instead of `update`)

## 🗄️ Database

### Docker Compose Services

#### PostgreSQL
- **Port**: 5432
- **Default User**: postgres
- **Default Password**: changemeinprod!
- **Database**: postgres

#### Adminer (Database Management)
- **URL**: http://localhost:8888
- **Login with PostgreSQL credentials**

### Database Schema

The application uses JPA with Hibernate to auto-generate the schema based on entity annotations:

**Key Entities:**
- **User** - User accounts and profiles
- **Event** - Event details and configuration
- **Ticket** - Individual tickets for events
- **Validation** - Ticket validation records

### Migrations

Currently using `ddl-auto=update` for development. For production, use Flyway or Liquibase migrations.

## 🔐 Security

### OAuth2 with Keycloak

The application is secured using OAuth2 with Keycloak as the authorization server.

#### Keycloak Setup
1. Access Keycloak Admin Console: `http://localhost:9090`
2. Default credentials: `admin` / `admin`
3. Create a new realm: `event-ticket-platform`
4. Create a client for your application
5. Configure JWT issuer-uri in `application.properties`

#### Security Configuration
```
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/event-ticket-platform
```

#### Protected Endpoints
All API endpoints require a valid JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## 🧑‍💻 Development Tools

### IntelliJ IDEA Shortcuts

#### Navigation
- `Alt + 1` - Focus Project tool window
- `Alt + 7` - Focus Structure tool window
- `Ctrl + N` - Go to class
- `Ctrl + Shift + N` - Go to file
- `Ctrl + E` - Recent files

#### Code Generation
- `Alt + 1` then `Alt + Insert` - Create new Java class
- `Alt + Insert` (in editor) - Generate code (getters, setters, constructors)
- `Ctrl + Alt + L` - Reformat code
- `Ctrl + Alt + O` - Optimize imports

#### Editing
- `Ctrl + D` - Duplicate line
- `Ctrl + Y` - Delete line
- `Alt + J` - Select next occurrence of word
- `Ctrl + /` - Comment/uncomment line
- `Shift + F6` - Rename

#### Running & Debugging
- `Shift + F10` - Run application
- `Shift + F9` - Debug application
- `Ctrl + F2` - Stop application

### Build Commands

```bash
# Build project
./mvnw clean install

# Run application
./mvnw spring-boot:run

# Run tests
./mvnw test

# Run specific test
./mvnw test -Dtest=TicketsApplicationTests

# Build with skipping tests
./mvnw clean install -DskipTests

# Check for dependency vulnerabilities
./mvnw dependency-check:check
```

### Maven Wrapper

The project includes Maven Wrapper (`mvnw`) for consistent builds:
- Linux/macOS: `./mvnw`
- Windows: `mvnw.cmd`

No need to install Maven separately!

## 📚 Key Libraries & Their Use

### Lombok
Reduces boilerplate code:
```java
@Getter           // Auto-generates getters
@Setter           // Auto-generates setters
@NoArgsConstructor // Empty constructor
@AllArgsConstructor // Constructor with all fields
@Builder          // Builder pattern
```

### MapStruct
Type-safe object mapping between entities and DTOs:
- Compile-time code generation
- Zero runtime overhead
- Better than manual mapping

### Spring Security OAuth2
- Token validation
- JWT processing
- Keycloak integration

## 🔄 Development Workflow

### 1. Create a New Entity
```bash
Alt + 1 → Navigate to domain package
Alt + Insert → Select "Java Class"
Add @Entity, @Table, @Getter, @Setter, @Builder annotations
Add @Id and @GeneratedValue
```

### 2. Format Code
```bash
Ctrl + Alt + L  # Format current file
Ctrl + Alt + O  # Organize imports
```

### 3. Run Tests
```bash
./mvnw test
```

### 4. Run Application
```bash
./mvnw spring-boot:run
# Or use Shift + F10 in IntelliJ
```

## 🤝 Contributing

### Code Standards
- Use Lombok annotations to reduce boilerplate
- Format code with `Ctrl + Alt + L` before committing
- Write unit tests for new features
- Follow Spring Boot conventions

### Branch Naming
- Feature: `feature/description`
- Bug fix: `bugfix/description`
- Hotfix: `hotfix/description`

### Pull Request Process
1. Create feature branch from `main`
2. Write tests for new functionality
3. Format code with IntelliJ
4. Submit PR with clear description
5. Wait for review and CI/CD checks

## 📝 License

This project is part of the Event Ticket Platform initiative (2026).

## 👨‍💼 Authors

- **Areddin** - Initial development

## 📞 Support

For issues or questions:
1. Check existing GitHub issues
2. Create a new issue with detailed description
3. Contact the development team

## 🚦 Status

- **Version**: 0.0.1-SNAPSHOT
- **Java**: 21
- **Spring Boot**: 4.0.3
- **Status**: In Active Development

---

**Last Updated**: March 2026

