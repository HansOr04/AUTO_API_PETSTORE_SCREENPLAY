package com.automation.questions;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Question;

public class PetApiResponse {

    public static Question<Integer> statusCode() {
        return actor -> {
            Response response = Serenity.sessionVariableCalled("lastResponse");
            return response.statusCode();
        };
    }

    public static Question<String> petName() {
        return actor -> {
            Response response = Serenity.sessionVariableCalled("lastResponse");
            return response.jsonPath().getString("name");
        };
    }
}
