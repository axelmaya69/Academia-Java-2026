# Sistema de Procesamiento de Estudiantes con Spring Batch - Axel Daniel Bartolo Maya

## Descripción

Este proyecto tiene como objetivo demostrar la integración de varias tecnologías del ecosistema de Spring para 
construir una aplicación capaz de procesar información de estudiantes mediante un proceso Batch ademas de almacenar los 
resultados en una base de datos y exponerlos mediante una arquitectura "MVC". Además, se incluyen pruebas unitarias 
utilizando JUnit y Mockito para validar el comportamiento de la lógica de negocio.

---

# Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Batch
* Spring MVC
* Spring Data JPA
* MySQL
* Mongo
* Lombok
* JUnit 5
* Mockito
* Maven

---

# Estructura del proyecto

```
src
├── config
├── controller
├── model
├── processor
├── repository
├── service
└── Clase Main
```

Cada paquete tiene una responsabilidad específica, siguiendo una arquitectura organizada y fácil de mantener.

---

# 1. Procesamiento Batch

La primera etapa del proyecto consiste en leer un archivo CSV con información de estudiantes para posteriormente 
procesarlo y almacenarlo en las bases de datos.

El flujo del procesamiento Batch se divide en tres componentes principales:

## Reader

Se encarga de leer cada registro del archivo CSV y convertirlo en un objeto Java.

## Processor

Recibe cada estudiante leído por el Reader y realiza el cálculo del promedio utilizando sus tres calificaciones.

## Writer

Finalmente, el Writer recibe los objetos procesados y los almacena en la base de datos mediante Spring Data JPA.


Spring Batch permite realizar este proceso de forma eficiente y desacoplada, facilitando el manejo de grandes 
cantidades de información.

---

# 2. Arquitectura MVC

Una vez almacenados los estudiantes procesados, el proyecto implementa una arquitectura MVC para exponer la información 
mediante servicios REST.

La estructura sigue la separación clásica de responsabilidades.

## Controller

Recibe las solicitudes HTTP realizadas por el cliente y delega la lógica al servicio correspondiente.

## Service

Contiene la lógica de negocio del sistema y coordina la comunicación entre el controlador y el repositorio.

## Repository

Se comunica directamente con la base de datos utilizando Spring Data JPA para realizar operaciones CRUD sobre 
la entidad de estudiantes.


# 3. Pruebas Unitarias

Para validar el correcto funcionamiento de la lógica de negocio se implementan pruebas unitarias utilizando JUnit 5 y Mockito.

Las pruebas se enfocan principalmente en la capa de servicio, simulando el comportamiento del repositorio 
mediante objetos Mock.

Durante las pruebas se verifican aspectos como:

* Obtención de estudiantes.
* Consulta por identificador.
* Guardado de registros.
* Comportamiento esperado ante distintos escenarios.


# Ejecución

1. Configurar la conexión a las bases de datos en `application.properties`.
2. Ejecutar la aplicación Spring Boot.
3. El proceso Batch leerá el archivo CSV y almacenará los registros.
4. Consumir los endpoints REST para consultar la información almacenada.
5. Ejecutar las pruebas unitarias con Maven o desde el IDE.

---
# Objetivo General

El objetivo fue desarrollar una aplicación utilizando el ecosistema de Spring que permita procesar información de estudiantes 
mediante un proceso Batch, almacenar los resultados en bases de datos y exponerlos a través de una arquitectura MVC, 
aplicando tambien varias pruebas unitarias para validar el correcto funcionamiento del programa y garantizar que funciones bien 
o de acuerdo a los estándares esperados.
