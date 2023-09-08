package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.models.Usuario;
import ulife.com.br.TCCEngenhariaComputacao.repositories.MedicoRepository;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Medico> listar(String palavra) {
        if (palavra != null)
            return medicoRepository.findAllByPalavraBarraDePesquisa(palavra);

        return medicoRepository.findAll();
    }

    public Medico findById(Long idMedico) {
        return medicoRepository.findById(idMedico).orElseThrow(() -> new EntityNotFoundException("Medico não encontrado!"));
    }

    public Medico save(Medico medico, Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        medico.setUsuario(usuarioSalvo);
        return medicoRepository.save(medico);
    }

    public Medico findByUsuario(Usuario usuario) {
        return medicoRepository.findByUsuario(usuario);
    }
}
