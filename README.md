# AUTO_API_PETSTORE_SCREENPLAY

Proyecto de automatizacion de pruebas REST para PetStore API usando Serenity BDD + Screenplay + Rest Assured + Cucumber.

## Prerrequisitos

- Java 17
- Conexion a internet

No requiere levantar ninguna aplicacion local. Los tests se ejecutan contra la API publica de PetStore.

## URL de la API

https://petstore.swagger.io/v2

## Ejecutar los tests

```powershell
.\gradlew clean test aggregate
```

## Ver el reporte

```
target/site/serenity/index.html
```

## Estructura del proyecto

```
AUTO_API_PETSTORE_SCREENPLAY/
├── build.gradle
├── serenity.conf
└── src/
    └── test/
        ├── java/
        │   └── com/automation/
        │       ├── tasks/
        │       │   ├── CreatePet.java
        │       │   ├── GetPet.java
        │       │   ├── UpdatePet.java
        │       │   └── DeletePet.java
        │       ├── questions/
        │       │   └── PetApiResponse.java
        │       ├── runner/
        │       │   └── CucumberTestRunner.java
        │       └── stepdefinitions/
        │           └── PetStoreStepDefinitions.java
        └── resources/
            └── features/
                └── petstore_crud.feature
```
