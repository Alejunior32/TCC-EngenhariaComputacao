create table usuario(
     id bigint not null auto_increment,
     login varchar(100) not null,
     senha varchar(255) not null,
     role varchar(100) not null,
     ativo boolean,
     primeiro_acesso boolean,
     primary key(id)
);

create table especialidade(
      id bigint not null auto_increment,
      titulo varchar(150),
      descricao varchar(255),
      primary key(id)
);

create table medico(
    id bigint not null auto_increment,
    especialidade_id bigint not null,
    usuario_id bigint not null,
    nome varchar(255) not null,
    crm integer not null,
    constraint fk_especialidade_id_medico foreign key (especialidade_id) references especialidade(id),
    constraint fk_usuario_id_medico foreign key (usuario_id) references usuario(id),
    primary key(id)
);

create table paciente(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    nome varchar(255),
    rg varchar(255),
    cpf varchar(255),
    imagem_paciente blob,
    data_nascimento DATE not null,
    constraint fk_usuario_id_paciente foreign key (usuario_id) references usuario(id),
    primary key(id)
);