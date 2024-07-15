package com.alura.lsh.foroHub.controller;

import com.alura.lsh.foroHub.domain.topico.DatosActualizarTopico;
import com.alura.lsh.foroHub.domain.topico.DatosRegistroTopico;
import com.alura.lsh.foroHub.domain.topico.DatosRespuestaTopico;
import com.alura.lsh.foroHub.domain.topico.Topico;
import com.alura.lsh.foroHub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo topico en la base de datos")
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                      UriComponentsBuilder uriComponentsBuilder){

        DatosRespuestaTopico topico = topicoService.crearTopico(datosRegistroTopico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").
                buildAndExpand(String.valueOf(topico.id())).toUri();
        return ResponseEntity.created(url).body(topico);
    }

    @GetMapping
    @Operation(summary = "Muestra una lista de todos los topicos registrados")
    public ResponseEntity<Page<DatosRespuestaTopico>> mostrarTopicos(Pageable paginacion){
        return ResponseEntity.ok(topicoService.obtenerTopicos(paginacion));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Muestra un tipico existente")
    public ResponseEntity<DatosRespuestaTopico> buscarTopico(@PathVariable Long id){
        DatosRespuestaTopico topico = topicoService.buscarTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza un tipico existente")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id,
                                     @RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        var actualizarTopico = new DatosActualizarTopico(id,datosActualizarTopico);
        DatosRespuestaTopico topico = topicoService.actualizarTopico(actualizarTopico);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
