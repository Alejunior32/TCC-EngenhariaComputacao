package ulife.com.br.TCCEngenhariaComputacao.dto.paciente;

import ulife.com.br.TCCEngenhariaComputacao.dto.convenio.CadastroConvenioDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;

import java.util.Base64;

public class PacienteMapper {

    public static Paciente fromDto(CadastroPacienteDTO cadastroPacienteDTO){
        return new Paciente(null,cadastroPacienteDTO.getNome(), cadastroPacienteDTO.getRg(), cadastroPacienteDTO.getCpf(), cadastroPacienteDTO.getDataNascimento(), null,null,  Base64.getDecoder().decode(cadastroPacienteDTO.getImagemBase64()),null);
    }
    public static CadastroPacienteDTO formEntity(Paciente paciente){
        return new CadastroPacienteDTO(paciente.getNome(),paciente.getRg(),paciente.getCpf(),paciente.getDataNascimento(),paciente.getUsuario().getLogin(),null, new CadastroConvenioDTO(paciente.getConvenio().getNomeConvenio(), paciente.getConvenio().getPlano(), paciente.getConvenio().getNumeroIdentificacao(), paciente.getConvenio().getDataInicio(), paciente.getConvenio().getDataTermino()));
    }
}
