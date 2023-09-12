CREATE TABLE token_redefinir_senha(
    id BIGINT NOT NULL auto_increment,
    token CHAR(36) NOT NULL,
    data_expiracao DATE NOT NULL,
    usuario_id BIGINT NOT NULL,
    constraint fk_usuario_id foreign key (usuario_id) references usuarios(id),
    PRIMARY KEY(id)
);


