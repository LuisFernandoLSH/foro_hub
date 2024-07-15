package com.alura.lsh.foroHub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        String correo,
        @NotBlank
        String contrasena
) {
}
