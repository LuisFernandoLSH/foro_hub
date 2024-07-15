package com.alura.lsh.foroHub.service;

import com.alura.lsh.foroHub.domain.curso.Curso;
import com.alura.lsh.foroHub.domain.curso.CursoRepository;
import com.alura.lsh.foroHub.domain.curso.DatosRegistroCurso;
import com.alura.lsh.foroHub.domain.curso.DatosRespuestaCurso;
import com.alura.lsh.foroHub.domain.curso.validacion.CursoValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<CursoValidacion> validaciones;

    public DatosRespuestaCurso crearCurso(DatosRegistroCurso datosRegistroCurso) {
        validaciones.forEach(v -> v.validar(datosRegistroCurso));
        Curso curso = new Curso(datosRegistroCurso);
        cursoRepository.save(curso);
        return new DatosRespuestaCurso(curso);
    }
}
