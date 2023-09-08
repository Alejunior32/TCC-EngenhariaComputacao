package ulife.com.br.TCCEngenhariaComputacao.dto.paciente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroPacienteDTO{
    String nome;
    LocalDate dataNascimento;
    String email;

}
