package com.alura.lsh.foroHub.controller;

import com.alura.lsh.foroHub.domain.curso.DatosRegistroCurso;
import com.alura.lsh.foroHub.domain.curso.DatosRespuestaCurso;
import com.alura.lsh.foroHub.domain.usuario.DatosRespuestaUsuario;
import com.alura.lsh.foroHub.service.CursoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> crearCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                                          UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaCurso curso = cursoService.crearCurso(datosRegistroCurso);

        URI url = uriComponentsBuilder.path("/topicos/{id}").
                buildAndExpand(String.valueOf(curso.id())).toUri();
        return ResponseEntity.created(url).body(curso);
    }
}
