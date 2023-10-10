create table convenio(
    id bigint not null auto_increment,
    nome_convenio varchar(255),
    plano varchar(255),
    numero_identificacao varchar(255),
    data_inicio DATE not null,
    data_termino DATE not null,
    primary key(id)
);

alter table paciente add column convenio_id bigint;

alter table paciente add constraint fk_paciente_convenio foreign key (convenio_id) references convenio(id);
