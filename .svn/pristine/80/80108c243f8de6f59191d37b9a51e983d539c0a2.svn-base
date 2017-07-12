/**
 * Author:  main
 * Created: 09/07/2017
 */

drop database linuxpark;

create database linuxpark;

use linuxpark;

create table if not exists tb_tipoveiculo (
id bigint primary key auto_increment,
descricao varchar(45) not null,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists tb_cor (
id bigint primary key auto_increment,
descricao varchar(45) not null,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists tb_marca (
id bigint primary key auto_increment,
descricao varchar(45) not null,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists tb_modelo (
id bigint primary key auto_increment,
id_marca bigint not null,
descricao varchar(45) not null,
data_inclusao datetime not null,
data_remocao datetime null,

    foreign key(id_marca) references tb_marca(id)
);

create table if not exists tb_veiculo (
id bigint primary key auto_increment,
placa varchar(45) not null,
id_tipoveiculo bigint not null,
id_cor bigint not null,
id_modelo bigint not null,
data_inclusao datetime not null,
data_remocao datetime null,

    foreign key(id_tipoveiculo) references tb_tipoveiculo(id),
    foreign key(id_cor) references tb_cor(id),
    foreign key (id_modelo) references tb_modelo(id)
);

create table if not exists tb_tipovaga (
id bigint primary key auto_increment,
descricao varchar(50) not null,
preco_unitario float not null,
quantidade int not null,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists tb_estaciona (
id bigint primary key auto_increment,
id_veiculo bigint not null,
id_tipo_vaga bigint not null,
data_entrada datetime not null,
data_saida datetime null,
-- preco_unitario float not null,
preco_total float null,

    foreign key (id_veiculo) references tb_veiculo(id),
    foreign key (id_tipo_vaga) references tb_tipovaga(id)
);
