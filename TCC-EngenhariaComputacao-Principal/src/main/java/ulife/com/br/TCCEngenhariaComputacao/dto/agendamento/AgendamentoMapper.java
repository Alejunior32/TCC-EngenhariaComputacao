package ulife.com.br.TCCEngenhariaComputacao.dto.agendamento;

import ulife.com.br.TCCEngenhariaComputacao.dto.convenio.CadastroConvenioDTO;
import ulife.com.br.TCCEngenhariaComputacao.enums.StatusAgendamentoMedico;
import ulife.com.br.TCCEngenhariaComputacao.enums.StatusAgendamentoPaciente;
import ulife.com.br.TCCEngenhariaComputacao.models.Agendamento;
import ulife.com.br.TCCEngenhariaComputacao.models.Convenio;

import java.time.LocalDate;

public class AgendamentoMapper {

    public static Agendamento fromDto(CadastroAgendamentoDto cadastroAgendamentoDto){
        return new Agendamento(null,cadastroAgendamentoDto.getEspecialidade(),cadastroAgendamentoDto.getMedico(),cadastroAgendamentoDto.getPaciente(),cadastroAgendamentoDto.getHorario(), LocalDate.now(), StatusAgendamentoMedico.AGUARDANDO_CONFIRMACAO_AGENDAMENTO.name(), StatusAgendamentoPaciente.AGUARDANDO_CONFIRMACAO_AGENDAMENTO.name());
    }

}
