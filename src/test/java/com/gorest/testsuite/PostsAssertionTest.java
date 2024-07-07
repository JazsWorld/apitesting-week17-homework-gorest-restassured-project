package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Verify  if the total record is 25
    @Test
    public void test1() {
        response.body("size()", equalTo(25));
    }

    //2. Verify the if the title of id =  139915 is equal to ”Defaeco in carbo decet audeo volutabrum corroboro.
    @Test
    public void test2() {
        response.body("[0].title", equalTo("Defaeco in carbo decet audeo volutabrum corroboro."));
    }

    //3. Check the single user_id in the Array list (7015124)
    @Test
    public void test3() {
        response.body("[0].user_id", equalTo(7015124));
    }

    //4. Check the multiple ids in the ArrayList (139915, 139914, 139909)
    @Test
    public void test4() {
        response.body("id", hasItems(139915, 139914, 139909));
    }

    //5. Verify the body of userid = 139915 is equal “Ultio cattus patrocinor. Sint cubitum vapulus. Valetudo tertius excepturi. Convoco delego sollers. Supellex antepono admoveo. Culpa appello deleniti. Aro dolores certo. Avaritia testimonium degero. Vir culpa temeritas. Vel modi theca. Voluptas vado error. Abduco sulum desipio. Suffoco quibusdam spiritus. Ea convoco velit..
    @Test
    public void test5() {
        response.body("[0].body", equalTo("Ultio cattus patrocinor. Sint cubitum vapulus. Valetudo tertius excepturi. Convoco delego sollers. Supellex antepono admoveo. Culpa appello deleniti. Aro dolores certo. Avaritia testimonium degero. Vir culpa temeritas. Vel modi theca. Voluptas vado error. Abduco sulum desipio. Suffoco quibusdam spiritus. Ea convoco velit."));
    }

}
