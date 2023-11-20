package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoConsulta;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoExame;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;

import java.util.List;

@Repository
public interface AgendamentoExameRepository extends JpaRepository<AgendamentoConsulta,Long> {

    List<AgendamentoExame> findAllByPaciente(Paciente paciente);
}