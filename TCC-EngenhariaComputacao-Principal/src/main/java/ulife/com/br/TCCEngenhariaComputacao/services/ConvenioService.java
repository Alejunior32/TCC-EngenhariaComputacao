package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.Convenio;
import ulife.com.br.TCCEngenhariaComputacao.repositories.ConvenioRepository;

@Service
public class ConvenioService {

    @Autowired
    ConvenioRepository convenioRepository;

    public Convenio salvar(Convenio convenio){
        return convenioRepository.save(convenio);
    }

}
