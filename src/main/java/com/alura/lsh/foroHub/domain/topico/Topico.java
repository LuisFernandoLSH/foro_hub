package com.alura.lsh.foroHub.domain.topico;

import com.alura.lsh.foroHub.domain.respuesta.Respuesta;
import com.alura.lsh.foroHub.domain.usuario.Usuario;
import com.alura.lsh.foroHub.domain.curso.Curso;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Topico(DatosRegistroTopico datosRegistroTopico, LocalDateTime time, Usuario usuario, Curso curso) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = time;
        this.status = true;
        this.autor = usuario;
        this.curso = curso;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico, Usuario usuario, Curso curso) {
        this.titulo = datosActualizarTopico.titulo();
        this.mensaje = datosActualizarTopico.mensaje();
        this.autor = usuario;
        this.curso = curso;
    }
}
