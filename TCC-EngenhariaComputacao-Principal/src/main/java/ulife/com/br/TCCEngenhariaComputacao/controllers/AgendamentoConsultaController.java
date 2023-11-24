package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
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

import java.time.LocalDate;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoConsultaController {

    @Autowired
    private AgendamentoConsultaService agendamentoService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ModelAndView agendamento(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv ;

        if (usuario.getRole().equals(Role.ROLE_PACIENTE))
            mv = new ModelAndView("agendamento/escolha-agendamento-paciente.html");
        else if (usuario.getRole().equals(Role.ROLE_MEDICO))
            mv =  new ModelAndView("redirect:/agendamentos/consulta");
        else
            mv = new ModelAndView("agendamento/escolha-agendamento-admin.html");

        return mv;
    }

    @GetMapping("/consulta")
    public ModelAndView listarAgendamentosConsulta(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv;

        if (usuario.getRole().equals(Role.ROLE_PACIENTE)) {
            mv = new ModelAndView("agendamento/lista-consultas-paciente.html");
            mv.addObject("agendamentos", agendamentoService.listarAgendamentosConsultaPaciente(pacienteService.findByUsuario(usuario)));
        }
        else if (usuario.getRole().equals(Role.ROLE_MEDICO)){
            mv = new ModelAndView("agendamento/lista-consultas-medico.html");
            mv.addObject("agendamentos", agendamentoService.listarAgendamentosConsultaMedico(medicoService.findByUsuario(usuario)));
        }
        else{
            mv = new ModelAndView("agendamento/lista-consultas-admin.html");
            mv.addObject("agendamentos", agendamentoService.listarAgendamentosConsultaAprovacao());
        }

        return mv;
    }

    @GetMapping("/consulta/agendar")
    public ModelAndView agendar(Authentication authentication,@RequestParam(required = false) Long idEspecialidade ,
                                @RequestParam(required = false) Long idMedico,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataConsulta) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView();

        if (idEspecialidade != null) {
            // Se o idEspecialidade estiver presente, redirecione para o formulário de agendamento
            mv.setViewName("agendamento/form");
            mv.addObject("idEspecialidade", idEspecialidade);
            mv.addObject("medicos",medicoService.findAllByEspecialidadeId(idEspecialidade));
            if (idMedico != null && dataConsulta !=  null){
                Medico medico = medicoService.findById(idMedico);
                mv.addObject("horarios", horarioService.findHorariosDisponiveisDoMedicoNoDia(medico, dataConsulta));
                CadastroAgendamentoDto cadastroAgendamentoDto = new CadastroAgendamentoDto(new Especialidade(idEspecialidade),new Medico(idMedico),null,null, dataConsulta);
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

    @RequestMapping("consulta/excluir")
    public ModelAndView excluirAgendamentoConsulta(Authentication authentication, @RequestParam Long idAgendamento) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("redirect:/agendamentos/consulta");

        try{
            agendamentoService.excluirAgendamento(idAgendamento);
        } catch (EntityNotFoundException exception){
            exception.getMessage();
        }

        return mv;
    }

    @RequestMapping("consulta/confirmaAgendamento")
    public ModelAndView confirmarAgendamentoConsulta(Authentication authentication, @RequestParam Long idAgendamento) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("redirect:/agendamentos/consulta");

        try{
            agendamentoService.atualizarStatusConsulta(idAgendamento);
        } catch (EntityNotFoundException exception){
            exception.getMessage();
        }

        return mv;
    }

}
