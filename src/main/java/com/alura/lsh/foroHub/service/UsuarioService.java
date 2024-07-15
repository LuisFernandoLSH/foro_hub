package com.alura.lsh.foroHub.service;

import com.alura.lsh.foroHub.domain.usuario.*;
import com.alura.lsh.foroHub.domain.usuario.validaciones.UsuarioValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private List<UsuarioValidacion> validaciones;

    public DatosRespuestaUsuario crearUsuario(DatosRegistroUsuario datosRegistroUsuario){
        validaciones.forEach(v -> v.validar(datosRegistroUsuario));
        var pass = encoder.encode(datosRegistroUsuario.contrasena());
        Usuario usuario = new Usuario(datosRegistroUsuario,pass, Role.ROLE_USER);
        usuarioRepository.save(usuario);
        return new DatosRespuestaUsuario(usuario);
    }
}
