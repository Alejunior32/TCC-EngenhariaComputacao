package ulife.com.br.TCCEngenhariaComputacao.dto.paciente;

import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;

public class PacienteMapper {

    public static Paciente fromDto(CadastroPacienteDTO cadastroPacienteDTO){
        return new Paciente(null,cadastroPacienteDTO.getNome(),cadastroPacienteDTO.getDataNascimento(),null,null);
    }
}
