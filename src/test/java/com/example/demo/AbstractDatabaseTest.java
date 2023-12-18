package com.example.demo;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

public abstract class AbstractDatabaseTest {

    private static final String IMAGE = "postgres:16.1";

    public static GenericContainer POSTGRES_CONTAINER = new GenericContainer(IMAGE)
            .withEnv("POSTGRES_USER", "postgres")
            .withEnv("POSTGRES_PASSWORD", "postgres")
            .withEnv("POSTGRES_DB", "demo")
            .withExposedPorts(5432);

    static {
        POSTGRES_CONTAINER.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=jdbc:postgresql://" + POSTGRES_CONTAINER.getHost() + ":" + POSTGRES_CONTAINER.getMappedPort(5432) + "/demo"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
