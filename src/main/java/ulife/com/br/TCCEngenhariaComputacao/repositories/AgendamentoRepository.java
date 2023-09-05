package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Agendamento;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {

    List<Agendamento> findAllByMedico(Medico medico);

    List<Agendamento> findAllByPaciente(Paciente paciente);

}
