package ulife.com.br.TCCEngenhariaComputacao.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ulife.com.br.TCCEngenhariaComputacao.dto.agendamento.AgendamentoMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.agendamento.CadastroAgendamentoDto;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.models.*;
import ulife.com.br.TCCEngenhariaComputacao.services.*;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoConsultaController {

    @Autowired
    AgendamentoConsultaService agendamentoService;

    @Autowired
    EspecialidadeService especialidadeService;

    @Autowired
    MedicoService medicoService;

    @Autowired
    HorarioService horarioService;

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ModelAndView agendamento(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("agendamento/lista.html");

        if (usuario.getRole().equals(Role.ROLE_PACIENTE))
            mv.addObject("agendamentos",agendamentoService.listarAgendamentosPaciente(pacienteService.findByUsuario(usuario)));

        return mv;
    }

    @GetMapping("/consulta")
    public ModelAndView listarAgendamentosConsulta(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("agendamento/lista-consultas.html");

        if (usuario.getRole().equals(Role.ROLE_PACIENTE))
            mv.addObject("agendamentos",agendamentoService.listarAgendamentosPaciente(pacienteService.findByUsuario(usuario)));

        return mv;
    }

    @GetMapping("/consulta/agendar")
    public ModelAndView agendar(Authentication authentication,@RequestParam(required = false) Long idEspecialidade ,@RequestParam(required = false) Long idMedico) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView();

        if (idEspecialidade != null) {
            // Se o idEspecialidade estiver presente, redirecione para o formulário de agendamento
            mv.setViewName("agendamento/form");
            mv.addObject("idEspecialidade", idEspecialidade);
            mv.addObject("medicos",medicoService.findAllByEspecialidadeId(idEspecialidade));
            if (idMedico != null){
                mv.addObject("horarios", horarioService.findHorariosDisponiveisDoMedicoNoDia(medicoService.findById(idMedico)));
                CadastroAgendamentoDto cadastroAgendamentoDto = new CadastroAgendamentoDto(new Especialidade(idEspecialidade),new Medico(idMedico),null,null, null);
                mv.addObject("cadastroAgendamentoDto",cadastroAgendamentoDto);
            }
        } else {
            // Se não houver idEspecialidade, mostre a página de escolha de especialidade
            mv.setViewName("agendamento/escolha-especialidade");
            mv.addObject("especialidades", especialidadeService.listar());
        }

        return mv;
    }

    @PostMapping("/consulta/agendar")
    public ModelAndView cadastroAgendamento(Authentication authentication,CadastroAgendamentoDto cadastroAgendamentoDto){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("redirect:/agendamentos");
        cadastroAgendamentoDto.setPaciente(pacienteService.findByUsuario(usuario));
        agendamentoService.salvarAgendamento(AgendamentoMapper.fromDto(cadastroAgendamentoDto));
        return mv;
    }

}
