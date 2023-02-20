package cukeSteps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


public class TestCukeSteps {
    Properties prop=new Properties();
    FileInputStream file; {
        try {
            file = new FileInputStream("./src/test/resources/cucumber.properties");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static Response response;
    private static String cityName;
    private static String jsonString;
    private static String idString;
    private static String idStatus;

    @Given("^we make a GET call of \"(.*)\"$")
    public void testGiven(String city) throws Exception {
        cityName = city;
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        RequestSpecification request = RestAssured.given();
        response = request.get("/data/2.5/weather?q="+city+"&appid=c0b4f54bd7f4c7ef8ce8ff5b8407097d");
        jsonString = response.asString();
    }


    @When("we request the ID")
    public void testWhen() {
        int actualID = JsonPath.from(jsonString).get("id");
        idString = Integer.toString(actualID);
    }

    @Then("^we should see \"(.*)\"$")
    public void testThen(String id) {
        assertEquals(idString, id);
        System.out.println("The ID for City: " + cityName + " of " + id + " is valid.");
    }

    @Given("^we make a GET call for zip code: \"(.*)\"$")
    public void givenTest2(String zip) throws Exception {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        RequestSpecification request = RestAssured.given();
        response = request.get("/data/2.5/weather?q="+zip+"&appid=c0b4f54bd7f4c7ef8ce8ff5b8407097d");
        jsonString = response.asString();
    }

    @When("we request the status code")
    public void whenTest2() {
        int actStatus = response.getStatusCode();
        idStatus = Integer.toString(actStatus);
    }

    @Then("^we should confirm \"(.*)\"$")
    public void thenTest2(String status) {
        assertEquals(idStatus, status);
    }



}