package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.CadastroMedicoDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.MedicoMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.usuario.UsuarioMapper;
import ulife.com.br.TCCEngenhariaComputacao.models.Agendamento;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.services.AgendamentoService;
import ulife.com.br.TCCEngenhariaComputacao.services.EspecialidadeService;
import ulife.com.br.TCCEngenhariaComputacao.services.MedicoService;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    EspecialidadeService especialidadeService;

    @Autowired
    MedicoService medicoService;

    @GetMapping
    public ModelAndView listarAgendamentos(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        ModelAndView mv = new ModelAndView("agendamento/lista.html");
        mv.addObject("agendamentos",agendamentoService.listarPorUsuario(usuario));
        return mv;
    }

    @GetMapping({"/agendar"})
    public ModelAndView especialidadeConsulta(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        ModelAndView mv = new ModelAndView("agendamento/escolha-especialidade");
        mv.addObject("especialidades",especialidadeService.listar());
        return mv;
    }

    @GetMapping("/agendar?idEspecialidade={idEspecialidade}")
    public ModelAndView agendarConsulta(Authentication authentication,@RequestParam Long idEspecialidade) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        ModelAndView mv = new ModelAndView("agendamento/form");

        return mv;
    }
}
