package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoConsulta;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.AgendamentoConsultaRepository;

import java.util.List;

@Service
public class AgendamentoConsultaService {

    @Autowired
    AgendamentoConsultaRepository agendamentoConsultaRepository;

    @Autowired
    MedicoService medicoService;

    @Autowired
    PacienteService pacienteService;

    public List<AgendamentoConsulta> listarPorUsuario(Usuario usuario) {
        if (usuario.getRole().equals(Role.ROLE_MEDICO))
            return agendamentoConsultaRepository.findAllByMedico(medicoService.findByUsuario(usuario));

        return agendamentoConsultaRepository.findAllByPaciente(pacienteService.findByUsuario(usuario));
    }

    public List<AgendamentoConsulta> listarAgendamentosPaciente(Paciente paciente){
        return agendamentoConsultaRepository.findAllByPaciente(paciente);
    }

    public AgendamentoConsulta salvarAgendamento(AgendamentoConsulta agendamentoConsulta){
        return agendamentoConsultaRepository.save(agendamentoConsulta);
    }
}
