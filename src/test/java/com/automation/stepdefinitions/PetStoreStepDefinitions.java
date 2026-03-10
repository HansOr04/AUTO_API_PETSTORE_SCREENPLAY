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
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreStepDefinitions {

    private Actor actor;

    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        actor = OnStage.theActorCalled("tester");
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
        actor.attemptsTo(UpdatePet.withNewName(nuevoNombre));
        actor.should(seeThat(PetApiResponse.statusCode(), equalTo(200)));
    }

    @Entonces("la mascota consultada refleja el nombre actualizado")
    public void laMascotaConsultadaReflejaElNombreActualizado() {
        actor.attemptsTo(GetPet.byStoredId());
        actor.should(seeThat(PetApiResponse.petName(), equalTo("Firulais Updated")));
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
