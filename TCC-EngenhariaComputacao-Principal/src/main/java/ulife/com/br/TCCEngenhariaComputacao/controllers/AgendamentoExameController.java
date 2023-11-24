package ulife.com.br.TCCEngenhariaComputacao.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ulife.com.br.TCCEngenhariaComputacao.dto.agendamento.AgendamentoMapper;
import ulife.com.br.TCCEngenhariaComputacao.dto.agendamento.CadastroAgendamentoDto;
import ulife.com.br.TCCEngenhariaComputacao.dto.agendamento.CadastroAgendamentoExameDto;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.models.*;
import ulife.com.br.TCCEngenhariaComputacao.services.AgendamentoExameService;
import ulife.com.br.TCCEngenhariaComputacao.services.ExameService;
import ulife.com.br.TCCEngenhariaComputacao.services.HorarioService;
import ulife.com.br.TCCEngenhariaComputacao.services.PacienteService;

import java.time.LocalDate;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoExameController {

    @Autowired
    private AgendamentoExameService agendamentoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ExameService exameService;

    @Autowired
    private HorarioService horarioService;

    @GetMapping("/exame")
    public ModelAndView listarAgendamentosExame(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("agendamento/lista-exames-geral.html");

        if (usuario.getRole().equals(Role.ROLE_PACIENTE)) {
            mv = new ModelAndView("agendamento/lista-exames-paciente.html");
            mv.addObject("agendamentos", agendamentoService.listarAgendamentosPaciente(pacienteService.findByUsuario(usuario)));
        } else {
            mv = new ModelAndView("agendamento/lista-exames-geral.html");
            mv.addObject("agendamentos", agendamentoService.listarAgendamentosConsultaAprovacao());
        }

        return mv;
    }

    @GetMapping("/exame/agendar")
    public ModelAndView agendarExame(Authentication authentication, @RequestParam(required = false) Long idExame,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataConsulta) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView();

        if (idExame != null) {
            mv.setViewName("agendamento/exame-form");
            mv.addObject("idExame", idExame);
            if (dataConsulta !=  null){
                mv.addObject("horarios", horarioService.findAll());
                CadastroAgendamentoExameDto cadastroAgendamentoExameDto = new CadastroAgendamentoExameDto(new Exame(idExame),null,null, dataConsulta);
                mv.addObject("cadastroAgendamentoDto", cadastroAgendamentoExameDto);
            }
        } else {
            mv.setViewName("agendamento/escolha-exame");
            mv.addObject("exames", exameService.listar());
        }

        return mv;
    }

    @PostMapping("/exame/agendar")
    public ModelAndView cadastroAgendamentoExame(Authentication authentication,CadastroAgendamentoExameDto cadastroAgendamentoExameDto){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("redirect:/agendamentos");
        cadastroAgendamentoExameDto.setPaciente(pacienteService.findByUsuario(usuario));
        agendamentoService.salvarAgendamento(AgendamentoMapper.fromDto(cadastroAgendamentoExameDto));
        return mv;
    }

    @RequestMapping("exame/excluir")
    public ModelAndView excluirAgendamentoConsulta(Authentication authentication, @RequestParam Long idAgendamento) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("redirect:/agendamentos/exame");

        try{
            agendamentoService.excluirAgendamento(idAgendamento);
        } catch (EntityNotFoundException exception){
            exception.getMessage();
        }

        return mv;
    }

    @RequestMapping("exame/confirmaAgendamento")
    public ModelAndView confirmarAgendamentoConsulta(Authentication authentication, @RequestParam Long idAgendamento) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("redirect:/agendamentos/exame");

        try{
            agendamentoService.atualizarStatusConsulta(idAgendamento);
        } catch (EntityNotFoundException exception){
            exception.getMessage();
        }

        return mv;
    }
}
