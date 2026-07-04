package com.academia.batch.processor;

import com.academia.batch.model.Estudiante;
import com.academia.batch.model.EstudianteReporte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProcessorTest {

    @Test
    void debeCalcularElPromedioCorrectamente() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Ana");
        estudiante.setGrupo("A1");
        estudiante.setNota1(80);
        estudiante.setNota2(70);
        estudiante.setNota3(60);

        EstudianteProcessor processor = new EstudianteProcessor();

        Estudiante procesado = processor.process(estudiante);

        assertNotNull(procesado);
        assertEquals(70.0, procesado.getPromedio(), 0.0001);
    }

    @Test
    void debeMarcarComoAprobadoConPromedioSetenta() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Ana");
        estudiante.setGrupo("A1");
        estudiante.setPromedio(70.0);

        ReporteEstudianteProcessor processor = new ReporteEstudianteProcessor();

        EstudianteReporte reporte = processor.process(estudiante);

        assertNotNull(reporte);
        assertEquals("APROBADO", reporte.getEstado());
        assertEquals("Ana", reporte.getNombre());
        assertEquals("A1", reporte.getGrupo());
        assertEquals(70.0, reporte.getPromedio(), 0.0001);
    }

    @Test
    void debeMarcarComoReprobadoConPromedioSesentaYNuevePuntoNueve() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Luis");
        estudiante.setGrupo("B1");
        estudiante.setPromedio(69.9);

        ReporteEstudianteProcessor processor = new ReporteEstudianteProcessor();

        EstudianteReporte reporte = processor.process(estudiante);

        assertNotNull(reporte);
        assertEquals("REPROBADO", reporte.getEstado());
    }
}
