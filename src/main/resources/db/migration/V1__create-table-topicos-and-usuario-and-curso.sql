CREATE TABLE usuarios (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correo_electronico varchar(100) not null unique,
    contrasena varchar(100) not null,
    activo tinyint,
    role varchar(100) not null,

    primary key(id)
);

CREATE TABLE cursos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,

    primary key(id)
);

CREATE TABLE topicos(
    id bigint not null auto_increment,
    id_usuario bigint not null,
    id_curso bigint not null,
    titulo varchar(100) not null,
    mensaje varchar(100) not null,
    fecha_creacion datetime not null,
    status tinyint not null,

    primary key(id),

    constraint fk_topicos_id_usuario foreign key(id_usuario) references usuarios(id),
    constraint fk_topicos_id_curso foreign key(id_curso) references cursos(id)
);