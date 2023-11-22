package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;

import java.util.List;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade,Long> {

    @Query("SELECT e FROM Especialidade e WHERE e.titulo LIKE %:palavra%")
    List<Especialidade> findAllByPalavraBarraDePesquisa(String palavra);
}
