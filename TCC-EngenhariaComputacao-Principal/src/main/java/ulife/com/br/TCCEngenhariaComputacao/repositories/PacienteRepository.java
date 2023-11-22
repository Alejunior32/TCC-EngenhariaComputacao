package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Paciente findByUsuario(Usuario usuario);

    @Query("SELECT p FROM Paciente p WHERE p.nome LIKE %:palavra%")
    List<Paciente> findAllByPalavraBarraDePesquisa(String palavra);

}
