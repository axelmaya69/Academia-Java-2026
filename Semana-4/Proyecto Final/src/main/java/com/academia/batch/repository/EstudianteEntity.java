package com.academia.batch.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "estudiantes_procesados")   // mapea a la tabla existente
@Entity
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // id autoincremental de MySQL
    private Long id;

    private String nombre;
    private String grupo;
    private double nota1;
    private double nota2;
    private double nota3;
    private double promedio;

    public EstudianteEntity() {
    }
}
