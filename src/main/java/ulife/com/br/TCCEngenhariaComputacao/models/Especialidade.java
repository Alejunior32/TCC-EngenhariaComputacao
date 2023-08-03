package ulife.com.br.TCCEngenhariaComputacao.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "especialidades")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

}
