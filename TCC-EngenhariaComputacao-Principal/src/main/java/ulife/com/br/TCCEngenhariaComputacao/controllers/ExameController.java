package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;
import ulife.com.br.TCCEngenhariaComputacao.models.Exame;
import ulife.com.br.TCCEngenhariaComputacao.services.EspecialidadeService;
import ulife.com.br.TCCEngenhariaComputacao.services.ExameService;

@Controller
@RequestMapping("/exame")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @GetMapping
    public ModelAndView listarExames(@RequestParam(name = "palavra", required = false) String palavra) {
        ModelAndView mv = new ModelAndView("exame/lista.html");
        mv.addObject("exames",exameService.listar(palavra));
        return mv;
    }

    @GetMapping("/detalhes")
    public ModelAndView detalhesExame(@RequestParam Long idExame){
        ModelAndView mv = new ModelAndView("exame/detalhes.html");
        try{
            mv.addObject("exame",exameService.buscarPorId(idExame));
        }catch (EntityNotFoundException exception){
            mv.addObject("erroBuscar",exception.getMessage());
        }
        return mv;
    }

    @GetMapping("cadastrar")
    private ModelAndView formularioExame(){
        ModelAndView mv = new ModelAndView("exame/form.html");
        mv.addObject("exame",new Exame());
        return mv;
    }

    @PostMapping("cadastrar")
    private ModelAndView cadastrarExame(@Valid Exame exame, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/exame");
        redirectAttributes.addFlashAttribute("mensagem","novo exame cadastrada com sucesso!");
        exameService.salvar(exame);
        return  mv;
    }

    @RequestMapping("excluir")
    public ModelAndView excluirExame(@RequestParam Long idExame){
        ModelAndView mv = new ModelAndView("redirect:/exame");
        try{
            exameService.excluirPorId(idExame);
        }catch (EntityNotFoundException exception){
            exception.getMessage();
        }

        return mv;
    }
}
