package com.example.demo.controller;

import com.example.demo.AbstractRestIntegrationTest;
import com.example.demo.generated.server.model.DemoItemDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class DemoItemControllerTests extends AbstractRestIntegrationTest {

	@Test
	void store() {
		DemoItemDTO requestDTO = new DemoItemDTO();

		DemoItemDTO responseDTO = RestAssured
				.given()
				.when()
				.body(requestDTO)
				.header(jsonContentTypeHeader())
				.post("/demo-items")
				.then()
				.statusCode(HttpStatus.OK.value())
				.extract()
				.body()
				.as(DemoItemDTO.class);

		Assertions.assertEquals(requestDTO.getTitle(), responseDTO.getTitle());
		Assertions.assertNotNull(responseDTO.getId());

		responseDTO = RestAssured
				.given()
				.when()
				.body(requestDTO)
				.header(jsonContentTypeHeader())
				.get("/demo-items/" + responseDTO.getId())
				.then()
				.statusCode(HttpStatus.OK.value())
				.extract()
				.body()
				.as(DemoItemDTO.class);

		Assertions.assertEquals(requestDTO.getTitle(), responseDTO.getTitle());
		Assertions.assertNotNull(responseDTO.getId());
	}

}
