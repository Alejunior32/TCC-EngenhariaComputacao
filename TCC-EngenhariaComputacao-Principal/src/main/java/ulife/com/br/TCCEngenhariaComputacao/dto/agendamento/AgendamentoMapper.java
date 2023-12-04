package ulife.com.br.TCCEngenhariaComputacao.dto.agendamento;

import ulife.com.br.TCCEngenhariaComputacao.enums.StatusAgendamentoMedico;
import ulife.com.br.TCCEngenhariaComputacao.enums.StatusAgendamentoPaciente;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoConsulta;
import ulife.com.br.TCCEngenhariaComputacao.models.AgendamentoExame;

import java.time.LocalDate;

public class AgendamentoMapper {

    public static AgendamentoConsulta fromDto(CadastroAgendamentoDto cadastroAgendamentoDto){
        return new AgendamentoConsulta(null,cadastroAgendamentoDto.getEspecialidade(),cadastroAgendamentoDto.getMedico(),cadastroAgendamentoDto.getPaciente(),cadastroAgendamentoDto.getHorario(), LocalDate.now(), StatusAgendamentoMedico.AGUARDANDO_CONFIRMACAO_AGENDAMENTO.name(), StatusAgendamentoPaciente.AGUARDANDO_CONFIRMACAO_AGENDAMENTO.name());
    }

    public static AgendamentoExame fromDto(CadastroAgendamentoExameDto cadastroAgendamentoExameDto) {
        return new AgendamentoExame(null, cadastroAgendamentoExameDto.getExame(), cadastroAgendamentoExameDto.getPaciente(), cadastroAgendamentoExameDto.getHorario(), LocalDate.now(), StatusAgendamentoMedico.AGUARDANDO_CONFIRMACAO_AGENDAMENTO.name(), StatusAgendamentoPaciente.AGUARDANDO_CONFIRMACAO_AGENDAMENTO.name());
    }
}
