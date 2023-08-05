package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade,Long> {
}
