package ulife.com.br.TCCEngenhariaComputacao.dto.convenio;

import ulife.com.br.TCCEngenhariaComputacao.dto.paciente.CadastroPacienteDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Convenio;

public class ConvenioMapper {

    public static Convenio fromDto(CadastroConvenioDTO cadastroConvenioDTO){
        return new Convenio(null, cadastroConvenioDTO.getNomeConvenio(), cadastroConvenioDTO.getPlano(), cadastroConvenioDTO.getNumeroIdentificacao(), cadastroConvenioDTO.getDataInicio(),cadastroConvenioDTO.getDataTermino());
    }
}
