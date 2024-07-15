package com.alura.lsh.foroHub.domain.topico.validaticiones;

import com.alura.lsh.foroHub.domain.topico.DatosActualizarTopico;
import com.alura.lsh.foroHub.domain.topico.DatosRegistroTopico;

public interface TopicoValidacion {
    void validar(DatosRegistroTopico topico);
    void validar(DatosActualizarTopico topico);
}
