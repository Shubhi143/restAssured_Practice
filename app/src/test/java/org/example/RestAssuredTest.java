package org.example;


import static org.hamcrest.Matchers.greaterThan;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTest {
     @BeforeClass
    public void setup() {
        // Set the base URI for Rest Assured
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
@Test
    public void get() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Perform GET request
        Response response = given()
                                .when()
                                .get("/posts")
                                .then()
                                .statusCode(200) // Assert status code
                                .body("size()", greaterThan(0)) // Assert non-empty response
                                .extract()
                                .response();

        // Print response for debugging
        System.out.println(response.asString());
    }

    @Test
    public void testGetRequest() {
        // Create a request specification
        RequestSpecification request = RestAssured.given();
        // Send the GET request and receive the response
        Response response = request.get("/posts/1");
        // 
//Assert the response status code and body
 Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        System.out.println("GET Response Body: " + response.getBody().asString());
    
    }

    @Test
    public void post(){
        RequestSpecification request = RestAssured.given();
        // Set the header to specify that the request body is JSON
        request.header("Content-Type", "application/json");
        // Set the request body
        String requestBody = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": \"1\"\n" +
                "}";
        // Send the POST request and receive the response
        Response response = request.body(requestBody).post("/posts");
        // 
// Assert the response status code and body
 Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        System.out.println("POST Response Body: " + response.getBody().asString());
    }

    @Test
    public void put(){
        RequestSpecification request = RestAssured.given();
        // Set the header to specify that the request body is JSON
        request.header("Content-Type", "application/json");
        // Set the request body
        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": \"1\"\n" +
                "}";
        // Send the PUT request and receive the response
        Response response = request.body(requestBody).put("/posts/1");
        // 
// Assert the response status code and body
 Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        System.out.println("PUT Response Body: " + response.getBody().asString());
    }

    @Test
    public void delete(){
        RequestSpecification request = RestAssured.given();
        // Send the DELETE request and receive the response
        Response response = request.delete("/posts/1");
        // 
//Assert the response status code
 Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
