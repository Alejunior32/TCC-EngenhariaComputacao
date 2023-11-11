create table horario(
    id bigint not null auto_increment,
    horaMinuto date not null,
    primary key(id)
);

create table agendamento(
    id bigint not null auto_increment,
    especialidade_id bigint not null,
    medico_id bigint not null,
    paciente_id bigint not null,
    horario_id bigint not null,
    data_consulta time not null,
    status_agendamento_medico varchar(150) not null,
    status_agendamento_paciente varchar(150) not null,
    constraint fk_especialidade_id_agendamento foreign key (especialidade_id) references especialidade(id),
    constraint fk_medico_id_agendamento foreign key (medico_id) references medico(id),
    constraint fk_paciente_id_agendamento foreign key (paciente_id) references paciente(id),
    constraint fk_horario_id_agendamento foreign key (horario_id) references horario(id),
    primary key(id)
);
