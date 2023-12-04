package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.enums.StatusAgendamentoPaciente;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoConsulta;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoExame;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.repositories.AgendamentoExameRepository;

import java.util.List;

@Service
public class AgendamentoExameService {

    @Autowired
    private AgendamentoExameRepository agendamentoExameRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EmailService emailService;

    public List<AgendamentoExame> listarAgendamentosPaciente(Paciente paciente){
        return agendamentoExameRepository.findAllByPaciente(paciente);

    }

    public List<AgendamentoExame> listarTodosAgendamentosExamePaciente(Paciente paciente){
        return agendamentoExameRepository.findAllByPaciente(paciente);
    }

    public List<AgendamentoExame> listarTodosAgendamentos(){
        return agendamentoExameRepository.findAll();
    }

    public List<AgendamentoExame> listarAgendamentosConsultaAprovacao(){
        return agendamentoExameRepository.findAllByStatusAgendamentoPaciente(StatusAgendamentoPaciente.AGUARDANDO_CONFIRMACAO_AGENDAMENTO.toString());
    }

    public void salvarAgendamento(AgendamentoExame agendamentoExame) {
        emailService.sendEmail(agendamentoExame.getPaciente().getUsuario().getLogin(),"Exame de " +
                        agendamentoExame.getExame().getTitulo()+ " Agendado!",
                "Exame agendado: "+ agendamentoExame.getExame().getTitulo() + "\n"
                        + "Dia: " + agendamentoExame.getDataConsulta() + "\nHorário: " + agendamentoExame.getHorario().getHoraMinuto());
        agendamentoExameRepository.save(agendamentoExame);
    }

    private AgendamentoExame buscarPorId(Long idExame) {
        return agendamentoExameRepository.findById(idExame).orElseThrow(() -> new EntityNotFoundException("Exame não existe!"));
    }

    public void excluirAgendamento(Long idAgendamento) throws EntityNotFoundException {
        AgendamentoExame agendamentoExame = buscarPorId(idAgendamento);
        agendamentoExameRepository.delete(agendamentoExame);
    }

    public void excluirAgendamentosPaciente(Paciente paciente) {
        List<AgendamentoExame> agendamentosPaciente = listarTodosAgendamentosExamePaciente(paciente);
        if (agendamentosPaciente != null && !agendamentosPaciente.isEmpty()) {
            for (AgendamentoExame agendamento : agendamentosPaciente) {
                agendamentoExameRepository.delete(agendamento);
            }
        }
    }

    public void atualizarStatusConsulta(Long idAgendamento) throws EntityNotFoundException {
        AgendamentoExame agendamentoExame = buscarPorId(idAgendamento);
        agendamentoExame.setStatusAgendamentoPaciente("PACIENTE_PRESENTE");
        agendamentoExameRepository.save(agendamentoExame);
    }

}
