package com.weatherapi.specbuilders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {

	public static RequestSpecification requestSpecification() {
		return new RequestSpecBuilder().addQueryParam("appid", "6aca19ba1b9b6aae8054ba53e19369cb")
				.setContentType(ContentType.JSON).log(LogDetail.ALL).build();

	}

	public static ResponseSpecification responsespecification() {

		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL).build();

	}

}
