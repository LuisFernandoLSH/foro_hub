package com.alura.lsh.foroHub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    @Query("SELECT t FROM Topico t WHERE t.titulo ILIKE %:titulo% AND t.mensaje ILIKE %:mensaje%")
    Optional<Topico> buscarPorTituloYMensaje(String titulo, String mensaje);
}
