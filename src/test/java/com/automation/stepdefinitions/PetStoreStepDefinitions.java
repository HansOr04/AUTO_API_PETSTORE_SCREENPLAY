package com.automation.stepdefinitions;

import com.automation.questions.PetApiResponse;
import com.automation.tasks.CreatePet;
import com.automation.tasks.DeletePet;
import com.automation.tasks.GetPet;
import com.automation.tasks.UpdatePet;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreStepDefinitions {

    private final EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();

    private Actor actor;
    private String lastUpdatedName;

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("tester");
        String baseUrl = env.getProperty("restapi.base.url",
                              "https://petstore.swagger.io/v2");
        actor.remember("restapi.base.url", baseUrl);
    }

    @Dado("el sistema tiene disponible la API de PetStore")
    public void elSistemaTieneDisponibleLaAPIDePetStore() {
    }

    @Cuando("el usuario crea una nueva mascota con nombre {string} y estado {string}")
    public void elUsuarioCreaUnaNuevaMascota(String nombre, String estado) {
        actor.attemptsTo(CreatePet.withName(nombre, estado));
        actor.should(seeThat(PetApiResponse.statusCode(), equalTo(200)));
    }

    @Entonces("la mascota creada puede ser consultada por su identificador")
    public void laMascotaCreadaPuedeSerConsultada() {
        actor.attemptsTo(GetPet.byStoredId());
        actor.should(seeThat(PetApiResponse.statusCode(), equalTo(200)));
    }

    @Cuando("el usuario actualiza el nombre de la mascota a {string}")
    public void elUsuarioActualizaElNombreDeLaMascota(String nuevoNombre) {
        lastUpdatedName = nuevoNombre;
        actor.attemptsTo(UpdatePet.withNewName(nuevoNombre));
        actor.should(seeThat(PetApiResponse.statusCode(), equalTo(200)));
    }

    @Entonces("la mascota consultada refleja el nombre actualizado")
    public void laMascotaConsultadaReflejaElNombreActualizado() {
        actor.attemptsTo(GetPet.byStoredId());
        actor.should(seeThat(PetApiResponse.petName(), equalTo(lastUpdatedName)));
    }

    @Cuando("el usuario elimina la mascota registrada")
    public void elUsuarioEliminaLaMascotaRegistrada() {
        actor.attemptsTo(DeletePet.byStoredId());
        actor.should(seeThat(PetApiResponse.statusCode(), equalTo(200)));
    }

    @Entonces("la mascota ya no existe en el sistema")
    public void laMascotaYaNoExisteEnElSistema() {
        actor.attemptsTo(GetPet.byStoredId());
        actor.should(seeThat(PetApiResponse.statusCode(), equalTo(404)));
    }
}
