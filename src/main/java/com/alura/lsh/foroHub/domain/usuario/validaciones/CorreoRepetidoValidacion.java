package com.alura.lsh.foroHub.domain.usuario.validaciones;

import com.alura.lsh.foroHub.domain.usuario.DatosRegistroUsuario;
import com.alura.lsh.foroHub.domain.usuario.UsuarioRepository;
import com.alura.lsh.foroHub.infra.errors.ValidacionIntegradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorreoRepetidoValidacion implements UsuarioValidacion {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validar(DatosRegistroUsuario datosRegistroUsuario) {
        if(repository.buscarPorCorreo(datosRegistroUsuario.correoElectronico()) != null)
            throw new ValidacionIntegradaException("Ya existe un usuario con el correo: "+datosRegistroUsuario.correoElectronico());
    }
}
