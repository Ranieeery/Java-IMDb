package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.infra.Security.DadosTokenJWT;
import dev.raniery.med.voll.api.infra.Security.TokenService;
import dev.raniery.med.voll.api.user.DadosAutenticacao;
import dev.raniery.med.voll.api.user.User;
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
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DadosAutenticacao dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication auth = authManager.authenticate(authToken);

        String token = tokenService.gerarToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(token));
    }
}