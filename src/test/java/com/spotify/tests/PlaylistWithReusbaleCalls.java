package com.spotify.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.spotify.api.PlaylistAPICalls;
import com.spotify.pojo.CreatePlaylistRequest;

import authManager.TokenGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import junit.framework.Assert;
import utils.ConfigReader;

@Epic("OAuth 2.0 Functionality")
@Feature("Playlist API Feature")
public class PlaylistWithReusbaleCalls {
	static String playListId;

//	@Test(priority = 0)
//	public void getAccessToken() {
//
//	  String accessToken = PlaylistAPICalls.getAccessTokenFromRefreshToken();
//		PlaylistAPICalls playlistAPICalls=new PlaylistAPICalls(accessToken);
//
//	}
@Story("sps- 01- Create a playlist")
@Description("Creation of playlist using test data")
	@Test(priority = 1)
	public void createPlaylist() throws IOException {
		CreatePlaylistRequest reqPlaylist = new CreatePlaylistRequest();
		reqPlaylist.setName(ConfigReader.readPropData("name"));
		reqPlaylist.setDescription(ConfigReader.readPropData("description"));
		reqPlaylist.setPublic(false);

		Response response = PlaylistAPICalls.postRequest(reqPlaylist, TokenGenerator.renewToken());

		CreatePlaylistRequest responsePlaylist = response.as(CreatePlaylistRequest.class);

		playListId = responsePlaylist.getId();

		String requestName = reqPlaylist.getName();
		String responseName = responsePlaylist.getName();

		Assert.assertEquals(responseName, requestName);

	}

@Story("sps- 02- Get a  playlist")
@Description("get the details of the created playlist")
	@Test(priority = 2)
	public void getAPlaylist() throws IOException {
		Response response = PlaylistAPICalls.getRequest(playListId, TokenGenerator.renewToken());
		CreatePlaylistRequest responsePlayList = response.as(CreatePlaylistRequest.class);

		String description = responsePlayList.getDescription();

		System.out.println("Description is:" + description);
	}

@Story("sps- 03 - Edit a playlist")
@Description("Updating the exising playlist")

	@Test(priority = 3)
	public void updateAPlaylist() throws IOException {

		CreatePlaylistRequest reqPlaylist = new CreatePlaylistRequest();
		reqPlaylist.setName(ConfigReader.readPropData("name")+"Akshay's_Palylist");
		reqPlaylist.setDescription(ConfigReader.readPropData("description")+"Akshays_Playlist_Description");
		reqPlaylist.setPublic(false);

		Response response = PlaylistAPICalls.updateRequest(reqPlaylist, playListId, TokenGenerator.renewToken());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

@Story("sps- 04- Regression of application")
@Description("Passing wrong Bearer token to check Authorization functionality.")

	@Test(priority = 4)
	public void shouldNotbeAuthorized() {
		CreatePlaylistRequest reqPlaylist = new CreatePlaylistRequest();
		reqPlaylist.setName("Rastrgit Songs for me_15_August");
		reqPlaylist.setDescription("Sprictual Content of Rastragit_15_August");
		reqPlaylist.setPublic(false);

		Response response = PlaylistAPICalls.postRequest(reqPlaylist,
				"BQCftoxo9W_Hbk5F4CUnbkt4ZbjRwsiGuk");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(400, statusCode);

	}
}
