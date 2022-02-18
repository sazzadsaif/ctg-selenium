package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithPathVariable {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getSingleUser(){
        LOGGER.info("---------API Test: Get Single user with Path Variables------");

        RestAssured.baseURI =  "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        String id = "2";

        Response response = httpRequest.request(Method.GET,id);
        LOGGER.debug(response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(),200);

        JsonPath jsonPath = response.jsonPath();
        //List<String> listEmails = jsonPath.get("data.email");
        String actualEmailId = jsonPath.getString("data.email");

        String expectedEmailId = "janet.weaver@reqres.in";
        //boolean emailExists =  listEmails.contains(expectedEmailId);
        Assert.assertEquals(actualEmailId,expectedEmailId);


        //Assert.assertTrue(emailExists,expectedEmailId + "does not exists");


        LOGGER.info("--------API End Test: Get Single User with path variable-------");

    }
    @Test
    public void attemptToGetUserWithInvalidId(){
        LOGGER.info("---------API Test: Attempt to retrieve user with Invalid Id------");

        RestAssured.baseURI =  "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        String id = "23";

        Response response = httpRequest.request(Method.GET,id);
        LOGGER.debug(response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(),404);

        JsonPath jsonPath = response.jsonPath();
        //List<String> listEmails = jsonPath.get("data.email");
        //String actualEmailId = jsonPath.getString("data.email");

        //String expectedEmailId = "janet.weaver@reqres.in";
        //boolean emailExists =  listEmails.contains(expectedEmailId);
        Assert.assertEquals(jsonPath.get().toString(),"{}");


        //Assert.assertTrue(emailExists,expectedEmailId + "does not exists");


        LOGGER.info("--------API End Test: Get Single User with path variable-------");

    }

    }

