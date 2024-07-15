package com.alura.lsh.foroHub.domain.topico;

import com.alura.lsh.foroHub.domain.curso.DatosRespuestaCurso;
import com.alura.lsh.foroHub.domain.usuario.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        DatosRespuestaCurso curso,
        DatosRespuestaUsuario autor,
        Boolean status
) {

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), new DatosRespuestaCurso(topico.getCurso()),
                new DatosRespuestaUsuario(topico.getAutor()), topico.getStatus());
    }
}
