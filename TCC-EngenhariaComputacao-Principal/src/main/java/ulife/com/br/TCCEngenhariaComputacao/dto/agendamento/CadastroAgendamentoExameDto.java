package ulife.com.br.TCCEngenhariaComputacao.dto.agendamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ulife.com.br.TCCEngenhariaComputacao.models.Exame;
import ulife.com.br.TCCEngenhariaComputacao.models.Horario;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroAgendamentoExameDto{

    private Exame exame;

    private Paciente paciente;

    private Horario horario;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataConsulta;

}

