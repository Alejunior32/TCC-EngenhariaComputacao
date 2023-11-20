package ulife.com.br.TCCEngenhariaComputacao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoExame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exame exame;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Horario horario;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataConsulta;
    private String statusAgendamentoPaciente;
}
