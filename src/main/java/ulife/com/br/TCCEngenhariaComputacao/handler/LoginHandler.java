package ulife.com.br.TCCEngenhariaComputacao.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.services.PacienteService;

import java.io.IOException;

@Component
public class LoginHandler implements AuthenticationSuccessHandler {

    @Autowired
    PacienteService pacienteService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, IOException {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        if (usuario.getPrimeiroAcesso() && pacienteService.buscarUsuario(usuario.getId()).isEmpty()) {
            response.sendRedirect("/primeiro-acesso");
        } else {
            response.sendRedirect("/");
        }
    }
}
