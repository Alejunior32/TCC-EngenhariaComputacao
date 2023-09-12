package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.TokenRedefinirSenha;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.TokenRedefinirSenhaRepository;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenRedefinirSenhaService {

    @Autowired
    private TokenRedefinirSenhaRepository tokenRedefinirSenhaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    public TokenRedefinirSenha buscarTokenRedefinirSenha(String token){
        return tokenRedefinirSenhaRepository.findByToken(token).orElseThrow( () -> new EntityNotFoundException("Token para redefinir senha não encontrado"));
    }

    public void salvaTokenRedefinirSenha(String email){
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(email);
        TokenRedefinirSenha token = tokenRedefinirSenhaRepository.save(new TokenRedefinirSenha(usuario));
        emailService.sendEmail(usuario.getLogin(), "Redefinição de Senha","Link para redefinir senha: http://localhost:8080/esqueci-a-senha/trocar-senha/"+token.getToken());
    }

    public boolean tokenRedefinirSenhaExpirou(TokenRedefinirSenha tokenRedefinirSenha){
        Date momentoAtual = new Date();
        return tokenRedefinirSenha.getDataExpiracao().before(momentoAtual);
    }
}
