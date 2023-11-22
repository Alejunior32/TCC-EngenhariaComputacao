package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Medico findByUsuario(Usuario usuario);

    @Query("SELECT m FROM Medico m WHERE m.nome LIKE %:palavra%")
    List<Medico> findAllByPalavraBarraDePesquisa(String palavra);

    List<Medico> findAllByEspecialidadeId(Long especialidadeId);
}
