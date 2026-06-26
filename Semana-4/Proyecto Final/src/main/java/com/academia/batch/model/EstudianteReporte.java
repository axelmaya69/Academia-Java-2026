package com.academia.batch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reportes_estudiantes")
@Setter
@Getter
@ToString
public class EstudianteReporte {
    @Id
    private String id;
    private String nombre;
    private String grupo;
    private double promedio;
    private String estado;

    public EstudianteReporte() {
    }
}
