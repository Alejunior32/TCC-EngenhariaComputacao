package ulife.com.br.TCCEngenhariaComputacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ulife.com.br.TCCEngenhariaComputacao.models.Horario;
import ulife.com.br.TCCEngenhariaComputacao.models.Medico;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface HorarioRepository extends JpaRepository<Horario,Long> {

    @Query("SELECT h FROM Horario h " +
            "WHERE NOT EXISTS (SELECT a FROM Agendamento a " +
            "                  WHERE a.horario = h " +
            "                  AND a.dataConsulta = :dataConsulta " +
            "                  AND a.medico = :medico)")
    List<Horario> findHorariosDisponiveisDoMedicoNoDia(Medico medico, LocalDate dataConsulta);
}




