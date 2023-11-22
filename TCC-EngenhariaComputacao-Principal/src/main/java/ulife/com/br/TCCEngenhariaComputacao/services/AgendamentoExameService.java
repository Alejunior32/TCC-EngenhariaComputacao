package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoConsulta;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoExame;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.repositories.AgendamentoExameRepository;

import java.util.List;

@Service
public class AgendamentoExameService {

    @Autowired
    AgendamentoExameRepository agendamentoExameRepository;

    @Autowired
    PacienteService pacienteService;

    public List<AgendamentoExame> listarAgendamentosPaciente(Paciente paciente){
        return agendamentoExameRepository.findAllByPaciente(paciente);
    }
}
