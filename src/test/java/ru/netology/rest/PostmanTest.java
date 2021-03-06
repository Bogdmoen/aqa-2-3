package ru.netology.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanTest {

    @Test
    void ShouldReturn200() {
        given()
                .baseUri("https://postman-echo.com")
                .body("some data")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    void ShouldReturnBody() {
        given()
                .baseUri("https://postman-echo.com")
                .body("some data")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", equalTo("some data"));
    }

    @Test
    void ShouldReturnWithBody() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("var1", "test1");
        jsonAsMap.put("var2", "test2");

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON)
                .body(jsonAsMap)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.var1", equalTo("test1"))
                .body("data.var2", startsWith("t"));
    }


}
