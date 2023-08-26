package ulife.com.br.TCCEngenhariaComputacao.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NovaSenhaDTO {

    String novaSenha;
    String confirmarSenha;
}
