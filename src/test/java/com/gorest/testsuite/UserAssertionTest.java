package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify if the total record is 25
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the name of id = 7015052 is equal to ”Deeptimoyee Nair”
    @Test
    public void test002() {
        response.body("find{it.id == 7015052}.name", equalTo("Deeptimoyee Nair"));
    }

    //3. Check the single ‘Name’ in the Array list (Daksha Patel)
    @Test
    public void test003() {
        response.body("name", hasItem("Daksha Patel"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList ("Menka Marar", "Nawal Johar", "Dhanapati Mishra" )
    @Test
    public void test004() {
        response.body("name", hasItems("Menka Marar", "Nawal Johar", "Dhanapati Mishra"));
    }

    //5. Verify the email of userid = 7015046 is equal "patel_urmila@rowe-abernathy.example"
    @Test
    public void test005() {
        response.body("find{it.id == 7015046}.email", equalTo("patel_urmila@rowe-abernathy.example"));
    }

    //6. Verify the status is “Active” of user name is “Dandapaani Agarwal”
    @Test
    public void test6() {
        response.body("[5].status", equalTo("active"));
    }

    //7. Verify the Gender = male
    @Test
    public void test7() {
        response.body("[1].gender", equalTo("male"));

    }

}
