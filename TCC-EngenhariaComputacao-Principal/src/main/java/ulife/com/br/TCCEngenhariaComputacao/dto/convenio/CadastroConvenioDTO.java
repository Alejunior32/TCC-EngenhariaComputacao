package ulife.com.br.TCCEngenhariaComputacao.dto.convenio;

import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ulife.com.br.TCCEngenhariaComputacao.config.encrypt.EncryptString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroConvenioDTO {

    private String nomeConvenio;
    private String plano;
    private String numeroIdentificacao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
}
