package com.Security.demo.Controller;

import com.Security.demo.dto.DadosLogin;
import com.Security.demo.model.UsuarioEntity;
import com.Security.demo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody DadosLogin dados){
        //Transformando dto para formato que Security compreende
        var tokenAutenticacao = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());


        //Compara authenticação
        var authentication = authenticationManager.authenticate(tokenAutenticacao);

        var usuarioSpring = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        //Gerando token JWT
        var tokenJWT = tokenService.gerarToken(usuarioSpring);
        return ResponseEntity.ok(tokenJWT);
    }
}
