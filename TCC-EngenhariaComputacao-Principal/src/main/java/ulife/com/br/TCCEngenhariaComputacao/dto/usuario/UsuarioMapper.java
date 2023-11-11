package ulife.com.br.TCCEngenhariaComputacao.dto.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.CadastroMedicoDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.paciente.CadastroPacienteDTO;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

import java.util.Random;

public class UsuarioMapper {

    public static Usuario fromMedico(CadastroMedicoDTO cadastroMedicoDTO){
        return new Usuario(null, cadastroMedicoDTO.getEmail(),null,true,true, Role.ROLE_MEDICO);
    }

    public static  Usuario fromPaciente(CadastroPacienteDTO cadastroPacienteDTO){
       return new Usuario(null, cadastroPacienteDTO.getEmail(),null,true,true,Role.ROLE_PACIENTE);
    }
}
