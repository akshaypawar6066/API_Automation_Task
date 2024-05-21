package com.weatherapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.weatherapi.pojo.WeatherResponseForSpecificCity;
import com.weatherapi.specbuilders.SpecBuilders;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import utils.ConfigReader;

public class GetWeatherDataForSpecificCity {

	@BeforeClass
	public void setBseURI() {
		RestAssured.baseURI = "https://api.openweathermap.org";
	}

	final Logger logger = LogManager.getLogger(GetWeatherDataForSpecificCity.class);

	@Story("weatherAPI- 01- Get a Weather details for a Specific City")
	@Description("Get Weather detaiils Based on City Name")
	@Test(priority = 1, enabled = true)
	public void getWeatherDetailsForSpecificCity() throws IOException {

		WeatherResponseForSpecificCity weatherResponse = given().spec(SpecBuilders.requestSpecification())
				.queryParam("q", "Mumbai").when().get("/data/2.5/weather").then()
				.spec(SpecBuilders.responsespecification()).statusCode(200).extract().response()
				.as(WeatherResponseForSpecificCity.class);

		// Assertion on the Response-Verify Latitude and Longitude of place
		Assert.assertEquals(ConfigReader.readPropData("expextedLat"), weatherResponse.getCoord().getLat());
		Assert.assertEquals(ConfigReader.readPropData("expectedLong"), weatherResponse.getCoord().getLon());

		// Validate Base
		Assert.assertEquals(ConfigReader.readPropData("expectedBase"), weatherResponse.getBase());

		// Verify the CIty Details
		Assert.assertEquals(ConfigReader.readPropData("expectedCityName"), weatherResponse.getName());
		Assert.assertEquals(200, weatherResponse.getCod());

		// Like this we can validate the more assertions.

		// Get The value of temperature and Humidity
		logger.info("Current temparature And humidity at " + weatherResponse.getName() + " is:" + "Temperature="
				+ weatherResponse.getMain().getTemp() + "," + "Humidity=" + weatherResponse.getMain().getHumidity());

	}

	@Story("weatherAPI- 02- Get a Response with Invalid CityName Query Params")
	@Description("Validate API By passing Invalid CityName In the Query Paramer")
	@Test(priority = 2, enabled = true)
	public void validateAPIWithQueringInvalidCityName() throws IOException {
		Response response = given().spec(SpecBuilders.requestSpecification()).queryParam("q", "Test").when()
				.get("/data/2.5/weather").then().spec(SpecBuilders.responsespecification()).statusCode(404).extract()
				.response();

		// Validation After passing Invalid CityName in the Query Parameter.
		JsonPath js = response.jsonPath();
		Assert.assertEquals(ConfigReader.readPropData("InvalidCity.code"), js.getString("cod"));
		Assert.assertEquals(ConfigReader.readPropData("InvalidCity.errorMessage"), js.getString("message"));

	}

	@Story("weatherAPI- 03- Get a Response with Invalid API Key Query Params")
	@Description("Validate API By passing Invalid API Key In the Query Paramer")
	@Test(priority = 3, enabled = true)
	public void validateAPIWithQueringInvalidAPIKey() throws IOException {

		RestAssured.baseURI = "https://api.openweathermap.org";

		HashMap<String, String> queryParams = new HashMap<>();

		queryParams.put("appid", "1234");
		queryParams.put("q", "London");

		Response response = given().log().all().queryParams(queryParams).when().get("/data/2.5/weather").then()
				.spec(SpecBuilders.responsespecification()).statusCode(401).extract().response();

		// Validation After passing Invalid API Key in the Query Parameter.
		JsonPath js = response.jsonPath();
		Assert.assertEquals(ConfigReader.readPropData("InvalidAPIKey.code"), js.getString("cod"));
		Assert.assertEquals(ConfigReader.readPropData("InvalidAPIKey.errorMessage"), js.getString("message"));

	}

	@Story("weatherAPI- 04- Get a Response for 5 days forecast for the Specific City")
	@Description("Validate the response for five days forecast of specific City")
	@Test(priority = 4, enabled = true)
	public void ValidateRetrivingTheWeatherForcastForFiveDayaForSpecificCity() throws IOException {
		Response response = given().spec(SpecBuilders.requestSpecification()).queryParam("q", "Mumbai").when()
				.get("/data/2.5/forecast").then().spec(SpecBuilders.responsespecification()).statusCode(200).extract()
				.response();
		JsonPath js = response.jsonPath();

		int size = js.getList("list").size();
		logger.info("Size of the List is:" + size);
		List<Integer> humidities = js.getList("list.main.humidity");
		List<Integer> temparatures = js.getList("list.main.temp");

		int humidityCount = humidities.size();
		int tempCount = temparatures.size();

		// Assert The count equals to 40 or not.
		Assert.assertEquals(humidityCount, 40);
		Assert.assertEquals(tempCount, 40);
	}
}
