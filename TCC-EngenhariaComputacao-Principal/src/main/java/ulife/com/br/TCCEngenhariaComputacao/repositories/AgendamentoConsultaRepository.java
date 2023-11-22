package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.enums.StatusAgendamentoPaciente;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoConsulta;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoConsultaRepository extends JpaRepository<AgendamentoConsulta,Long> {

    List<AgendamentoConsulta> findAllByMedicoAndDataConsulta(Medico medico, LocalDate dataConsulta);

    List<AgendamentoConsulta> findAllByPaciente(Paciente paciente);

    List<AgendamentoConsulta> findAllByStatusAgendamentoPaciente(StatusAgendamentoPaciente status);

}
