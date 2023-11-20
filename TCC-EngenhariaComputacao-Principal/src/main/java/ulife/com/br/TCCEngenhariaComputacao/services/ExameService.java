package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;
import ulife.com.br.TCCEngenhariaComputacao.models.Exame;
import ulife.com.br.TCCEngenhariaComputacao.repositories.EspecialidadeRepository;
import ulife.com.br.TCCEngenhariaComputacao.repositories.ExameRepository;

import java.util.List;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public List<Exame> listar() {
        return exameRepository.findAll();
    }

    public List<Exame> listar(String palavra) {
        if (palavra != null)
            return exameRepository.findAllByPalavraBarraDePesquisa(palavra);

        return exameRepository.findAll();
    }

    public Exame buscarPorId(Long idEspecialidade) {
        return exameRepository.findById(idEspecialidade).orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada!"));
    }

    public Exame salvar(Exame exame) {
        return exameRepository.save(exame);
    }
}
