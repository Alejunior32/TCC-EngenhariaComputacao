package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.repositories.MedicoRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    public Medico buscarPorId(Long idMedico) {
        return medicoRepository.findById(idMedico).orElseThrow(() -> new EntityNotFoundException("Medico não encontrado!"));
    }
}
