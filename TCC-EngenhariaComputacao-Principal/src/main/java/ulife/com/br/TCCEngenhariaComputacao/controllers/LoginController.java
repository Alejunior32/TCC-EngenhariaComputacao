package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.dto.login.EmailDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.login.NovaSenhaDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.services.LoginService;
import ulife.com.br.TCCEngenhariaComputacao.services.TokenRedefinirSenhaService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    TokenRedefinirSenhaService tokenRedefinirSenhaService;

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/")
    public String layout(){
        return "home";
    }

    @GetMapping("/primeiro-acesso")
    public ModelAndView loginPrimeiroAcesso(){
        ModelAndView mv = new ModelAndView("/login/primeiro-acesso");
        mv.addObject("novaSenhaDTO",new NovaSenhaDTO());
        return mv;
    }

    @PostMapping("/primeiro-acesso")
    public ModelAndView trocarSenha(@ModelAttribute NovaSenhaDTO novaSenhaDTO , RedirectAttributes redirectAttributes, Authentication authentication){

        Usuario usuario = (Usuario) authentication.getPrincipal();
        return loginService.primeiroAcesso(novaSenhaDTO,usuario);
    }

    @GetMapping("/esqueci-a-senha")
    public ModelAndView esqueciASenha(){
        return new ModelAndView("/login/esqueciSenha");
    }

    @PostMapping("/esqueci-a-senha")
    public ModelAndView emailEsqueciASenha(@RequestParam("email") String email, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("/login/esqueciSenha");
        tokenRedefinirSenhaService.salvaTokenRedefinirSenha(email);
        mv.addObject("mensagem", "email enviado");
        return mv;
    }


    @GetMapping("/esqueci-a-senha/trocar-senha/{tokenString}")
    public ModelAndView esqueciASenhaLink(@PathVariable String tokenString){
        ModelAndView mv = new ModelAndView("/login/trocarSenha");
        mv.addObject("novaSenhaDTO",new NovaSenhaDTO());
        mv.addObject("token", tokenString);
        return mv;
    }

    @PostMapping("/esqueci-a-senha/trocar-senha/{tokenString}")
    public ModelAndView esqueciSenhaRedefinirSenha(@PathVariable String tokenString, NovaSenhaDTO novaSenhaDTO){
        ModelAndView mv = new ModelAndView("redirect:/login");
        return loginService.esqueciSenha(tokenString,novaSenhaDTO);
    }


}
