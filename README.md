# B-Apheresis

A web-based blood donation management system that facilitates matching blood donors with recipients while managing the donation process.

## Table of Contents
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Setup & Installation](#setup--installation)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)

## Features

- Donor management and registration
- Recipient tracking and management
- Blood donation process handling
- Automated donor-recipient matching
- Real-time status tracking
- Comprehensive logging system

## Technology Stack

- Java 11
- JPA/Hibernate
- PostgreSQL
- Servlet API
- JSP/JSTL
- Maven
- Logback
- JUnit

## Project Structure

```
B-Apheresis/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/                 # Application configuration
│   │   │   │   ├── DIContainer.java
│   │   │   │   └── LoggerConfig.java
│   │   │   ├── controller/             # MVC Controllers
│   │   │   ├── dao/                    # Data Access Layer
│   │   │   │   ├── impl/
│   │   │   │   └── interfaces/
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   ├── entity/                 # JPA Entities
│   │   │   │   └── enums/
│   │   │   ├── mapper/                 # DTO-Entity Mappers
│   │   │   ├── service/                # Business Logic
│   │   │   │   ├── impl/
│   │   │   │   └── interfaces/
│   │   │   ├── servlets/               # Servlet Controllers
│   │   │   └── utils/                  # Utility Classes
│   │   ├── resources/
│   │   │   ├── logback.xml
│   │   │   └── META-INF/
│   │   └── webapp/
│   │       ├── resources/
│   │       │   └── js/
│   │       └── WEB-INF/
│   │           └── views/
│   └── test/
       ├── java/
       │   └── integration/
       └── resources/
```

## Dependencies

```xml
<dependencies>
    <!-- JPA & Hibernate -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.15.Final</version>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.6.0</version>
    </dependency>
    
    <!-- Servlet API -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- JSP & JSTL -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    
    <!-- Logging -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.11</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.7</version>
    </dependency>
    
    <!-- Testing -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Setup & Installation

### Prerequisites
- JDK 11+
- Maven 3.6+
- PostgreSQL 12+
- Apache Tomcat 9+

### Database Setup
1. Create PostgreSQL database:
```bash
sudo -u postgres psql
postgres=# CREATE DATABASE b_apheresis;
postgres=# CREATE USER bapheresis_user WITH PASSWORD 'your_password';
postgres=# GRANT ALL PRIVILEGES ON DATABASE bapheresis TO bapheresis_user;
```

2. Configure `src/main/resources/META-INF/persistence.xml`:
```xml
<property name="javax.persistence.jdbc.url" 
          value="jdbc:postgresql://localhost:5432/b_apheresis"/>
<property name="javax.persistence.jdbc.user" value="bapheresis_user"/>
<property name="javax.persistence.jdbc.password" value="your_password"/>
```

### Build & Deploy
1. Clone repository:
```bash
git clone https://github.com/your-username/B-Apheresis.git
cd B-Apheresis
```

2. Build project:
```bash
mvn clean install
```

3. Deploy to Tomcat:
```bash
cp target/B-Apheresis.war $CATALINA_HOME/webapps/
```

## Running the Application

### Development Mode
```bash
mvn tomcat7:run
```
Access at: http://localhost:8080/B-Apheresis

### Production Mode
1. Start Tomcat:
```bash
$CATALINA_HOME/bin/startup.sh
```

2. Access at: http://your-server:8080/B-Apheresis

## Testing
Run tests with:
```bash
mvn test
```

## Logging
Logs are written to `logs/` directory as configured in `logback.xml`
