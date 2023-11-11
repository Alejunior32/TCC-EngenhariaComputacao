package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByLogin(String login);

    boolean existsByLogin(String login);
}
