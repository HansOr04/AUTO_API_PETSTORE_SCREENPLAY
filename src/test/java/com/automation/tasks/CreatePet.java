package com.automation.tasks;

import com.automation.model.Pet;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreatePet implements Task {

    private final String name;
    private final String status;

    public CreatePet(String name, String status) {
        this.name = name;
        this.status = status;
    }



    public static CreatePet withName(String name, String status) {
        return instrumented(CreatePet.class, name, status);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Pet pet = Pet.withName(name, status);

        String baseUrl = actor.recall("restapi.base.url");

        var response = SerenityRest.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .extract()
                .response();

        actor.remember("lastResponse", response);
        actor.remember("petId", response.jsonPath().getLong("id"));
    }
}
