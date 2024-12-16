package com.example.allure_report;

import groovy.util.logging.Slf4j;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Slf4j
public class MyDemoTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/";
    }

    @Test
    void sendFailure() {
        given()
                .filters(new AllureRestAssured())
                .when()
                .get("v2/pet/481345")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void sendSuccess() {
        given()
                .filters(new AllureRestAssured())
                .when()
                .queryParam("status", "available")
                .get("v2/pet/findByStatus")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
