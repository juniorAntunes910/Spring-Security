package com.Security.demo.security;


import com.Security.demo.model.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //Vai puxar o Secret
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(org.springframework.security.core.userdetails.User usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); //Algoritimo que assina nosso algoritomo

            return JWT.create()
                    .withIssuer("API_Seguranca_Demo") //Quem emite o token
                    .withSubject(usuario.getUsername()) //Dono do token
                    .withExpiresAt(dataExpiracao()) //Quando o token expira
                    .sign(algorithm); //Assina o token
        } catch (JWTCreationException jwtCreationException){
            throw  new RuntimeException("Erro ao Gerar o JWT " + jwtCreationException);
        }
    }

    //Metodo vale por 2 horas segundo esse metodo
    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
