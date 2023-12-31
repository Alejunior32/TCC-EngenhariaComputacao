package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;
import ulife.com.br.TCCEngenhariaComputacao.services.EspecialidadeService;

@Controller
@RequestMapping("/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping
    public ModelAndView listarEspecialidades(@RequestParam(name = "palavra", required = false) String palavra) {
        ModelAndView mv = new ModelAndView("especialidade/lista.html");
        mv.addObject("especialidades",especialidadeService.listar(palavra));
        return mv;
    }

    @GetMapping("cadastrar")
    private ModelAndView formularioEspecialidade(@RequestParam(required = false) Long idEspecialidade){
        ModelAndView mv = new ModelAndView("especialidade/form.html");

        Especialidade especialidade;

        if (idEspecialidade == null){
            especialidade = new Especialidade();
        } else {
            try {
                especialidade = especialidadeService.buscarPorId(idEspecialidade);
            } catch (Exception e) {
                especialidade = new Especialidade();
                mv = new ModelAndView("redirect:/especialidade");
                mv.addObject("mensagem", "Falha ao editar Especialidade");
            }
        }

        mv.addObject("especialidade", especialidade);
        return mv;
    }

    @PostMapping("cadastrar")
    private ModelAndView cadastrarEspecialidade(@Valid Especialidade especialidade, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/especialidade");
        redirectAttributes.addFlashAttribute("mensagem","Nova Especialidade cadastrada com sucesso!");
        especialidadeService.salvar(especialidade);
        return  mv;
    }

    @RequestMapping("excluir")
    public ModelAndView excluirEspecilidade(@RequestParam Long idEspecialidade){
        ModelAndView mv = new ModelAndView("redirect:/especialidade");
        try{
            especialidadeService.excluirPorId(idEspecialidade);
        }catch (EntityNotFoundException exception){
            exception.getMessage();
        }

        return mv;
    }

}
