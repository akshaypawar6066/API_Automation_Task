package com.spotify.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.spotify.api.SpecBuilders;
import com.spotify.pojo.CreatePlaylistRequest;



public class CreatePlaylist {
	 static String createdPlaylistId;

	@Test(priority = 1)
	public void createPlaylist()
	{
		CreatePlaylistRequest reqBody = new CreatePlaylistRequest();
		reqBody.setName("Rastrgit Songs for me");
		reqBody.setDescription("Sprictual Content of Rastragit");
		reqBody.setPublic(false);

		CreatePlaylistRequest response=given()
				.spec(SpecBuilders.requestSpecification())
				.body(reqBody)
		        .when()
		        .post("/users/31n64s22erzad25vftps2ywsj64i/playlists")
		        .then()
		        .spec(SpecBuilders.responsespecification())
		        .extract().response().as(CreatePlaylistRequest.class);
		
		    createdPlaylistId=  response.getId();
		
			}
	
	@Test(priority = 2)
	public  void getAPlaylist()
	{
		given().spec(SpecBuilders.requestSpecification())
		.pathParam("PId", createdPlaylistId)
		.when().get("/playlists/{PId}")
		.then().spec(SpecBuilders.responsespecification());
        
	}
}
