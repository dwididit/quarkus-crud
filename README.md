# Quarkus CRUD Application

This project is a simple CRUD (Create, Read, Update, Delete) application built with Quarkus, demonstrating RESTful API development and database operations.

## Features

- User management (Create, Read, Update, Delete)
- RESTful API endpoints
- PostgreSQL database integration
- Error handling and validation

## Prerequisites

- Java 21
- Maven 3.9.8
- PostgreSQL

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell
./mvnw compile quarkus:dev
```

## Packaging and running the application
The application can be packaged using:
```shell
./mvnw package
```

## API Documentation

Swagger UI is available at:
```http://localhost:8090/q/swagger-ui```