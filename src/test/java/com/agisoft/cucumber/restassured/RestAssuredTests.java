package com.agisoft.cucumber.restassured;

import io.restassured.RestAssured;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class RestAssuredTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testFoods() {
        given().header("Authorization", "Bearer " + "eyJraWQiOiJDX0x5NFcxWlh5bWp3NjdYOWlpRGxpZHBIVDFSOUFqX0hPaUNzNjhDMUdNIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULmtTZjNTZEVxZjBaVjVpNy1ZRE1PcGh4VG1YYTVJRFNGM1hBTGs2VzJSclUiLCJpc3MiOiJodHRwczovL2Rldi0yNTA1MDQub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNTg1MzI1MzI4LCJleHAiOjE1ODUzMjg5MjgsImNpZCI6IjBvYTR5anZtZmZ4NE03UHQzNHg2IiwidWlkIjoiMDB1NHlqc2NjNFUxUFRRenY0eDYiLCJzY3AiOlsicHJvZmlsZSIsImVtYWlsIiwiYWRkcmVzcyIsInBob25lIiwib3BlbmlkIl0sInN1YiI6ImtyaXM5NzlAZ21haWwuY29tIn0.UkGgNA6_HYujE_RFvi4ob500yqhIRoIJaU9dNSxwDFDPQL7xl9qYCE4SAmjaeQA0pruNEakMxvHcwCunYiLgJ_AlMwqIm1H1sUdKEA4rtvZm8trvqypaGhFHEjEmR01ktRl3M1-wbdv8zWxl05QUVpUd3P8A9qmS_iu9ycaKAIiBwUwptKP-AKpP2ZreVjI28W2Qv8aVgNoHV46yjInpksElvr98zXhvy84XxNE3iEbHrP-QQ8VX4AlfzdK6OyE4hDYUU2_hmp_GCmiWy0-5xpPIEZGpCXVcLl8aUMueDHrry9oIk-r2PfaI4OmtoVzDW6ZfTy-S6Eu4UFFkaXpqsg").
        when().
                       get("/foods").
                       then().
                       statusCode(200).
                       assertThat().body("", hasSize(4));
    }

    @Test
    public void testFoods1() {
        given().auth().oauth2("eyJraWQiOiJDX0x5NFcxWlh5bWp3NjdYOWlpRGxpZHBIVDFSOUFqX0hPaUNzNjhDMUdNIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULmtTZjNTZEVxZjBaVjVpNy1ZRE1PcGh4VG1YYTVJRFNGM1hBTGs2VzJSclUiLCJpc3MiOiJodHRwczovL2Rldi0yNTA1MDQub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNTg1MzI1MzI4LCJleHAiOjE1ODUzMjg5MjgsImNpZCI6IjBvYTR5anZtZmZ4NE03UHQzNHg2IiwidWlkIjoiMDB1NHlqc2NjNFUxUFRRenY0eDYiLCJzY3AiOlsicHJvZmlsZSIsImVtYWlsIiwiYWRkcmVzcyIsInBob25lIiwib3BlbmlkIl0sInN1YiI6ImtyaXM5NzlAZ21haWwuY29tIn0.UkGgNA6_HYujE_RFvi4ob500yqhIRoIJaU9dNSxwDFDPQL7xl9qYCE4SAmjaeQA0pruNEakMxvHcwCunYiLgJ_AlMwqIm1H1sUdKEA4rtvZm8trvqypaGhFHEjEmR01ktRl3M1-wbdv8zWxl05QUVpUd3P8A9qmS_iu9ycaKAIiBwUwptKP-AKpP2ZreVjI28W2Qv8aVgNoHV46yjInpksElvr98zXhvy84XxNE3iEbHrP-QQ8VX4AlfzdK6OyE4hDYUU2_hmp_GCmiWy0-5xpPIEZGpCXVcLl8aUMueDHrry9oIk-r2PfaI4OmtoVzDW6ZfTy-S6Eu4UFFkaXpqsg").
                when().
                       get("/foods").
                       then().
                       statusCode(200).
                       assertThat().body("", hasSize(4));
    }
}
