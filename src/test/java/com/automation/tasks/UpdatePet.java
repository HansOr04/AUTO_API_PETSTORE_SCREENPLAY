package com.automation.tasks;

import com.automation.model.Pet;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdatePet implements Task {

    private final String newName;

    public UpdatePet(String newName) {
        this.newName = newName;
    }



    public static UpdatePet withNewName(String newName) {
        return instrumented(UpdatePet.class, newName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        long petId = actor.recall("petId");
        String baseUrl = actor.recall("restapi.base.url");
        Pet pet = Pet.withIdAndName(petId, newName, "available");

        var response = SerenityRest.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .extract()
                .response();

        actor.remember("lastResponse", response);
    }
}
