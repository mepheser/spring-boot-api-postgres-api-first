package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {AbstractDatabaseTest.Initializer.class})
public abstract class AbstractRestIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = port;
        RestAssured.basePath = "/";
        RestAssured.defaultParser = Parser.JSON;

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((type, s) -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JsonNullableModule());
                    objectMapper.registerModule(new JavaTimeModule());
                    return objectMapper;
                }));
    }

    protected Header jsonContentTypeHeader() {
        return new Header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
