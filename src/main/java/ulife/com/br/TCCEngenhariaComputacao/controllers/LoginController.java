package ulife.com.br.TCCEngenhariaComputacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.dto.login.NovaSenhaDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.services.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/")
    public String layout(){
        return "layout";
    }

    @GetMapping("/primeiro-acesso")
    public ModelAndView loginPrimeiroAcesso(){
        ModelAndView mv = new ModelAndView("/login/primeiroAcesso");
        mv.addObject("novaSenhaDTO",new NovaSenhaDTO());
        return mv;
    }

    @PostMapping("/primeiro-acesso")
    public ModelAndView trocarSenha(@ModelAttribute NovaSenhaDTO novaSenhaDTO , RedirectAttributes redirectAttributes, Authentication authentication){

        Usuario usuario = (Usuario) authentication.getPrincipal();
        return loginService.primeiroAcesso(novaSenhaDTO,usuario,redirectAttributes);
    }
}
