package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.dto.login.NovaSenhaDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

@Service
public class LoginService {

    @Autowired
    UsuarioService usuarioService;

    public ModelAndView primeiroAcesso(@ModelAttribute NovaSenhaDTO novaSenhaDTO, Usuario usuario, RedirectAttributes redirectAttributes){
        ModelAndView mv;

            if (novaSenhaDTO.getNovaSenha().equals(novaSenhaDTO.getConfirmarSenha())){
                mv = new ModelAndView("redirect:/logout");
                redirectAttributes.addFlashAttribute("message","Nova senha cadastrada");
                usuarioService.trocarSenhaPrimeiroAcesso(usuario,novaSenhaDTO.getNovaSenha());
            }else {
                mv = new ModelAndView("redirect:/primeiro-acesso");
                mv.addObject("novaSenhaDTO",novaSenhaDTO);
                redirectAttributes.addFlashAttribute("erroSenha", "Senhas não coincidem");
            }

        return mv;
    }
}
