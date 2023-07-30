package ulife.com.br.TCCEngenhariaComputacao.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;

@Table(name = "medicos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Usuario{

    private String especialidade;

    public Medico(Long id, String login, String senha, Role role, String especialidade) {
        super(id, login, senha, role);
        this.especialidade = especialidade;
    }

}
