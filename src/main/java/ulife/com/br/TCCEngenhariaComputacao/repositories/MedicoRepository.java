package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Medico findByUsuario(Usuario usuario);
}
