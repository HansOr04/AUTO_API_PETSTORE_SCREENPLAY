package com.automation.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class UpdatePet implements Performable {

    private final String newName;

    public UpdatePet(String newName) {
        this.newName = newName;
    }

    public static UpdatePet withNewName(String newName) {
        return new UpdatePet(newName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        long petId = Serenity.sessionVariableCalled("petId");

        String body = "{"
                + "\"id\": " + petId + ","
                + "\"name\": \"" + newName + "\","
                + "\"status\": \"available\""
                + "}";

        var response = SerenityRest.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json")
                .body(body)
                .when()
                .put("/pet")
                .then()
                .extract()
                .response();

        Serenity.setSessionVariable("lastResponse").to(response);
    }
}
