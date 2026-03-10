# language: es
Característica: Gestión de mascotas en PetStore API
  Como consumidor de la API PetStore
  Quiero gestionar mascotas mediante operaciones CRUD
  Para validar la integridad de los servicios REST

  Escenario: Ciclo completo CRUD de una mascota
    Dado el sistema tiene disponible la API de PetStore
    Cuando el usuario crea una nueva mascota con nombre "Firulais" y estado "available"
    Entonces la mascota creada puede ser consultada por su identificador
    Cuando el usuario actualiza el nombre de la mascota a "Firulais Updated"
    Entonces la mascota consultada refleja el nombre actualizado
    Cuando el usuario elimina la mascota registrada
    Entonces la mascota ya no existe en el sistema
