package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByUsuario_Id(Long usuairoId);
}
