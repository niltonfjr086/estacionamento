/**
 * Author:  main
 * Created: 09/07/2017
 */

drop database linuxpark;

create database linuxpark;

use linuxpark;

create table if not exists linuxpark.tb_tipoveiculo (
id bigint primary key auto_increment,
descricao varchar(45) not null unique,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists linuxpark.tb_cor (
id bigint primary key auto_increment,
descricao varchar(45) not null unique,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists linuxpark.tb_marca (
id bigint primary key auto_increment,
descricao varchar(45) not null unique,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists linuxpark.tb_modelo (
id bigint primary key auto_increment,
id_marca bigint not null,
descricao varchar(45) not null unique,
data_inclusao datetime not null,
data_remocao datetime null,

    foreign key(id_marca) references linuxpark.tb_marca(id)
);

create table if not exists linuxpark.tb_veiculo (
id bigint primary key auto_increment,
placa varchar(45) not null unique,
id_tipoveiculo bigint not null,
id_cor bigint not null,
id_modelo bigint not null,
data_inclusao datetime not null,
data_remocao datetime null,

    foreign key(id_tipoveiculo) references linuxpark.tb_tipoveiculo(id),
    foreign key(id_cor) references linuxpark.tb_cor(id),
    foreign key (id_modelo) references linuxpark.tb_modelo(id)
);

create table if not exists linuxpark.tb_tipovaga (
id bigint primary key auto_increment,
descricao varchar(50) not null unique,
preco_unitario float not null,
quantidade int not null,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists linuxpark.tb_estaciona (
id bigint primary key auto_increment,
id_veiculo bigint not null,
id_tipo_vaga bigint not null,
preco_total float null,
data_entrada datetime not null,
data_saida datetime null,

    foreign key (id_veiculo) references linuxpark.tb_veiculo(id),
    foreign key (id_tipo_vaga) references linuxpark.tb_tipovaga(id)
);


-- SELECT * FROM linuxpark.tb_tipoveiculo;
-- SELECT * FROM linuxpark.tb_cor;
-- SELECT * FROM linuxpark.tb_marca;
-- SELECT * FROM linuxpark.tb_modelo;
-- SELECT * FROM linuxpark.tb_veiculo;
-- SELECT * FROM linuxpark.tb_tipovaga;
-- SELECT * FROM linuxpark.tb_estaciona;