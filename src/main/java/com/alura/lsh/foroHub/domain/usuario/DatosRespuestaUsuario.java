package com.alura.lsh.foroHub.domain.usuario;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String correo
) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }
}
