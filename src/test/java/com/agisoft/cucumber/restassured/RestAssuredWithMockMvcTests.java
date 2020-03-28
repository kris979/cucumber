package com.agisoft.cucumber.restassured;

import com.agisoft.cucumber.GreetingController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RestAssuredWithMockMvcTests {


    @Test
    public void test() {
        RestAssuredMockMvc.standaloneSetup(new GreetingController());
        given().
                       param("name", "Johan").
                       when().
                       get("/greeting").
                       then().
                       statusCode(200).
                       body("id", equalTo(1)).
                       body("content", equalTo("Hello, Johan!"));
    }

}
