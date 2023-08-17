package com.spotify.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {

	public static RequestSpecification requestSpecification() {
		return new RequestSpecBuilder().setBaseUri("https://api.spotify.com").setBasePath("/v1")
				.setContentType(ContentType.JSON)
				//addHeader("Authorization", "Bearer BQCftoxo9W_Hbk5F4CUnbkt4ZbjRwsiGukmPkBIOtjtHTYZjstcARTly2cDIRLOab67qZVYCxR4Kj-On_JmBbJHVqRsnJrggQWT0jmN4VQtAUAuCzmRxxnMN12v-j2TDdFd8aBgPTrRnfz3o88vh4la14bJut1g5gOEyZ9e73EeRXKSBYl4PNLH-shhHtZlCZ2oYaDUl8DtCdH-P6Q82Nug3pRqCcc-QRDgonDnf8Mvn8McQ2Y2ukjsFmux40peQhmp47bQI2IlZQ_eW")
				.log(LogDetail.ALL).build();

	}

	public static ResponseSpecification responsespecification() {

		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL).build();

	}

}
