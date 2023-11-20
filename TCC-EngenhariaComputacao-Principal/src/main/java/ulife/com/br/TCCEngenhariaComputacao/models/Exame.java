package ulife.com.br.TCCEngenhariaComputacao.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "exame")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    private String urlImagem;

    public Exame(Long id) {
        this.id = id;
    }
}
