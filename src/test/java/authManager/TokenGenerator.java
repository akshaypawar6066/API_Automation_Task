package authManager;

import java.io.IOException;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ConfigReader;

import static io.restassured.RestAssured.*;

public class TokenGenerator {

	public static String renewToken() throws IOException {
		
		//ConfigReader configReader=new ConfigReader();
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "refresh_token");
		param.put("refresh_token",ConfigReader.readPropData("re1freshToken"));
		param.put("client_id", ConfigReader.readPropData("clientId"));
		param.put("client_secret", ConfigReader.readPropData("clientSecretId"));

		RestAssured.baseURI = "https://accounts.spotify.com";

		Response response = given().log().all().contentType(ContentType.URLENC)

				.formParams(param)

				.when().post("/api/token")

				.then().log().all()

				.extract().response();
		
		if(response.getStatusCode()!=200)
		{
			throw new RuntimeException("Failed to generate the access token");
		}

		return response.jsonPath().getString("access_token");

	}

}
