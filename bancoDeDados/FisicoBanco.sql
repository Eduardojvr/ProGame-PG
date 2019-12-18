use heroku_7e0ed9b0e3f50c0;
create database heroku_7e0ed9b0e3f50c0;
create table LINGUAGEM_PROGRAMACAO(
	idLinguagem int not null auto_increment,
    nomeLinguagem varchar(20) not null,
    descLinguagem varchar(500) ,
    constraint linguagem_pk primary key(idLinguagem),
    constraint linguagem_uk unique (nomeLinguagem)
)engine=InnoDb auto_increment=1;

insert into LINGUAGEM_PROGRAMACAO values (1,'C', 'Linguagem de baixo nível.');


create table TIPO_MIDIA(
	idTipo int not null auto_increment,
    nomeTipo varchar(30) not null,
    constraint tipo_conteudo_pk primary key (idTipo),
    constraint tipo_conteudo_uk unique (nomeTipo)
)engine=innodb auto_increment=1;

insert into TIPO_MIDIA values (1, 'Video'), (2, 'Site');

create table TIPO_CONTEUDO(
	idConteudo int not null auto_increment,
    assunto varchar(30) not null,
    desConteudo varchar(300) not null,
    constraint tipo_conteudo_pk primary key (idConteudo),
    constraint tipo_conteudo_uk unique (assunto)
)ENGINE=INNODB auto_increment=1;

insert into TIPO_CONTEUDO values (1, 'Algoritmos', 'Sequência finita de ações executáveis que visam obter uma solução para um determinado tipo de problema.');

create table MATERIAL (
	idMaterial bigint not null auto_increment,
    idConteudo int not null,
    idTipoMidia int not null,
	urlMaterial varchar(200) not null,
    idLinguagem int not null,
    tituloMaterial varchar(200) not null,
    constraint material_pk primary key (idMaterial),
    constraint material_tipo_conteudo foreign key (idConteudo) references TIPO_CONTEUDO(idConteudo),
    constraint material_tipo_midia foreign key (idTipoMidia) references TIPO_MIDIA(idTipo),
	constraint material_linguagem_fk foreign key (idLinguagem) references LINGUAGEM_PROGRAMACAO(idLinguagem),    
    constraint material_uk unique (urlMaterial)
)engine=innodb auto_increment=1;
insert into MATERIAL values (1, 1, 1, 'https://www.youtube.com/watch?v=8mei6uVttho', 1, 'Introdução aos algoritmos');

create table TIPO_QUESTAO(
	idTipo int not null auto_increment,
    tipo varchar (100) not null,
    constraint tipo_questao_pk primary key (idTipo),
    constraint tipo_questao_uk unique (tipo)
)engine=innodb auto_increment=1;

insert into TIPO_QUESTAO values (1, 'Lacuna'), (2, 'Codigo'), (3,'V ou F'), (4, 'Múltipla escolha');
select * from tipo_questao;
create table QUESTAO(
	idQuestao bigint not null auto_increment,
    idTipoConteudo int not null,
    idLinguagem int not null,
    idTipoQuestao int not null,
	questao varchar(200) not null,
    constraint questao_pk primary key (idQuestao),
    constraint questao_tipo_questao_fk foreign key (idTipoQuestao) references TIPO_QUESTAO(idTipo),
    constraint questao_tipo_conteudo_fk foreign key (idTipoConteudo) references TIPO_CONTEUDO(idConteudo),
    constraint questao_linguagem foreign key (idLinguagem) references LINGUAGEM_PROGRAMACAO(idLinguagem),    
    constraint questao_uk unique (questao)
)engine=innodb auto_increment=1;
select * from questao;
desc questao;
alter table QUESTAO add column idTipoQuestao int not null;
alter table QUESTAO add constraint questao_tipo_questao_fk foreign key (idTipoQuestao) references TIPO_QUESTAO(idTipo);
drop table questao;
insert into QUESTAO values (1, 1, 1, 4,'É uma sequência finita de ações executáveis que visam obter uma solução para um determinado tipo de problema.');
insert into QUESTAO values (2, 1, 1, 4,'É uma "receita" para executar uma tarefa ou resolver algum problema');
insert into QUESTAO values (3, 1, 1, 3,'Um código escrito na linguagem c, é interpretado por um interpretador');

create table DISCIPLINA(
	codDisciplina bigint not null auto_increment,
    disciplina varchar(100) not null,
    siglaDisciplina varchar(10) not null,
    constraint disciplina_pk primary key (codDisciplina)
)engine=innodb auto_increment=1;

create table TIPO_PERFIL(
	idTipo int  not null auto_increment,
    perfil varchar(30) not null,
    constraint tipo_perfil_pk primary key (idTipo)
)ENGINE=innodb auto_increment=1;

insert into TIPO_PERFIL values (1, 'PROFESSOR'), ( 3, 'ALUNO'),(2, 'ADMINISTRADOR'),(4, 'MONITOR');

create table PERSONAGEM(
	idPersonagem int not null auto_increment,
    nomePersonagem varchar(30) not null,
    imgPersonagem varchar(200) not null,
    constraint personagem_pk primary key (idPersonagem),
    constraint personagem_uk unique (imgPersonagem)
)engine=innodb auto_increment=1;
insert into PERSONAGEM values (1, 'Iron Adm','/progame/src/main/webapp/resources/img/administrador');

create table USUARIO(
	nomeUsuario varchar(60) not null,
    matricula bigint (12) not null,
    senha varchar(200) not null,
    idTipoPerfil int not null,
    idPersonagem int,
    email varchar(100) not null,
    pontuacao int not null,
    level int not null,
    constraint usuario_pk primary key (matricula),
    constraint usuario_tipo_perfil_fk foreign key (idTipoPerfil) references TIPO_PERFIL(idTipo),
    constraint usuario_personagem_fk foreign key (idPersonagem) references PERSONAGEM(idPersonagem)
)engine=innodb auto_increment=1;
drop table login;
drop table USUARIO;
-- alter table usuario add column pontuacao int not null;
-- alter table usuario add column level int not null;


select * from usuario;
-- insert into USUARIO values ('Eduardo', '140168192', MD5('98125003'), 3, null, 'eduardo@gmail.com');
-- delete from usuario;
create table login(
	matriculaUsuario bigint(12) not null,
    dtHora datetime not null,
    constraint login_usario_fk foreign key (matriculaUsuario) references USUARIO(matricula)
)engine=innodb;

select * from usuario;

insert into personagem values 	(1, 'Aisha', '../resources/img/jogadores/aisha.png' ), 
								(2, 'Lince', '../resources/img/jogadores/Lince.png'), 
                                (3, 'Scar','../resources/img/jogadores/Scar.png'), 
                                (4,'Tymer' ,'../resources/img/jogadores/Tymer.png'), 
                                (5, 'Voxter','../resources/img/jogadores/Voxter.png'), 
                                (6, 'Amazona','../resources/img/jogadores/amazona.png');
insert into personagem values (7, 'N/D', '../resources/img/persona.png');

show tables;

select * from questao;
-- alter table questao add column ;
select * from TIPO_QUESTAO;

-- V ou F
create table resposta_verdadeiro_falso(
	idQuestao bigint not null,
    resposta varchar(10) not null,
	constraint resposta_verdadeiro_falso_fk foreign key (idQuestao) references QUESTAO(idQuestao)
)engine=innodb;
insert into resposta_verdadeiro_falso values (5, 'Verdadeiro');

-- Unica escolha
create table resposta_Multipla_Escolha(
	idQuestao bigint not null,
    respCorreta varchar(300) not null,
	respIncorreta1 varchar(300) not null,
    respIncorreta2 varchar(300) not null,
    respIncorreta3 varchar(300) not null,
    comentarioCorreta varchar(300) not null,
    comentarioErrado varchar(300) not null,
	constraint resposta_Multipla_Escolha_Questao_FK foreign key (idQuestao) references QUESTAO(idQuestao),
	constraint resposta_Multipla_Escolha_Questao_uk unique (idQuestao)
)engine=InnoDb;
drop table resposta_Multipla_Escolha;
insert into  resposta_Multipla_Escolha values (1, 'Algoritmo', 'Progamação', 'Computador', 'Linguagem', 'Parabéns jogador, resposta correta!', 'Resposta incorreta, essas características são dos algoritmos.');
insert into  resposta_Multipla_Escolha values (2, 'Algoritmo', 'Passo a Passo', 'Metodologia', 'Desenovlvimento', 'Parabéns jogador, resposta correta!', 'Resposta incorreta, essas características são dos algoritmos.');

SELECT q.idQuestao, q.idTipoConteudo, q.questao, r.respCorreta, r.respIncorreta1, r.respIncorreta2,
	   r.respIncorreta3, r.comentarioCorreta, r.comentarioErrado, l.nomeLinguagem, t.assunto, v.resposta
	FROM QUESTAO q
		left join resposta_verdadeiro_falso v 
			on v.idQuestao=q.idQuestao
		left join resposta_Multipla_Escolha r
			on r.idQuestao=q.idQuestao
		INNER JOIN LINGUAGEM_PROGRAMACAO l
			ON l.idLinguagem=q.idLinguagem
		INNER JOIN TIPO_CONTEUDO t
			ON t.idConteudo=q.idTipoConteudo
    WHERE q.idTipoConteudo=1;



	select q.idQuestao, q.idTipoQuestao, q.questao, vf.resposta, l.nomeLinguagem, t.assunto
		from QUESTAO q
        Inner join resposta_verdadeiro_falso vf
			ON vf.idQuestao=q.idQuestao
		Inner join LINGUAGEM_PROGRAMACAO l
			ON l.idLinguagem=q.idLinguagem
		Inner join TIPO_CONTEUDO t
			ON t.idConteudo=q.idTipoConteudo
		where q.idTipoConteudo=1;
        
        
        create table resposta_lacuna(
			idQuestao bigint not null,
            resposta varchar(100) not null,
            respostaAlternativa varchar(100) not null,
            comentarioCorreta varchar(300) not null,
            comentarioIncorreta varchar(300) not null,
            constraint resposta_lacuna_questao_fk foreign key (idQuestao) references QUESTAO(idQuestao)
        )engine=innodb;

insert into resposta_lacuna values (6, 'algoritmos', 'Algoritmo', 'Parabéns. Resposta correta!', 'Errado. Essas características são dos algoritmos!');
insert into QUESTAO values (default, 1, 1, 1,'Sequencia de passos para realização de uma tarefa. Essas características são dos ');
select * from QUESTAO;
insert into resposta_lacuna values (7, 'algoritmos', 'Algoritmo', 'Parabéns. Resposta correta!', 'Errado. Essas características são dos algoritmos!');
insert into QUESTAO values (default, 1, 1, 1,'Também conhecidos como "receitas" para a realização de uma tarefa  ');
update QUESTAO set idQuestao=6 where idQuestao=31;
insert into QUESTAO values (default, 1, 1, 3, 'Algoritmos só podem ser utilizados para resolver problemas simples');
insert into resposta_verdadeiro_falso values (6, 'Falso');
select * from resposta_verdadeiro_falso;

create table resposta_codigo(
	idQuestao bigint not null,
	resposta varchar(200) not null,
    constraint resposta_codigo_questao_fk foreign key (idQuestao) references QUESTAO(idQuestao)
)engine=innodb;
insert into resposta_Codigo values (7, '4');

select * from questao;

	select q.idQuestao, q.idTipoQuestao, q.questao, la.resposta, la.respostaAlternativa, la.comentarioCorreta, la.comentarioIncorreta , l.nomeLinguagem, t.assunto
		from QUESTAO q
        Inner join resposta_lacuna la
			ON la.idQuestao=q.idQuestao
		Inner join LINGUAGEM_PROGRAMACAO l
			ON l.idLinguagem=q.idLinguagem
		Inner join TIPO_CONTEUDO t
			ON t.idConteudo=q.idTipoConteudo
		where q.idTipoConteudo=1;

select * from resposta_Multipla_Escolha;
update resposta_Multipla_Escolha set respIncorreta3='Desenvolvimento' where idQuestao=2;
select * from questao where idQuestao=6;
select * from questao;
 insert into resposta_verdadeiro_falso values (3,'Falso'),(4, 'Falso');




select * from usuario;

-- ---------------------------------------------------------------------------------------------

-- create table LINGUAGEM_PROGRAMACAO(
-- 	idLinguagem int not null auto_increment,
--     nomeLinguagem varchar(20) not null,
--     descLinguagem varchar(500) ,
--     constraint linguagem_pk primary key(idLinguagem),
--     constraint linguagem_uk unique (nomeLinguagem)
-- )engine=InnoDb auto_increment=1;

-- insert into LINGUAGEM_PROGRAMACAO values (1,'C', 'Linguagem de baixo nível.');


-- create table TIPO_MIDIA(
-- 	idTipo int not null auto_increment,
--     nomeTipo varchar(30) not null,
--     constraint tipo_conteudo_pk primary key (idTipo),
--     constraint tipo_conteudo_uk unique (nomeTipo)
-- )engine=innodb auto_increment=1;

-- insert into TIPO_MIDIA values (1, 'Video'), (2, 'Site');

-- create table TIPO_CONTEUDO(
-- 	idConteudo int not null auto_increment,
--     assunto varchar(30) not null,
--     desConteudo varchar(300) not null,
--     constraint tipo_conteudo_pk primary key (idConteudo),
--     constraint tipo_conteudo_uk unique (assunto)
-- )ENGINE=INNODB auto_increment=1;

-- insert into TIPO_CONTEUDO values (1, 'Algoritmos', 'Sequência finita de ações executáveis que visam obter uma solução para um determinado tipo de problema.');

-- create table MATERIAL (
-- 	idMaterial bigint not null auto_increment,
--     idConteudo int not null,
--     idTipoMidia int not null,
-- 	urlMaterial varchar(200) not null,
--     idLinguagem int not null,
--     tituloMaterial varchar(200) not null,
--     constraint material_pk primary key (idMaterial),
--     constraint material_tipo_conteudo foreign key (idConteudo) references TIPO_CONTEUDO(idConteudo),
--     constraint material_tipo_midia foreign key (idTipoMidia) references TIPO_MIDIA(idTipo),
-- 	constraint material_linguagem_fk foreign key (idLinguagem) references LINGUAGEM_PROGRAMACAO(idLinguagem),    
--     constraint material_uk unique (urlMaterial)
-- )engine=innodb auto_increment=1;

-- insert into material values(1,1,1,'https://www.youtube.com/watch?v=8mei6uVttho',1);
-- insert into material values(2,1,1,'https://www.youtube.com/watch?v=M2Af7gkbbro&t=1s',1, 'Algoritmos');
-- update material set urlMaterial="https://www.youtube.com/watch?v=M2Af7gkbbro" where idMaterial=2;
-- select * from material;
-- select * from material;
-- select m.idMaterial, m.idTipoMidia, m.urlMaterial, m.tituloMaterial, lp.idLinguagem, lp.nomeLinguagem,
-- 	   lp.descLinguagem, tm.nomeTipo, tm.idTipo, tc.idConteudo,tc.assunto, tc.desConteudo	 from MATERIAL m
-- 	inner join TIPO_CONTEUDO tc
-- 		on tc.idConteudo = m.idConteudo
-- 	inner join TIPO_MIDIA tm
-- 		on tm.idTipo=m.idTipoMidia
-- 	inner join LINGUAGEM_PROGRAMACAO lp
-- 		on lp.idLinguagem=m.idLinguagem
-- where m.idTipoMidia=1 and tc.idConteudo=1 and lp.idLinguagem=1 ;


select * from tipo_questao; 

select q.idQuestao, q.idTipoConteudo, q.idLinguagem, q.idTipoQuestao, q.questao,
	   rc.resposta, l.nomeLinguagem, t.assunto 
	from questao q
	inner join resposta_codigo rc
			on rc.idQuestao=q.idQuestao
	Inner join LINGUAGEM_PROGRAMACAO l
			ON l.idLinguagem=q.idLinguagem
	inner join TIPO_CONTEUDO t
			ON t.idConteudo=q.idTipoConteudo
	where q.idTipoConteudo=1;

select * from questao;    
     
insert into questao values(7, 1,1,2,'Orcs são guerreiros que não se dão bem com números. Escreva um código que seja capaz de somar dois valores a e b, onde a=3 e b=1');

use heroku_7e0ed9b0e3f50c0;

 create table resposta_multipla_escolha(
     idQuestao bigint not null,
     alternativa1 varchar(300) not null,
     alternativa2 varchar(300)not null,
     alternativa3 varchar(300) not null,
     alternativa4 varchar(300) not null,
     respostaCorretaAlternativa int not null,
     comentarioCorreta varchar(150) not null,
     comentarioErrado varchar(150) not null,
     constraint resposta_multipla_escolha_questao foreign key (idQuestao) references QUESTAO(idQuestao)
)engine=InnoDB;
insert into resposta_multipla_escolha values(1, 'Computador', 'Algoritmos', 'Cpu', 'Nenhuma das anteriores', 2, 'Parabéns!', 'Errado');
select * from QUESTAO;

desc resposta_multipla_escolha;

use heroku_7e0ed9b0e3f50c0;
select * from linguagem_programacao;

select urlMaterial, tituloMaterial, assunto, desConteudo, nomeTipo, nomeLinguagem from material m
	inner join tipo_conteudo tc
		on tc.idConteudo=m.idConteudo
	inner join tipo_midia tm
		on tm.idTipo=m.idTipoMidia
	inner join linguagem_programacao lp
		on lp.idLinguagem=m.idLinguagem;

select * from tipo_conteudo;
select * from tipo_midia;
select * from linguagem_programacao;

desc material;

create table DESAFIO (
	idDesafio bigint not null auto_increment,
	matriculaDesafiante bigint(12) not null,
    matriculaDesafiado bigint(12) not null,
    tituloDesafio varchar(100) not null,
    desafio varchar(200) not null,
    respostaDesafiado varchar(300) default null, 
    avaliacao varchar(300),
    resultado enum('C', 'E', 'null') default null,    
    constraint desafio_pk primary key (idDesafio),
    constraint desafio_usuario_desafiante foreign key (matriculaDesafiante) references USUARIO(matricula),
    constraint desafio_usuario_desafiado foreign key (matriculaDesafiado) references USUARIO(matricula)
)engine=innodb auto_increment=1;

desc desafio;

select * from desafio;

update desafio set resultado=null where idDesafio=151;

update desafio set resultado='c', avaliacao='boa' where idDesafio=1;
