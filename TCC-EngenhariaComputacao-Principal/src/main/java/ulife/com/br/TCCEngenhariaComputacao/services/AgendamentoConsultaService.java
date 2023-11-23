package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.enums.StatusAgendamentoPaciente;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoConsulta;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.AgendamentoConsultaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoConsultaService {

    @Autowired
    private AgendamentoConsultaRepository agendamentoConsultaRepository;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;


    public List<AgendamentoConsulta> listarAgendamentosConsultaPaciente(Paciente paciente){
        return agendamentoConsultaRepository.findAllByPaciente(paciente);
    }

    public List<AgendamentoConsulta> listarAgendamentosConsultaMedico(Medico medico){
        return agendamentoConsultaRepository.findAllByMedicoAndDataConsulta(medico, LocalDate.now());
    }

    public List<AgendamentoConsulta> listarTodosAgendamentosConsultaMedico(Medico medico){
        return agendamentoConsultaRepository.findAllByMedico(medico);
    }

    public List<AgendamentoConsulta> listarTodosAgendamentosConsultaPaciente(Paciente paciente){
        return agendamentoConsultaRepository.findAllByPaciente(paciente);
    }

    public List<AgendamentoConsulta> listarAgendamentosConsultaAprovacao(){
        return agendamentoConsultaRepository.findAllByStatusAgendamentoPaciente(StatusAgendamentoPaciente.AGUARDANDO_CONFIRMACAO_AGENDAMENTO);
    }

    public AgendamentoConsulta salvarAgendamento(AgendamentoConsulta agendamentoConsulta){
        return agendamentoConsultaRepository.save(agendamentoConsulta);
    }

    public void excluirAgendamentosPaciente(Paciente paciente){
        List<AgendamentoConsulta> agendamentosPaciente = listarTodosAgendamentosConsultaPaciente(paciente);
        if (agendamentosPaciente != null && !agendamentosPaciente.isEmpty()) {
            for (AgendamentoConsulta agendamento : agendamentosPaciente) {
                agendamentoConsultaRepository.delete(agendamento);
            }
        }
    }

    public void excluirAgendamentosMedico(Medico medico){
        List<AgendamentoConsulta> agendamentosMedico = listarTodosAgendamentosConsultaMedico(medico);
        if (agendamentosMedico != null && !agendamentosMedico.isEmpty()) {
            for (AgendamentoConsulta agendamento : agendamentosMedico) {
                agendamentoConsultaRepository.delete(agendamento);
            }
        }
    }
}
