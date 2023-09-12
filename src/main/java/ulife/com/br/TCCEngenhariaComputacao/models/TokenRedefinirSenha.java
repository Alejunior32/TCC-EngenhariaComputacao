package ulife.com.br.TCCEngenhariaComputacao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "token_redefinir_senha")
public class TokenRedefinirSenha {

    private static final int DATA_EXPIRACAO = 60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne()
    private Usuario usuario;

    private Date dataExpiracao;

    public TokenRedefinirSenha(Usuario usuario){
        this.usuario = usuario;
        this.token = UUID.randomUUID().toString();
        Date momentoAtual = new Date();
        this.dataExpiracao = new Date(momentoAtual.getTime() + DATA_EXPIRACAO);
    }

}
