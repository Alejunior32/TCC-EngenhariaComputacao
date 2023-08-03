package ulife.com.br.TCCEngenhariaComputacao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table(name = "pacientes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentos;

    @OneToOne
    private Usuario usuario;

}
