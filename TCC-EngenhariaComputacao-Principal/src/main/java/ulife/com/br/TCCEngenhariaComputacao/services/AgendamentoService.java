package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.models.Agendamento;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.AgendamentoRepository;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    MedicoService medicoService;

    @Autowired
    PacienteService pacienteService;

    public List<Agendamento> listarPorUsuario(Usuario usuario) {
        if (usuario.getRole().equals(Role.ROLE_MEDICO))
            return agendamentoRepository.findAllByMedico(medicoService.findByUsuario(usuario));

        return agendamentoRepository.findAllByPaciente(pacienteService.findByUsuario(usuario));
    }

    public List<Agendamento> listarAgendamentosPaciente(Paciente paciente){
        return agendamentoRepository.findAllByPaciente(paciente);
    }

    public Agendamento salvarAgendamento(Agendamento agendamento){
        return agendamentoRepository.save(agendamento);
    }
}
