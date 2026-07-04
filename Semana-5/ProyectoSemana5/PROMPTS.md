# Evidencia de uso de GitHub Copilot — Axel Daniel Bartolo Maya

#1 Actualizacion de archivo pom.xml para proyecto Spring Boot 3.2.2 con Java 17 y dependencias especificadas

Genera un pom.xml para un proyecto Spring Boot 3.2.2 con Java 17 y estas dependencias:
spring-boot-starter-batch
mysql-connector-j (scope runtime)
spring-boot-starter-data-mongodb
springboot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-test (scope test)

groupId  com.academia
artifactId spring-batch-final-calificaciones
versión 1.0.0. 
Incluye el spring-boot-mavenplugin.


No corregí nada, copilot generó el siguiente el pom.xml de forma correcta, ademas descargó las dependencias necesarias y configuró el proyecto para que funcione con Spring Boot 3.2.2 y Java 17. Aquí está el contenido del archivo pom.xml generado:

# 2 Creacion de la clase Estudiante
En el paquete com.academia.batch.model, crea una clase llamada Estudiante, crea los campos: nombre (String) grupo(String) nota1,nota2,nota3 y promedio (como double)
crea un constructor vacio, ademas de sus respectivos getters y setters de todos los campos, por ultimo un toString que muestre
nombre, grupo y promedio.

No se corrigió nada, copilot generó la clase Estudiante de forma correcta.

# 3 Creación de la clase EstudianteProcessor

En el paquete com.academia.batch.processor, crea una clase llamada EstudianteProcessor que implemente la 
interfaz ItemProcessor<Estudiante, Estudiante>. En el método process, calcula el promedio de las notas 
(nota1, nota2 y nota3) y lo asigna al campo promedio del objeto Estudiante con setPromedio, registrando un log con 
SLF4j: "Step 1 - Procesando: {estudiante}" y devuelve el estudiante

Únicamente se corrigió el nombre del archivo .md ya que se colocó en mayúsculas y copilot no lo supo encontrar, 
el resto del contenido fue generado correctamente por copilot.

# 4 Creación de la clase EstudianteReporte

Dentro del paquete com.academia.batch.model crea una clase llamada EstudianteReporte, la cual va a ser un Documento de
MongoDB (@Document collection = "reportes_estudiantes") con los campos: id(String, con su @Id), nombre, grupo, promedio(double) 
y estado(String). Crea un constructor vacío, además de sus respectivos getters y setters de todos los campos, por último un toString que muestre nombre, grupo y promedio.

No se corrigió nada, copilot generó la clase EstudianteReporte de forma correcta.

# 5 Creacion de la clase ReporteEstudianteProcessor 

En el paquete com.academia.batch.processor, crea una clase llamada ReporteEstudianteProcessor que implemente 
la interfaz ItemProcessor<Estudiante, EstudianteReporte>, donde se convierte un Estudiante en un EstudianteReporte
copiando el nombre, grupo y promedio, ademas se le asigna al estado: "APROBADO" si: promedio >=70, o "REPROBADO" si es menor.
Loggea "Step 2 - Reporte: {reporte}" y devuelve el reporte.

No se corrigió nada, copilot generó la clase ReporteEstudianteProcessor de forma correcta. El umbral de aprobación se 
estableció en mayor o igual a 70, y se implementó la lógica correcta para asignar el estado correspondiente.

# 6 Generando el BatchConfig

En el paquete com.academia.batch.config, crea una clase llamada BatchConfig con @Configuration de Spring Batch(Springboot 3.2)
esta va a contener 2 steps, donde: 
Step 1: Implementa un FlatItemReader para leer el archivo "estudiantes.csv" del classpath (Si no está, crealo, 
delimitado, columnas: nombre, grupo, nota1, nota2, nota3, salta 1 linea, targetType Estudiante) procesalo con EstudianteProcessor
y escribe en MySQL con un JdbcBatchItemWriter (INSERT en estudiantes_procesados con nombre, grupo, nota1, nota2,nota3,promedio,beanMapped).

Step2: Implementa un JdbcCursorItemReader que hace un SELECT nombre, grupo, promedio de estudiantes_procesados, procesalo
con ReporteEstudianteProcessor y escribe en MongoDB con MongoItemWriter en la coleccion "reportes_estudiantes".

Ambos steps con un chunk de 3

Un Job llamado "procesarCalificacionesJob" con RunIdIncrementer y que ejecute los 2 steps en orden (paso 1, paso 2). 
Usa la API de Spring Batch 5 (JobBuilder y StepBuilder con JobRepository).

Se corrigió la clase eliminando la notacion @EnableBatchProcessing ya que impedia que la aplicacion funcionara
correctamente al no enviar los datos del csv a MySQL, una vez eliminado funciono correctamente.

# 7 Creando la clase con método principal
Dentro del paquete com.academia.batch crea la clase principal llamada SpringBatchApplication con el método main 
que arranca la aplicación con SpringApplication.run.

No se corrigio nada, copilot generó la clase SpringBatchApplication de forma correcta agregando la notacion @SpringBootApplication 
de forma implicita.

# 8 Creando el archivo de propiedades application.properties
En la carpeta resources del proyecto, genera un application.properties para Spring Boot que se conecte a MySQL 
en jdbc:mysql://localhost:3306/academia (usuario alumno, password alumno123), inicialice el esquema de Spring
Batch siempre, que ejecute el Job al arrancar, y se conecte a MongoDB en mongodb://root:root123@localhost:27018/academia?authSource=admin.

Posteriormente se le debe a indicar al programador que debe llenar los datos de estudiantes.csv, configurar el puerto
en caso de que no funcione, y crear la tabla estudiantes_procesados en la base de datos MySQL.

-Copilot generó correctamente el archivo application.properties, y al compilar el programa funciona correctamente mandando
los datos a las bases de datos.

# 9 Agregando capa web
En la carpeta com.academia.batch.repository crea una nueva Entidad JPA llamada EstudianteEntity (con @Entity, 
@Table name="estudiantes_procesados") que mapea la tabla existente. id Long con @Id y @GeneratedValue(strategy = GenerationType.IDENTITY);
campos: nombre, grupo, nota1, nota2, nota3, promedio, ademas de sus getters y setters
Posteriormente en el mismo paquete crea dos interfaces tal y como se te pide a continuacion, no agregues otras querys u notaciones extra:

La primera interfaz se llamará EstudianteRepository que extiende de JpaRepository<EstudianteEntity, Long> con un metodo
findByGrupo(String grupo) que devuelve List<EstudianteEntity>

La segunda interfaz se llamará ReporteRepository que extiende de MongoRepository<EstudianteReporte, String>, con un metodo
findByEstado(String estado) que devuelve List<EstudianteReporte>.

- Copilot genero bien la clase EstudianteEntity, pero en la Interfaz EstudianteRepository añadio codigo extra, por lo cual
- se rechazo su sugerencia y se le volvio a pedir que lo implementara tal y como estaba establecido. Al generarlo nuevamente
- copilot lo hizo correctamente, generando la interfaz sin codigo extra.

# 10 Creando el servicio
En la carpeta com.academia.batch.service crea una clase llamada EstudianteService, la cual tenga una notacion @Service con
inyeccion por constructor de EstudianteRepository. Incluye tambien un metodo llamado contarAprobados() que devuelve cuantos
estudiantes tienen promedio >= 70 implementando findAll() y un stream con filter y count.

Posteriormente en el archivo application.properties agrega (Únicamente si no están ya agregados):
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

- Copilot genero correctamente la clase EstudianteService y el metodo contarAprobados() implementando la logica
- solicitada, no fue necesario corregir o rechazar.

# 11 Creando los controladores
En la carpeta com.academia.batch.controller crea una clase llamada EstudianteController, la cual va a ser una @RestController
en /api/estudiantes que implemente la interfaz EstudianteRepository y a EstudianteService (inyeccion por constructor). con:
GET /(listar todos), GET /{id}(200 o 404), GET /aprobados/total (devuelve un Map con el conteo del servicio), POST / (crea, 201 Created),
PUT /{id} (reemplaza, 200 o 404), PATCH /{id} (cambia solo el grupo desde un Map, 200 o 404), DELETE /{id} (200 0 404).
Implementa ResponseEntity para manejar los codigos de respuesta. 

En el mismo paquete crea otra clase llamada ReporteController la cual tambien va a ser una @RestController en /api/reportes
que implemente la interfaz ReporteRepository con GET /(listar todos los reportes), GET /estado/{estado} (devuelve los que
tengan ese estado convertido a mayusculas) usando findByEstado.


- Copilot genero correctamente ambas clases EstudianteController y ReporteController, implementando los endpoints
- solicitados y manejando los codigos de respuesta con ResponseEntity. No fue necesario realizar correcciones.

# 12 Creacion de tests con JUnit 5
Genera pruebas unitarias con JUnit 5 para EstudianteProcessor y ReporteEstudianteProcessor: que verifiquen que el 
promedio se calcula bien, y que el estado es APROBADO con promedio 70 y REPROBADO con 69.9.
Guarda el test en src/test/java/com/academia/batch/processor/ProcessorTest.java 

-Copilot genero correctamente las pruebas unitarias para ambas clases, verificando el calculo del promedio y la asignacion
- del estado de manera correcta. No fue necesario realizar correcciones.

# 13 Creación de tests con Mockito
Ve la clase EstudianteService y a partir de esta, genera una prueba unitaria de EstudianteService con Mockito: 
mockea EstudianteRepository con @Mock, inyecta el servicio con @InjectMocks, usa 
@ExtendWith(MockitoExtension.class), simula findAll() devolviendo 2 estudiantes aprobados y 1 reprobado, y 
verifica que contarAprobados() devuelve 2. Por ultimo,
guarda el test en src/test/java/com/academia/batch/service/EstudianteServiceTest.java y corre mvn test para verificar 
que pasa correctamente.

-- Copilot genero correctamente la prueba unitaria para EstudianteService con Mockito, simulando el comportamiento
-- de findAll() por lo que no se realizaron correcciones. Al ejecutar mvn test, todas las pruebas pasaron correctamente.

# 14 Documentar con /doc
Copilot genero la documentacion a medias, por lo que solo se dejo lo más importante y se borro lo que no era relevante o
que no tenia sentido.

# 15 Explicar con /explain
Copilot explicó correctamente como funcionaba el metodo para  obtener un alumno por id, implementando que pasa cunado
si existe y cuando no existe. devolviendo 200 y 404 respectivamente. Aunque su explicación fue algo corta, si responde
a la pregunta.


# 16 Solucionar con /fix
Al cambiar la division de /3 por /2 y correr el test este no pasó, entonces al ir al metodo, seleccionarlo y colcar
ese comando copilot entro al metodo, lo evaluo y sí se dió cuenta de que iba en realidad un /3, ademas explica qué falló
y por qué, de modo que al hacer el test, este funcionó correctamente, de modo que no fueron necesarias correcciones adicionales.

# 17 Diferencia de prompts

Haz un metodo de reprobados: Copilot generó correctamente el metodo

En EstudianteService, genera un método contarReprobados() que devuelva un long con la cantidad de
estudiantes cuyo promedio sea menor a 70, usando findAll() y Stream API (filter + count), igual en estilo que
contarAprobados(): Copilot unicamente mostro que lo iba a verificar y posteriormente mostró que eso ya estaba implementado
se sospecha que fue porque se basó mucho en el primer metodo de contarAprobados() solo que invirtió la condicion.

# 18 Refactorización de metodo contarAprobados
Copilot unicamente creo una constante que tiene el valor para aprobar (promedioMinimoAprobatorio = 70), luego lo
colocó en el condicional, y al ejecutar el test este pasó sin problemas, por lo que se puede decir que funcionó 
pero no tan exitosamente ya que no se refactorizo en gran medida o con un resultado más diferente. 



