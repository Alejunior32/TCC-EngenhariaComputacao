package ulife.com.br.TCCEngenhariaComputacao.dto.medico;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CadastroMedicoDTO {

    String nome;

    String email;

    Integer crm;

     Especialidade especialidade;
}
