create table horarios(
    id bigint not null auto_increment,
    horaMinuto date not null,
    primary key(id)
);

create table agendamentos(
    id bigint not null auto_increment,
    especialidade_id bigint not null,
    medico_id bigint not null,
    paciente_id bigint not null,
    horario_id bigint not null,
    dataConsulta time not null,
    constraint fk_especialidade_id_agendamento foreign key (especialidade_id) references especialidades(id),
    constraint fk_medico_id_agendamento foreign key (medico_id) references medicos(id),
    constraint fk_paciente_id_agendamento foreign key (paciente_id) references pacientes(id),
    constraint fk_horario_id_agendamento foreign key (horario_id) references horarios(id),
    primary key(id)
);
