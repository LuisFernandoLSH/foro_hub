package com.alura.lsh.foroHub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
    Boolean existsByNombre(String nombre);
}
