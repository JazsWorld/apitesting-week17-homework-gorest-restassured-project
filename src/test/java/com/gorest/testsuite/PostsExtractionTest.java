package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {
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

    //  1. Extract the title
    @Test
    public void test001() {
        List<Object> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Title are : " + title);//.size()
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        List<Object> totalRecord = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total records are : " + totalRecord.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Body of 15th Record : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Object> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total records are : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<Object> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total Title are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 139914
    @Test
    public void test006() {
        List<?> allRecords = response.extract().path("findAll{it.user_id == 139914}.record");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("UserId Record : " + allRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 139914
    @Test
    public void test007() {
        List<?> allRecordWhoseID2670 = response.extract().path("findAll{it.id==2670}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("UserId Record Body : " + allRecordWhoseID2670);
        System.out.println("------------------End of Test---------------------------");
    }
}
