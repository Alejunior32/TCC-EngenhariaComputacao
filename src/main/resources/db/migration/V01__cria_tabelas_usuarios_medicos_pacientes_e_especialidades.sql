create table usuarios(
     id bigint not null auto_increment,
     login varchar(100) not null,
     senha varchar(255) not null,
     role varchar(100) not null,
     ativo boolean,
     primeiro_acesso boolean,
     primary key(id)
);

create table especialidades(
      id bigint not null auto_increment,
      titulo varchar(150),
      descricao varchar(255),
      primary key(id)
);

create table medicos(
    id bigint not null auto_increment,
    especialidade_id bigint not null,
    usuario_id bigint not null,
    nome varchar(255) not null,
    crm integer not null,
    constraint fk_especialidade_id_medico foreign key (especialidade_id) references especialidades(id),
    constraint fk_usuario_id_medico foreign key (usuario_id) references usuarios(id),
    primary key(id)
);

create table pacientes(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    nome varchar(255),
    data_nascimento DATE not null,
    constraint fk_usuario_id_paciente foreign key (usuario_id) references usuarios(id),
    primary key(id)
);