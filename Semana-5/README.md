# Spring Batch — Procesamiento de Calificaciones — Axel Daniel Bartolo Maya 

## ¿Qué es este proyecto?

Aplicación Spring Boot que implementa un pipeline de procesamiento por lotes (batch) para calificaciones de estudiantes. El proyecto fue construido paso a paso usando **GitHub Copilot** como asistente de generación de código, siguiendo una guía PDF de la academia. Cada prompt utilizado para generar el código está documentado 
en [`PROMPTS.md`](./PROMPTS.md).

## ¿Cómo funciona?

El job `procesarCalificacionesJob` se ejecuta al arrancar la aplicación y corre dos pasos en secuencia:

```
estudiantes.csv
      │
      ▼
  [Step 1] Lee CSV → Calcula promedio → Inserta en MySQL (estudiantes_procesados)
      │
      ▼
  [Step 2] Lee MySQL → Asigna estado (APROBADO/REPROBADO) → Guarda reporte en MongoDB
```

- **Step 1** — `FlatFileItemReader` lee el CSV, `EstudianteProcessor` calcula el promedio de las 3 notas, `JdbcBatchItemWriter` persiste en MySQL. Chunk size: 3.
- **Step 2** — `JdbcCursorItemReader` consulta los registros de MySQL, `ReporteEstudianteProcessor` asigna `APROBADO` si promedio ≥ 70 o `REPROBADO` si es menor, `MongoItemWriter` guarda el reporte en MongoDB. Chunk size: 3.

Además expone una **API REST** para consultar y gestionar los datos en ambas bases de datos.

## Temas que cubre

- Spring Batch 5 (JobBuilder, StepBuilder, ItemReader, ItemProcessor, ItemWriter)
- Spring Data JPA con MySQL
- Spring Data MongoDB
- Spring Web (REST controllers, ResponseEntity)
- Pruebas unitarias con JUnit 5
- Pruebas con Mockito (@Mock, @InjectMocks, @ExtendWith)
- GitHub Copilot: generación de código, `/fix`, `/explain`, `/doc`, diferencia de prompts, refactorización

---

## Requisitos

- Java 17
- Maven 3.x
- Docker

---

## 1. Levantar los contenedores de base de datos

### MySQL (`mysql-academia`) — puerto `3306`

```bash
docker run -d \
  --name mysql-academia \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=academia \
  -e MYSQL_USER=alumno \
  -e MYSQL_PASSWORD=alumno123 \
  -p 3306:3306 \
  mysql:8
```

### MongoDB (`mongodb-academia`) — puerto `27018`

```bash
docker run -d \
  --name mongodb-academia \
  -e MONGO_INITDB_ROOT_USERNAME=root \
  -e MONGO_INITDB_ROOT_PASSWORD=root123 \
  -e MONGO_INITDB_DATABASE=academia \
  -p 27018:27017 \
  mongo:6
```

---

## 2. Crear la tabla en MySQL

Conéctate al contenedor y crea la tabla (Spring Batch crea sus propias tablas de metadata automáticamente):

```bash
docker exec -it mysql-academia mysql -u alumno -palumno123 academia
```

```sql
CREATE TABLE IF NOT EXISTS estudiantes_procesados (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100),
    grupo     VARCHAR(10),
    nota1     DOUBLE,
    nota2     DOUBLE,
    nota3     DOUBLE,
    promedio  DOUBLE
);
```

---

## 3. Correr la aplicación

```bash
mvn spring-boot:run
```

Al arrancar, el job se ejecuta automáticamente procesando el archivo `src/main/resources/estudiantes.csv`. La app queda escuchando en el puerto **8080**.

---

## 4. Correr los tests

```bash
mvn test
```

---

## Endpoints REST

### Estudiantes — datos en MySQL

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/estudiantes` | Listar todos |
| GET | `/api/estudiantes/{id}` | Obtener por ID (200 / 404) |
| GET | `/api/estudiantes/aprobados/total` | Cantidad de aprobados |
| GET | `/api/estudiantes/reprobados/total` | Cantidad de reprobados |
| POST | `/api/estudiantes` | Crear estudiante (201) |
| PUT | `/api/estudiantes/{id}` | Reemplazar estudiante (200 / 404) |
| PATCH | `/api/estudiantes/{id}` | Cambiar grupo (200 / 404) |
| DELETE | `/api/estudiantes/{id}` | Eliminar (200 / 404) |

### Reportes — datos en MongoDB

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/reportes` | Listar todos los reportes |
| GET | `/api/reportes/estado/{estado}` | Filtrar por `APROBADO` o `REPROBADO` |

---

## Resumen de puertos

| Servicio | Puerto |
|----------|--------|
| Spring Boot (API) | 8080 |
| MySQL | 3306 |
| MongoDB | 27018 |

---
## Conclusión
Con este proyecto se aprendió a implementar IA (Copilot) para la generación de código, ademas de otras herramientas como para que
esta nos explique lo que hace, generar test, solucionar o reactorizar,
de modo que se comprende su uso e importancia para implementar
de forma cautelosa ya que también es cierto que a veces generó cosas que no tenían sentido, por lo que aprobar todo sin revisar puede causar
más problemas que beneficios, aunque con la guía dada y la verificación 
con el proyecto anterior se pudo entender mejor lo que debía hacer.
