package com.alura.lsh.foroHub.infra.security;

import com.alura.lsh.foroHub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    public String claveToken;

    public String generarToken(Usuario usuario)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(claveToken);
            return  JWT.create()
                    .withIssuer("alura")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFecha())
                    .sign(algorithm);
        }catch(JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String tokenJWT)
    {
        try{
            Algorithm algoritmo = Algorithm.HMAC256(claveToken);
            return JWT.require(algoritmo)
                    .withIssuer("alura")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch(JWTVerificationException exception){
            throw new RuntimeException("Token inválido o expirado...");
        }
    }

    private Instant generarFecha ()
    {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-08:00"));
    }

}


