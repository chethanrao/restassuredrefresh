package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

import java.util.*;

public class RestAssuredExample {
 
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/employees";
    }

    @Test
    public void verifyStatusCode() {
        Response response = given().when().get();
        Assert.assertEquals("Status code is not 200",200,response.getStatusCode());
    }

    @Test
    public void verifyAtleastOneRecord() {
        Response response = given().when().get();
        JsonPath jsonPath = response.jsonPath();
        List<Map<Object,Object>> employeeData = jsonPath.get("data");
        Assert.assertTrue("No records found",employeeData.size()>0);
    }

    @Test
    public void verifyAtleastOneRecordGreaterThanHundredThousand() {
        Response response = given().when().get();
        JsonPath jsonPath = response.jsonPath();
        List<Map<Object, Object>> employeeData = jsonPath.get("data.findAll{data->data.employee_salary>100000}");
        Assert.assertTrue("No records found", employeeData.size() > 0);
    }
}
