package com.alura.lsh.foroHub.controller;

import com.alura.lsh.foroHub.domain.usuario.LoginDTO;
import com.alura.lsh.foroHub.domain.usuario.Usuario;
import com.alura.lsh.foroHub.infra.security.DatosJWTToken;
import com.alura.lsh.foroHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid LoginDTO loginDTO){
        Authentication authToken = new UsernamePasswordAuthenticationToken(loginDTO.correo(),
                loginDTO.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
