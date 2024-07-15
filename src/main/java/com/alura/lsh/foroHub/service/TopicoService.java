package com.alura.lsh.foroHub.service;

import com.alura.lsh.foroHub.domain.curso.Curso;
import com.alura.lsh.foroHub.domain.curso.CursoRepository;
import com.alura.lsh.foroHub.domain.topico.*;
import com.alura.lsh.foroHub.domain.topico.validaticiones.TopicoValidacion;
import com.alura.lsh.foroHub.domain.usuario.Usuario;
import com.alura.lsh.foroHub.domain.usuario.UsuarioRepository;
import com.alura.lsh.foroHub.infra.errors.EntidadNoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<TopicoValidacion> validations;


    public DatosRespuestaTopico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        Optional<Usuario> usuario = usuarioRepository.findById(datosRegistroTopico.idAutor());
        Optional<Curso> curso = cursoRepository.findById(datosRegistroTopico.idCurso());

        if(usuario.isEmpty())
            throw new EntidadNoEncontradaException("Usuario no encontrado.\nNo se pudo crear el topico.");
        if(curso.isEmpty())
            throw new EntidadNoEncontradaException("Curso no encontrado.\nNo se pudo crear el topico.");

        validations.forEach(v -> v.validar(datosRegistroTopico));

        LocalDateTime time = LocalDateTime.now();
        Topico topico = new Topico(datosRegistroTopico,time, usuario.get(), curso.get());
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    public Page<DatosRespuestaTopico> obtenerTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new);
    }

    public DatosRespuestaTopico buscarTopico(Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isEmpty())
            throw new EntidadNoEncontradaException("No se encontro un topico con el id: "+id);
        return new DatosRespuestaTopico(topico.get());
    }

    public DatosRespuestaTopico actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        Optional<Topico> topico = topicoRepository.findById(datosActualizarTopico.id());
        if(topico.isEmpty())
            throw new EntidadNoEncontradaException("No se encontro un topico con el id: "+datosActualizarTopico.id());

        Optional<Usuario> usuario = usuarioRepository.findById(datosActualizarTopico.idAutor());
        Optional<Curso> curso = cursoRepository.findById(datosActualizarTopico.idCurso());

        if(usuario.isEmpty())
            throw new EntidadNoEncontradaException("Usuario no encontrado.\nNo se pudo crear el topico.");
        if(curso.isEmpty())
            throw new EntidadNoEncontradaException("Curso no encontrado.\nNo se pudo crear el topico.");

        validations.forEach(v -> v.validar(datosActualizarTopico));

        topico.get().actualizarDatos(datosActualizarTopico, usuario.get(), curso.get());

        return new DatosRespuestaTopico(topico.get());
    }

    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
