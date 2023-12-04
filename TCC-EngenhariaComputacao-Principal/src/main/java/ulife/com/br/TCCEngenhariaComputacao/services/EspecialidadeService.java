package ulife.com.br.TCCEngenhariaComputacao.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ulife.com.br.TCCEngenhariaComputacao.models.Especialidade;
import ulife.com.br.TCCEngenhariaComputacao.repositories.EspecialidadeRepository;

import java.util.List;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> listar() {
        return especialidadeRepository.findAll();
    }

    public List<Especialidade> listar(String palavra) {
        if (palavra != null)
            return especialidadeRepository.findAllByPalavraBarraDePesquisa(palavra);

        return especialidadeRepository.findAll();
    }

    public Especialidade buscarPorId(Long idEspecialidade) {
        return especialidadeRepository.findById(idEspecialidade).orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada!"));
    }

    public Especialidade salvar(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    public void excluirPorId(Long idEspecialidade) throws EntityNotFoundException {
        Especialidade especialidade = this.buscarPorId(idEspecialidade);
        especialidadeRepository.delete(especialidade);
    }
}
