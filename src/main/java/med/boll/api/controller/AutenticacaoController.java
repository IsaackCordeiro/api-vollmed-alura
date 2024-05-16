package med.boll.api.controller;

import jakarta.validation.Valid;
import med.boll.api.dto.usuario.DadosAutenticacao;
import med.boll.api.infra.security.DadosTokenJWT;
import med.boll.api.model.Usuario;
import med.boll.api.service.TokenService;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody  @Valid DadosAutenticacao dados){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authenticate = authenticationManager.authenticate(authToken);

        String tokenJWT = tokenService.gerarToken((Usuario) authenticate.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
