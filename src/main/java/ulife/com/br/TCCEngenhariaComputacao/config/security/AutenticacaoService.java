package ulife.com.br.TCCEngenhariaComputacao.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.dto.DadosAutenticacao;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

@Service
public class AutenticacaoService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String autenticar(DadosAutenticacao dadosAutenticacao){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(),dadosAutenticacao.senha());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        return tokenService.gerarToken((Usuario) authenticate.getPrincipal());
    }
}
