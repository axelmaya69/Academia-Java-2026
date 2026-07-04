package com.academia.batch.service;

import com.academia.batch.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para operaciones relacionadas con estudiantes.
 */
@Service
public class EstudianteService {

    /**
     * Repositorio de acceso a datos de estudiantes.
     */
    private final EstudianteRepository estudianteRepository;

    /**
     * Crea el servicio con su dependencia de persistencia.
     *
     * @param estudianteRepository repositorio de estudiantes
     */
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


    /**
     * Cuenta la cantidad de estudiantes con promedio aprobatorio.
     *
     * @return total de estudiantes con promedio mayor o igual a 70
     */
    public long contarAprobados() {
        final double promedioMinimoAprobatorio = 70;
        return estudianteRepository.findAll().stream()
                .filter(estudiante -> estudiante.getPromedio() >= promedioMinimoAprobatorio)
                .count();
    }

    /**
     * Cuenta la cantidad de estudiantes con promedio reprobatorio.
     *
     * @return total de estudiantes con promedio menor a 70
     */
    public long contarReprobados() {
        return estudianteRepository.findAll().stream()
                .filter(estudiante -> estudiante.getPromedio() < 70)
                .count();
    }
}
