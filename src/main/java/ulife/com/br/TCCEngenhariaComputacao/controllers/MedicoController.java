package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.CadastroMedicoDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.MedicoMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.usuario.UsuarioMapper;
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
        mv.addObject("medicos",medicoService.listAll());
        return mv;
    }

    @GetMapping("/detalhes")
    public ModelAndView detalhesMedico(@RequestParam Long idMedico){
        ModelAndView mv = new ModelAndView("medico/detalhes.html");
        try {
            mv.addObject("medico", medicoService.findById(idMedico));
        }catch (EntityNotFoundException exception){
            mv.addObject("erroBusca",exception.getMessage());
        }
        return mv;
    }

    @GetMapping("cadastrar")
    public ModelAndView formularioCadastro(){
        ModelAndView mv = new ModelAndView("medico/form.html");
        mv.addObject("cadastroMedicoDto", new CadastroMedicoDTO());
        mv.addObject("especialidades", especialidadeService.listar());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView cadastrarMedico(@Valid CadastroMedicoDTO cadastroMedicoDTO, RedirectAttributes redirectAttributes){
        ModelAndView mv;
        if (String.valueOf(cadastroMedicoDTO.getCrm()).length() == 6){
            mv = new ModelAndView("redirect:/medico");
            redirectAttributes.addAttribute("message","Novo médico cadastrado com sucesso!");
            medicoService.save(MedicoMapper.fromDto(cadastroMedicoDTO), UsuarioMapper.fromMedico(cadastroMedicoDTO));
        }else {
            mv = new ModelAndView("redirect:/medico/cadastrar");
            redirectAttributes.addFlashAttribute("erro", "crm informado incorreto!");
        }
        return  mv;
    }


}
