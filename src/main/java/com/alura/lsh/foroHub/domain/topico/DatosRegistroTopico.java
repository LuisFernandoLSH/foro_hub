package com.alura.lsh.foroHub.domain.topico;

import com.alura.lsh.foroHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long idAutor,
        @NotNull
        Long idCurso
) {
}
