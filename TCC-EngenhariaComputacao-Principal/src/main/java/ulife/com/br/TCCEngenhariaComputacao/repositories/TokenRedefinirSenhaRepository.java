package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.TokenRedefinirSenha;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRedefinirSenhaRepository extends JpaRepository<TokenRedefinirSenha,Long> {

    Optional<TokenRedefinirSenha> findByToken(String token);
}
