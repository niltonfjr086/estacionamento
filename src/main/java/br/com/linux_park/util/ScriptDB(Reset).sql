/**
 * Author:  Leandro P., Nilton
 * Created: 15/07/2017
 */

drop database if exists linuxpark;

create database linuxpark;

use linuxpark;

create table if not exists linuxpark.tb_tipovaga (
id bigint primary key auto_increment,
descricao varchar(50) not null unique,
preco_unitario float not null,
quantidade int not null,
data_inclusao datetime not null,
data_remocao datetime null
);

create table if not exists linuxpark.tb_tipoveiculo (
id bigint primary key auto_increment,
descricao varchar(45) not null unique,
id_tipo_vaga bigint not null,  
data_inclusao datetime not null,
data_remocao datetime null,

    foreign key (id_tipo_vaga) references tb_tipovaga(id)
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
id_tipoveiculo bigint not null,
id_marca bigint not null,
descricao varchar(45) not null unique,
data_inclusao datetime not null,
data_remocao datetime null,

    foreign key(id_marca) references linuxpark.tb_marca(id),
    foreign key(id_tipoveiculo) references linuxpark.tb_tipoveiculo(id)
);

create table if not exists linuxpark.tb_veiculo (
id bigint primary key auto_increment,
placa varchar(45) not null unique,
id_modelo bigint not null,
id_cor bigint not null,
data_inclusao datetime not null,
data_remocao datetime null,

    foreign key(id_cor) references linuxpark.tb_cor(id),
    foreign key (id_modelo) references linuxpark.tb_modelo(id)
);



create table if not exists linuxpark.tb_estaciona (
id bigint primary key auto_increment,
id_veiculo bigint not null,
valor_un float not null,
tolerancia int DEFAULT 15,
preco_total float null,
data_entrada datetime not null,
data_saida datetime null,

    foreign key (id_veiculo) references linuxpark.tb_veiculo(id)
);

-- INSERT INTO linuxpark.tb_estaciona(id_veiculo, valor_un) VALUES(1, 2.0);

-- SELECT * FROM linuxpark.tb_tipovaga;
-- SELECT * FROM linuxpark.tb_tipoveiculo;
-- SELECT * FROM linuxpark.tb_cor;
-- SELECT * FROM linuxpark.tb_marca;
-- SELECT * FROM linuxpark.tb_modelo;
-- SELECT * FROM linuxpark.tb_veiculo;
-- SELECT * FROM linuxpark.tb_estaciona;


--  SELECT * FROM linuxpark.tb_veiculo
--                 WHERE linuxpark.tb_veiculo.data_remocao IS NULL
--                  AND LOWER(linuxpark.tb_veiculo.placa) = 'psl-4977';


-- SELECT IF(
-- (SELECT linuxpark.tb_estaciona.id FROM linuxpark.tb_estaciona 
-- WHERE LOWER(linuxpark.tb_estaciona.id) = 1)
--  IS NOT NULL, TRUE, FALSE);
-- 
-- 
-- SELECT IF(
-- (SELECT MIN(linuxpark.tb_veiculo.id)  FROM linuxpark.tb_veiculo 
-- WHERE linuxpark.tb_veiculo.placa = 'PSL-4977')
--              IS NOT NULL , TRUE , FALSE);
-- 
-- SELECT linuxpark.tb_estaciona.id , linuxpark.tb_veiculo.id
-- FROM linuxpark.tb_estaciona 
-- INNER JOIN linuxpark.tb_veiculo
-- ON linuxpark.tb_estaciona.id_veiculo = linuxpark.tb_veiculo.id
-- ORDER BY linuxpark.tb_estaciona.id
-- 
-- 
-- SELECT MIN(linuxpark.tb_estaciona.id)
-- FROM linuxpark.tb_estaciona 
-- INNER JOIN linuxpark.tb_veiculo
-- ON linuxpark.tb_estaciona.id_veiculo = linuxpark.tb_veiculo.id
-- WHERE linuxpark.tb_veiculo.id = 2 
-- AND linuxpark.tb_estaciona.data_saida IS NULL
-- ORDER BY linuxpark.tb_estaciona.id


-- SELECT DEFAULT(linuxpark.tb_estaciona.tolerancia) 
-- FROM linuxpark.tb_estaciona 
-- order by linuxpark.tb_estaciona.tolerancia desc LIMIT 1;
-- 
-- ALTER TABLE linuxpark.tb_estaciona
-- MODIFY linuxpark.tb_estaciona.tolerancia int default 80;