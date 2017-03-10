package integration;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServiceIntegrationTest
{
    @BeforeClass
    public static void setUpBeforeAll() 
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8084;
        RestAssured.basePath = "/Test1";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void serverIsRunning()
    {
        given().when().get().then().statusCode(200);
    }
    
    @Test
    public void addOperation() 
    {
        given().pathParam("n1", 2).pathParam("n2", 2).
        when().get("/api/calculator/add/{n1}/{n2}").
        then().
        statusCode(200).
        body("result", equalTo(4), "operation", equalTo("2 + 2"));
    }
    
    @Test
    public void addOperationv2() 
    {
        given().
        when().get("/api/calculator/add/2/2").
        then().
        statusCode(200).
        body("result", equalTo(4), "operation", equalTo("2 + 2"));
    }
    
    @Test
    public void subOperation() 
    {
        given().
        when().get("/api/calculator/sub/2/2").
        then().
        statusCode(200).
        body("result", equalTo(0), "operation", equalTo("2 - 2"));
    }
    
    @Test
    public void nonExistantRoute() 
    {
        given().
        when().get("/api/calculator/subsssss").
        then().
        statusCode(404).
        body("code", equalTo(404), "message", equalTo("Not Found"));
    }

    @Test
    public void illegalParameter() 
    {
        given().
        when().get("/api/calculator/add/2/2.3").
        then().
        statusCode(400).
        body("code", equalTo(400));
    }
    
    @Test
    public void zeroDivision() 
    {
        given().
        when().get("/api/calculator/div/3/0").
        then().
        statusCode(500).
        body("code", equalTo(500));
    }
}
