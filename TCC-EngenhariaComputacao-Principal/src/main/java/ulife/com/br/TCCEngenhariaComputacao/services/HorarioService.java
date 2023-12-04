package ulife.com.br.TCCEngenhariaComputacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulife.com.br.TCCEngenhariaComputacao.models.Horario;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;
import ulife.com.br.TCCEngenhariaComputacao.repositories.HorarioRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    HorarioRepository horarioRepository;

    public List<Horario> findHorariosDisponiveisDoMedicoNoDia(Medico medico,LocalDate dataConsulta){
        return horarioRepository.findHorariosDisponiveisDoMedicoNoDia(medico, dataConsulta);
    }

    public List<Horario> findAll(){
        return horarioRepository.findAll();
    }

}
