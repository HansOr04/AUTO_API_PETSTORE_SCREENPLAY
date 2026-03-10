package com.automation.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class DeletePet implements Performable {

    public static DeletePet byStoredId() {
        return new DeletePet();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        long petId = Serenity.sessionVariableCalled("petId");

        var response = SerenityRest.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json")
                .when()
                .delete("/pet/" + petId)
                .then()
                .extract()
                .response();

        Serenity.setSessionVariable("lastResponse").to(response);
    }
}
