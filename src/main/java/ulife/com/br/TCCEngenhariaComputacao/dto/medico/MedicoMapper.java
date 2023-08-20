package ulife.com.br.TCCEngenhariaComputacao.dto.medico;

import ulife.com.br.TCCEngenhariaComputacao.models.Medico;

public class MedicoMapper {

    public static Medico fromDto(CadastroMedicoDTO cadastroMedicoDTO){
        return new Medico(null,cadastroMedicoDTO.getNome(),cadastroMedicoDTO.getCrm(),cadastroMedicoDTO.getEspecialidade(),null);
    }
}
