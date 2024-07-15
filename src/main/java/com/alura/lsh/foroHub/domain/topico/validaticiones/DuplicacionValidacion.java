package com.alura.lsh.foroHub.domain.topico.validaticiones;

import com.alura.lsh.foroHub.domain.topico.DatosActualizarTopico;
import com.alura.lsh.foroHub.domain.topico.DatosRegistroTopico;
import com.alura.lsh.foroHub.domain.topico.TopicoRepository;
import com.alura.lsh.foroHub.infra.errors.ValidacionIntegradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicacionValidacion implements TopicoValidacion {
    @Autowired
    private TopicoRepository repository;

    @Override
    public void validar(DatosRegistroTopico topico) {
        if(repository.buscarPorTituloYMensaje(topico.titulo(),topico.mensaje()).isPresent())
            throw new ValidacionIntegradaException("Ya existe un topico con el mismo titulo y mensaje");
    }

    @Override
    public void validar(DatosActualizarTopico datosActualizarTopico) {
        var topico = repository.findById(datosActualizarTopico.id());

        if(!topico.get().getTitulo().equalsIgnoreCase(datosActualizarTopico.titulo()) ||
                !topico.get().getMensaje().equalsIgnoreCase(datosActualizarTopico.mensaje())){
            if(repository.buscarPorTituloYMensaje(datosActualizarTopico.titulo(),
                    datosActualizarTopico.mensaje()).isPresent())
                throw new ValidacionIntegradaException("Ya existe un topico con el mismo titulo y mensaje");
        }


    }
}
