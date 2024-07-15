package com.alura.lsh.foroHub.domain.curso;

public record DatosRespuestaCurso(
        Long id,
        String nombre,
        Categoria categoria
) {
    public DatosRespuestaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
