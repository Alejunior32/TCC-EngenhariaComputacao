package ulife.com.br.TCCEngenhariaComputacao.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        System.out.println(new BCryptPasswordEncoder().encode("12345"));
        return "login/login";
    }

}
