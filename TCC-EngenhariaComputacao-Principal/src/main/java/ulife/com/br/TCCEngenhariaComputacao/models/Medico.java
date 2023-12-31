package ulife.com.br.TCCEngenhariaComputacao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "medico")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    private Integer crm;

    @ManyToOne
    private Especialidade especialidade;

    @JsonIgnore
    @OneToMany(mappedBy = "medico")
    private List<AgendamentoConsulta> agendamentoConsultas;

    @OneToOne
    private Usuario usuario;

    public Medico(Long id) {
        this.id = id;
    }
}
