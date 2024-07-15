package com.alura.lsh.foroHub.domain.curso.validacion;

import com.alura.lsh.foroHub.domain.curso.CursoRepository;
import com.alura.lsh.foroHub.domain.curso.DatosRegistroCurso;
import com.alura.lsh.foroHub.infra.errors.ValidacionIntegradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NombreRepetidoValidacion implements CursoValidacion {

    @Autowired
    private CursoRepository repository;

    @Override
    public void validar(DatosRegistroCurso datosRegistroCurso) {
        if(repository.existsByNombre(datosRegistroCurso.nombre()))
            throw new ValidacionIntegradaException("Ya existe un curso con el nombre: "+datosRegistroCurso.nombre());
    }
}
