package ulife.com.br.TCCEngenhariaComputacao.dto.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ulife.com.br.TCCEngenhariaComputacao.dto.medico.CadastroMedicoDTO;
import ulife.com.br.TCCEngenhariaComputacao.dto.paciente.CadastroPacienteDTO;
import ulife.com.br.TCCEngenhariaComputacao.enums.Role;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;

import java.util.Random;

public class UsuarioMapper {

    public static Usuario fromMedico(CadastroMedicoDTO cadastroMedicoDTO){
        Random random = new Random();
        String senha = String.valueOf(random.nextInt(900000) + 100000);
        System.out.println(senha);
        return new Usuario(null, cadastroMedicoDTO.getEmail(),new BCryptPasswordEncoder().encode(senha),true,true, Role.ROLE_MEDICO);
    }

    public static  Usuario fromPaciente(CadastroPacienteDTO cadastroPacienteDTO){
       return new Usuario(null, cadastroPacienteDTO.getEmail(),new BCryptPasswordEncoder().encode(cadastroPacienteDTO.getSenha()),true,true,Role.ROLE_PACIENTE);
    }
}
