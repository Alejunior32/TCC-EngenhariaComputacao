package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.services.EspecialidadeService;
import ulife.com.br.TCCEngenhariaComputacao.services.MedicoService;

@Controller
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping
    public ModelAndView listarMedicos() {
        ModelAndView mv = new ModelAndView("medico/lista.html");
        mv.addObject("medicos",medicoService.listar());
        return mv;
    }

    @GetMapping("{idMedico}")
    public ModelAndView detalhesMedico(@PathVariable Long idMedico){
        ModelAndView mv = new ModelAndView("medico/detalhes.html");
        try {
            mv.addObject("medico", medicoService.buscarPorId(idMedico));
        }catch (EntityNotFoundException exception){
            mv.addObject("erroBusca",exception.getMessage());
        }
        return mv;
    }

    @GetMapping("cadastrar")
    public ModelAndView formularioCadastro(){
        ModelAndView mv = new ModelAndView("medico/form.html");
        mv.addObject("medico", new Medico());
        mv.addObject("especialidades", especialidadeService.listar());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView cadastrarMedico(@Valid Medico medico, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/medico");
        redirectAttributes.addAttribute("message","Novo médico cadastrado com sucesso!");
        medicoService.salvar(medico);
        return  mv;
    }


}
