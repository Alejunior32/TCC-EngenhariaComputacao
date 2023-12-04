package ulife.com.br.TCCEngenhariaComputacao.dto.paciente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ulife.com.br.TCCEngenhariaComputacao.dto.convenio.CadastroConvenioDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroPacienteDTO{
    private String nome;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String imagemBase64;
    private CadastroConvenioDTO convenio;

}
