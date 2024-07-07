package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static int userId;
    static String name = TestUtils.getRandomValue() + "PrimeUser";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String gender = "male";
    static String status = "active";

    @Test
    public void T1verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);

        userId = response.then().extract().path("id");
        System.out.println("User ID is: " + userId);
    }

    @Test
    public void T2verifyUserReadSuccessfully() {
        Response response = given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Content-Type", "application/json")
                .when()
                .get("/users/" + userId);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void T3verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        Response response = given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .put("/users/"+userId);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void T4VerifyUserDeleteSuccessfully() {
        Response response = given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Connection", "keep-alive")
                .when()
                .delete("/users/"+userId);
        response.prettyPrint();
        response.then().statusCode(204);
    }
}
