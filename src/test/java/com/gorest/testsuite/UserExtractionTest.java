package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {

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

    //1. Extract the All Ids
    @Test
    public void test01() {
        List<Object> totalId = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total ids are : " + totalId.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test02() {
        List<?> allName = response.extract().path("names");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all names are : " + allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test03() {
        String objectName = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of 5th store : " + objectName);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test04() {
        List<?> allInActiveStatus = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Status of Inactive: " + allInActiveStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test05() {
        List<?> allActiveGenderStatus = response.extract().path("findAll{it.status == 'active'}.gender");
        List<?> allActiveStatusGender = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Status of Active: " + allActiveGenderStatus);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test06() {
        List<?> allObjectWithGenderFemale = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is Female: " + allObjectWithGenderFemale);
        System.out.println("------------------End of Test---------------------------");

    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test07() {
        List<?> allEmailsOfStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email Of Inactive : " + allEmailsOfStatusInactive);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test08() {
        List<?> allIdWithGenderMale = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is Female: " + allIdWithGenderMale);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test09() {
        List<Integer> statusList = response.extract().path("status");
        statusList.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Status list : " + statusList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Dev Tagore
    @Test
    public void test10() {
        List<?> emails = response.extract().path("findAll{it.name == 'Dev Tagore'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email Of Dev Tagore : " + emails);
        System.out.println("------------------End of Test---------------------------");
    }


    //11. Get gender of id = 7015089

    @Test
    public void test11() {
        List<?> getGenderOfId7015089 = response.extract().path("findAll{it.id == 7015089 }.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender Of Id7015089 : " + getGenderOfId7015089);
        System.out.println("------------------End of Test---------------------------");
    }
}
