package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import ulife.com.br.TCCEngenhariaComputacao.dto.login.NovaSenhaDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.TokenRedefinirSenha;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

@Service
public class LoginService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TokenRedefinirSenhaService tokenRedefinirSenhaService;

    public ModelAndView primeiroAcesso(@ModelAttribute NovaSenhaDTO novaSenhaDTO, Usuario usuario){
        ModelAndView mv;

            if (novaSenhaDTO.getNovaSenha().equals(novaSenhaDTO.getConfirmarSenha())){
                mv = new ModelAndView("redirect:/logout");
                mv.addObject("mensagem","Nova senha cadastrada");
                usuarioService.trocarSenhaUsuario(usuario,novaSenhaDTO.getNovaSenha());
            }else {
                mv = new ModelAndView("redirect:/primeiro-acesso");
                mv.addObject("novaSenhaDTO",novaSenhaDTO);
                mv.addObject("erro", "Senhas não coincidem");
            }

        return mv;
    }

    public ModelAndView esqueciSenha(String tokenString, NovaSenhaDTO novaSenhaDTO) {
        ModelAndView mv;
        TokenRedefinirSenha token = tokenRedefinirSenhaService.buscarTokenRedefinirSenha(tokenString);
        Usuario usuario = token.getUsuario();

        if (novaSenhaDTO.getNovaSenha().equals(novaSenhaDTO.getConfirmarSenha()) && tokenRedefinirSenhaService.tokenRedefinirSenhaExpirou(token)){
            mv = new ModelAndView("redirect:/login");
            mv.addObject("mensagem","Nova senha cadastrada");
            usuarioService.trocarSenhaUsuario(usuario,novaSenhaDTO.getNovaSenha());
        } else if (!tokenRedefinirSenhaService.tokenRedefinirSenhaExpirou(token)) {
            mv = new ModelAndView("redirect:/esqueci-a-senha/trocar-senha/"+tokenString);
            mv.addObject("novaSenhaDTO",novaSenhaDTO);
            mv.addObject("erro", "Token invállido");
        } else {
            mv = new ModelAndView("redirect:/esqueci-a-senha/trocar-senha/"+tokenString);
            mv.addObject("novaSenhaDTO",novaSenhaDTO);
            mv.addObject("erro", "Senhas não coincidem");
        }

        return mv;
    }
}
