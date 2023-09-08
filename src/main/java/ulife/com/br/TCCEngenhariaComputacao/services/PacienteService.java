package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.dto.paciente.CadastroPacienteDTO;
import ulife.com.br.TCCEngenhariaComputacao.models.Paciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    UsuarioService usuarioService;

    public Paciente buscarPorId(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElseThrow(() -> new EntityNotFoundException("Paciente não existe!"));
    }

    public Paciente salvar(Paciente paciente, Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        paciente.setUsuario(usuario);
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listar(String palavra) {
        if (palavra != null)
            return pacienteRepository.findAllByPalavraBarraDePesquisa(palavra);

        return pacienteRepository.findAll();
    }



    public Paciente findByUsuario(Usuario usuario) {
        return pacienteRepository.findByUsuario(usuario);
    }
}
