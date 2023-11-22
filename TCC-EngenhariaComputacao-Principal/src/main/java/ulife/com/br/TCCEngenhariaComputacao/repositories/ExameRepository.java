package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;
import ulife.com.br.TCCEngenhariaComputacao.models.Exame;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame,Long> {

    @Query("SELECT e FROM Exame e WHERE e.titulo LIKE %:palavra%")
    List<Exame> findAllByPalavraBarraDePesquisa(String palavra);
}