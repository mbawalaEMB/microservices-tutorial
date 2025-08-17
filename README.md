# Microservices Tutorial

A hands-on tutorial demonstrating a microservices architecture using Java and Spring Boot.

## Overview

This tutorial showcases a basic microservices setup featuring the following services:

- **Service Registry** – Manages discovery using Eureka.
- **API Gateway** – Routes and aggregates requests.
- **Question Service** – Handles question-related operations.
- **Quiz Service** – Manages quiz operations.

Each service is self-contained and communicates via Spring Cloud components.

## Repository Structure

- **microservices-tutorial/service-registry/** – Spring Boot application acting as Eureka Server.
- **microservices-tutorial/api-gateway/** – Spring Cloud Gateway directing external requests to internal services.
- **microservices-tutorial/question-service/** – Manages question-related API endpoints.
- **microservices-tutorial/quiz-service/** – Handles quiz-related functionality.
