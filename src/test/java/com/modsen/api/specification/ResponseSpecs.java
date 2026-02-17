package com.modsen.api.specification;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public final class ResponseSpecs {

    public static ResponseSpecification checkStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static ResponseSpecification response200ok() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
