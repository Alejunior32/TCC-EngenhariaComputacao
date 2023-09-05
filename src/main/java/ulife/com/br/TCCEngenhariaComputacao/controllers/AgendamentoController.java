package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.CadastroMedicoDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.MedicoMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.usuario.UsuarioMapper;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.services.AgendamentoService;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping
    public ModelAndView listarAgendamentos(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        ModelAndView mv = new ModelAndView("agendamento/lista.html");
        mv.addObject("agendamentos",agendamentoService.listarPorUsuario(usuario));
        return mv;
    }

//    @GetMapping("cadastrar")
//    public ModelAndView formularioCadastro(){
//        ModelAndView mv = new ModelAndView("medico/form.html");
//        mv.addObject("cadastroMedicoDto", new CadastroMedicoDTO());
//        mv.addObject("especialidades", especialidadeService.listar());
//        return mv;
//    }
//
//    @PostMapping("cadastrar")
//    public ModelAndView cadastrarMedico(@Valid CadastroMedicoDTO cadastroMedicoDTO, RedirectAttributes redirectAttributes){
//        ModelAndView mv;
//        if (String.valueOf(cadastroMedicoDTO.getCrm()).length() == 6){
//            mv = new ModelAndView("redirect:/medico");
//            redirectAttributes.addAttribute("message","Novo médico cadastrado com sucesso!");
//            medicoService.save(MedicoMapper.fromDto(cadastroMedicoDTO), UsuarioMapper.fromMedico(cadastroMedicoDTO));
//        }else {
//            mv = new ModelAndView("redirect:/medico/cadastrar");
//            redirectAttributes.addFlashAttribute("erro", "crm informado incorreto!");
//        }
//        return  mv;
//    }
}
