package com.alura.lsh.foroHub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByCorreoElectronico(String username);

    @Query("SELECT u FROM Usuario u WHERE u.correoElectronico ILIKE %:correoElectronico%")
    Usuario buscarPorCorreo(String correoElectronico);
}
