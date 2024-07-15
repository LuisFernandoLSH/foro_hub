package com.alura.lsh.foroHub.controller;

import com.alura.lsh.foroHub.domain.usuario.DatosRegistroUsuario;
import com.alura.lsh.foroHub.domain.usuario.DatosRespuestaUsuario;
import com.alura.lsh.foroHub.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> crearUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                       UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Creando USUARIO");
        DatosRespuestaUsuario usuario = usuarioService.crearUsuario(datosRegistroUsuario);

        URI url = uriComponentsBuilder.path("/topicos/{id}").
                buildAndExpand(String.valueOf(usuario.id())).toUri();
        return ResponseEntity.created(url).body(usuario);
    }

}
