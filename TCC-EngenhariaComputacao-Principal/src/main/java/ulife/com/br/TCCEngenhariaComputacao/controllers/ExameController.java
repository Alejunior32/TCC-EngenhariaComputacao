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
    private ModelAndView formularioExame(@RequestParam(required = false) Long idExame){
        ModelAndView mv = new ModelAndView("exame/form.html");

        Exame exame;

        if (idExame == null){
            exame = new Exame();
        } else {
            try {
                exame = exameService.buscarPorId(idExame);
            } catch (Exception e){
                exame = new Exame();
                mv = new ModelAndView("redirect:/exame");
                mv.addObject("mensagem", "Falha ao editar Exame");
            }
        }

        mv.addObject("exame", exame);
        return mv;
    }

    @PostMapping("cadastrar")
    private ModelAndView cadastrarExame(@Valid Exame exame, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/exame");
        redirectAttributes.addFlashAttribute("mensagem","Novo Exame cadastrado com sucesso!");
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
