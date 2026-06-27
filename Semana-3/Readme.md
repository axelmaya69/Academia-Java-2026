# Programas Java Semana 3

## Descripción

Esta carpeta reúne seis programas desarrollados en Java para practicar conceptos avanzados del lenguaje, incluyendo 
colecciones, programación funcional, Streams API, concurrencia y estructuras de datos modernas. 
Cada ejercicio se enfoca en resolver un problema específico utilizando herramientas introducidas a partir de Java 8 
y versiones posteriores.

---

## 1.  Gestor de Contactos con Collections

Sistema de gestión de contactos que permite agregar, buscar y ordenar contactos.

### Conceptos utilizados

* `TreeSet`
* `Comparable`
* `Comparator`
* `equals()` y `hashCode()`
* Streams API
* `Optional`

### Funcionalidades

* Agregar contactos evitando duplicados.
* Búsqueda por correo electrónico.
* Búsqueda por prefijo de nombre.
* Ordenamiento por nombre o correo electrónico.

---

## 2. Caché Genérico con Expiración

Implementación de una caché genérica con expiración automática basada en tiempo.

### Conceptos utilizados

* Genéricos (`<K,V>`)
* `HashMap`
* Records
* `Optional`
* Expiración basada en tiempo
* Operaciones sobre colecciones

### Funcionalidades

* Almacenamiento temporal de datos.
* TTL configurable por elemento.
* Eliminación automática de entradas expiradas.
* Consulta segura mediante `Optional`.

---

## 3. Validador Composable con Lambdas

Pequeño framework de validación basado en programación funcional.

### Conceptos utilizados

* Interfaces funcionales
* Lambdas
* `Predicate`
* Métodos por defecto (`default`)
* Composición de funciones
* Records

### Funcionalidades

* Creación de validadores reutilizables.
* Combinación de validaciones mediante operadores lógicos.
* Acumulación de errores de validación.
* Validación de objetos complejos.

---

## 4. Análisis de Ventas con Streams

Herramienta para analizar ventas utilizando Streams.

### Conceptos utilizados

* Streams API
* Collectors
* Agrupaciones (`groupingBy`)
* Operaciones estadísticas
* Comparadores
* Records

### Funcionalidades

* Cálculo de ingresos totales.
* Ingresos por categoría y región.
* Promedios por categoría.
* Productos más vendidos.
* Conteo de ventas por mes.

---

## 5. Pipeline de Procesamiento de Texto

Analizador de texto para obtener estadísticas y patrones de palabras.

### Conceptos utilizados

* Streams
* `flatMap`
* Expresiones regulares
* Colecciones (`Set`, `Map`, `List`)
* Agrupaciones y conteos

### Funcionalidades

* Conteo de palabras.
* Detección de palabras únicas.
* Frecuencia de palabras.
* Longitud promedio.
* Agrupación por letra inicial.

---

## 6. Web Scraper Concurrente con CompletableFuture

Simulación de un scraper web concurrente utilizando programación asíncrona.

### Conceptos utilizados

* Concurrencia
* `ExecutorService`
* `CompletableFuture`
* Programación asíncrona
* Timeouts
* Streams API

### Funcionalidades

* Descarga paralela de páginas.
* Manejo de múltiples hilos.
* Timeouts configurables.
* Reportes de rendimiento.
* Estadísticas de tiempos de respuesta.
 
---
