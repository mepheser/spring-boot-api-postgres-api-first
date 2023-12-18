# Spring Boot 3 Postgres API First Showcase

A simple showcase setup of a Spring Boot REST Microservice using

* Spring Boot 3 created with [Spring Initilizr](https://start.spring.io)
* JPA with postgres and liquibase migration
* REST with api-first generated model and controller interfaces
  using [openapi generator](https://openapi-generator.tech)
* Tests with containerized postgres ans [testcontainers](https://testcontainers.com)
* Docker build setup

## Features

Projects creates a REST microservice with CRUD operations for a simple `DemoItem` entity with a sequential id and a
title as string.

## Dependencies

Setup is intended to keep dependencies to libraries at absolute minimum. `pom.xml` includes

* `spring-boot-starter-parent` for parent (including bom)
* `spring-boot-starter-web` for REST controllers
* `spring-boot-starter-data-jpa`, `postgres`, `liquibase-core` for persistence
* `jackson-databind-nullable`, `jakarta.validation-api` for annotations used in created API model code
* `spring-boot-starter-test` for spring boot tests
* `spring-boot-testcontainers`, `junit-jupiter` for postgres docker containers in tests
* `rest-assured` for REST integration tests

## API-first code generation

REST API is described as openapi3 desctiption in `src/main/api/demo-api.yaml`. API model DTOS and Spring Boot REST
controller
inerfaces are generated on build time using `openapi-generator-maven-plugin`
into `com.example.demo.generated.server.model.*` and `com.example.demo.generated.server.api.*`.
Generation is configured with opinionated sane best practices:

* `<useSpringBoot3>true</useSpringBoot3>` to get spring boot 3 ready code (eg. no javax.* Annotations)
* `<useTags>true</useTags>` to get separate interfaces by tag
* `<interfaceOnly>true</interfaceOnly> and <skipDefaultInterface>true</skipDefaultInterface>` to get only interfaces for
  controllers, no boilerplate of default implementations and delegates
* `<documentationProvider>none</documentationProvider> and <annotationLibrary>none</annotationLibrary> and <useBeanValidation>false</useBeanValidation>`
  to keep interfaces clean. Generates only Spring Boot REST annotations

Just run `mvn clean install` once first to get generated code in IDE.

## Liquibase migration

Adding `liquibase-core` is enough to enable default liquibase setup. This searches for `db/changelog/db.changelog-master.yaml` in classpath. Just add sql scripts here.

## Unit tests with postgres container

Unit tests are configured to start an internal postgres docker container with testcontainers. Liquibase automatically creates db structure automatically.
Spring boot is automatically configured to use this postgres in jpa conf using custom `ApplicationContextInitializer`, see `AbstractDatabaseUnitTest`.   

## Docker dev db

To execute REST api locally in dev mode, run `docker-compose up db` to start an empty postgres container on port 9001. Start spring boot app with active profile `dev` to connect to the db. 



## Docker build for deployment

Build docker image and push to registry in ci/cd. To test locally:

```
mvn clean install
docker-compose build api
docker-compose push api
```

On target host, create a `compose.yaml` specifying requested profile:

```
version: '3.7'

services:
  api:
    image: 'demo-api:${IMAGE_VERSION:-latest}'
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: my-hosts-profile
```

and use docker compose for deployment:

* `docker-compose pull` get latest version. use .env for specific `IMAGE_VERSION`
* `docker-compose down` shutdown current running version
* `docker-compose up -d` start new version



