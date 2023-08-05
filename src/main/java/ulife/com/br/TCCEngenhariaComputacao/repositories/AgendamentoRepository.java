package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
}
