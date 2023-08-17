package com.spotify.api;

import static io.restassured.RestAssured.given;

import com.spotify.pojo.CreatePlaylistRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PlaylistAPICalls {
	
	
//	static String accessToken;
//	
//	public PlaylistAPICalls(String accessToken)
//	{
//		PlaylistAPICalls.accessToken=accessToken;
//	}
//	
//	public static String getAccessTokenFromRefreshToken()
//	{
//		String refreshToken = "AQAnwpF4WAt8BunMJHfYOzwLR2mZkB1NI4QhMhVJVxhqYolX1Pgv_0Y9oUqJgJpdd89G0a27PCziFUJZ9fT4Q7PscMg5G8Y0Pu_43ECBiDtENmidwn1ndHLIDZkH08rYeWY";
//        String clientId = "7d6dbf5f9a064863b07d96a641533693";
//        String clientSecret = "855b9bf1b09d41b1b8a7fb5042c40e4a";
//        RestAssured.baseURI="https://accounts.spotify.com";
//        
//       Response response=  given().log().all()
//        .header("Content-Type", "application/x-www-form-urlencoded")
//        .header("Cookie", "__Host-device_id=AQD9A6uO-1A7yH1YO12LCyEtbuwKOiBH5AXwG9xRPmJs8FO9qad3_-IjObi-R4RewY_dGW8b_2HNkY-F6lA6uRd3ZKSONHO-wOs; sp_tr=false")
//        .formParam("grant_type", "refresh_token")
//        .formParam("refresh_token", refreshToken)
//        .formParam("client_id", clientId)
//        .formParam("client_secret", clientSecret)
//        .when().post("/api/token")
//        .then().log().all()
//        .extract().response();
//     return  response.jsonPath().getString("access_token");
//        
//        
//	}

	public static Response postRequest(CreatePlaylistRequest reqBody, String token) {
		return given()

				//.header("Authorization","Bearer "+accessToken)
				.header("Authorization","Bearer "+token)

				
				.spec(SpecBuilders.requestSpecification())

				.body(reqBody)

				.when()

				.post("/users/31n64s22erzad25vftps2ywsj64i/playlists")

				.then()
				
				.spec(SpecBuilders.responsespecification())
				
				.extract().response();
	}

	public static Response getRequest(String playlistId, String token) {
		return given()

				//.header("Authorization","Bearer "+accessToken)
				.header("Authorization","Bearer "+token)

				
				.spec(SpecBuilders.requestSpecification())

				.pathParam("PID", playlistId)

				.when().get("/playlists/{PID}")

				.then()
				
				.spec(SpecBuilders.responsespecification())

				.extract()

				.response();
	}

	public static Response updateRequest(CreatePlaylistRequest reqBody, String playListId, String token) {
		return given()

				//.header("Authorization","Bearer "+accessToken)
				.header("Authorization","Bearer "+token)

				
				.spec(SpecBuilders.requestSpecification())

				.body(reqBody)

				.when()

				.put("/playlists/" + playListId)

	            .then()
				
				.log().all()
				
				.extract().response();
	}

}
