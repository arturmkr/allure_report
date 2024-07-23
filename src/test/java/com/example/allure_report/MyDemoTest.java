package com.example.allure_report;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class MyDemoTest {

    @Test
    void sendDataTest() {
        RestAssured.baseURI = "https://petstore.swagger.io/";

        Random rand = new Random();
        int randomNum = 1000 + rand.nextInt(1001);

        given()
                .filters(new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter())
                .when()
                .get("v2/pet/" + randomNum)
                .then()
                .assertThat()
                .statusCode(200);
    }
}

//