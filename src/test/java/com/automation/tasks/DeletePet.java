package com.automation.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeletePet implements Task {

    public static DeletePet byStoredId() {
        return instrumented(DeletePet.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        long petId = actor.recall("petId");
        String baseUrl = actor.recall("restapi.base.url");

        var response = SerenityRest.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .when()
                .delete("/pet/" + petId)
                .then()
                .extract()
                .response();

        actor.remember("lastResponse", response);
    }
}
