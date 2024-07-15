package com.alura.lsh.foroHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long idAutor,
        @NotNull
        Long idCurso
) {
        public DatosActualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
                this(id, datosActualizarTopico.titulo(), datosActualizarTopico.mensaje(),
                        datosActualizarTopico.idAutor,datosActualizarTopico.idCurso());
        }
}
