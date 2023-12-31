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
    public ModelAndView listarMedicos(@RequestParam(name = "palavra", required = false) String palavra ) {
        ModelAndView mv = new ModelAndView("medico/lista.html");
        mv.addObject("medicos",medicoService.listar(palavra));
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
    public ModelAndView cadastrarMedico(@Valid CadastroMedicoDTO cadastroMedicoDTO, RedirectAttributes redirectAttributes) {
        ModelAndView mv;
        if (String.valueOf(cadastroMedicoDTO.getCrm()).length() == 6 && !medicoService.usuarioExistente(cadastroMedicoDTO.getEmail())) {

            mv = new ModelAndView("redirect:/medico");
            mv.addObject("message", "Novo médico cadastrado com sucesso!");
            medicoService.save(MedicoMapper.fromDto(cadastroMedicoDTO), UsuarioMapper.fromMedico(cadastroMedicoDTO));
        } else if (medicoService.usuarioExistente(cadastroMedicoDTO.getEmail())) {

            mv = new ModelAndView("redirect:/medico/cadastrar");
            mv.addObject("erro","Usuário já cadastrado");
        } else {

            mv = new ModelAndView("redirect:/medico/cadastrar");
            mv.addObject("erro", "crm informado incorreto!");
        }
        return  mv;
    }

    @GetMapping("editar")
    public ModelAndView formEditar(@RequestParam(name = "idMedico") Long idMedico ){
        ModelAndView mv = new ModelAndView("medico/editar.html");

        Medico medico = medicoService.findById(idMedico);

        mv.addObject("cadastroMedicoDto", MedicoMapper.formeEntity(medico));
        mv.addObject("idMedico",idMedico);
        mv.addObject("especialidades", especialidadeService.listar());
        return mv;
    }

    @PostMapping("editar")
    public ModelAndView editarMedico(@Valid CadastroMedicoDTO cadastroMedicoDTO, RedirectAttributes redirectAttributes, @RequestParam(name = "idMedico") Long idMedico ) {
        ModelAndView mv= new ModelAndView("redirect:/medico");

        if (String.valueOf(cadastroMedicoDTO.getCrm()).length() == 6 ) {
            mv.addObject("message", "Medico editado");
            medicoService.editar(cadastroMedicoDTO,idMedico);
        }

        return  mv;
    }



    @RequestMapping("excluir")
    public ModelAndView excluirMedico(@RequestParam Long idMedico){
        ModelAndView mv = new ModelAndView("redirect:/medico");
        try{
            medicoService.excluirPorId(idMedico);
        }catch (EntityNotFoundException exception){
            exception.getMessage();
        }

        return mv;
    }

}
