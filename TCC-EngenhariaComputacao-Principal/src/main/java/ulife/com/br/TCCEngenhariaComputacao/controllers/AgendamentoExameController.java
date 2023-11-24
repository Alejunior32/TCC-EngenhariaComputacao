package ulife.com.br.TCCEngenhariaComputacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.services.AgendamentoExameService;
import ulife.com.br.TCCEngenhariaComputacao.services.ExameService;
import ulife.com.br.TCCEngenhariaComputacao.services.PacienteService;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoExameController {

    @Autowired
    AgendamentoExameService agendamentoService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    ExameService exameService;

    @GetMapping("/exame")
    public ModelAndView listarAgendamentosExame(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("agendamento/lista-exames-geral.html");

        if (usuario.getRole().equals(Role.ROLE_PACIENTE)) {
            mv = new ModelAndView("agendamento/lista-exames-paciente.html");
            mv.addObject("agendamentos", agendamentoService.listarAgendamentosPaciente(pacienteService.findByUsuario(usuario)));
        } else if (usuario.getRole().equals(Role.ROLE_ADMIN)||usuario.getRole().equals(Role.ROLE_MEDICO)) {
            mv = new ModelAndView("agendamento/lista-exames-geral.html");
            mv.addObject("agendamentos", agendamentoService.listarTodosAgendamentos());
        }

        return mv;
    }

    @GetMapping("/exame/agendar")
    public ModelAndView agendarExame(Authentication authentication, @RequestParam(required = false) Long idExame ) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView();

        mv.setViewName("agendamento/escolha-exame");
        mv.addObject("exames", exameService.listar());


        return mv;
    }
//
//    @PostMapping("/exame/agendar")
//    public ModelAndView cadastroAgendamentoExame(Authentication authentication,CadastroAgendamentoDto cadastroAgendamentoDto){
//        Usuario usuario = (Usuario) authentication.getPrincipal();
//        ModelAndView mv = new ModelAndView("redirect:/agendamentos");
//        cadastroAgendamentoDto.setPaciente(pacienteService.findByUsuario(usuario));
//        agendamentoService.salvarAgendamento(AgendamentoMapper.fromDto(cadastroAgendamentoDto));
//        return mv;
//    }
}
