-- --------     << agenda >>     ------------
-- 
--                    SCRIPT PROCEDURAL (DDL)
-- 
-- date Criacao ...........: 01/11/2019
-- Autor(es) ..............: Eduardo Júnio Veloso Rodrigues
-- Banco de Dados .........: MySql
-- Base de Dados(nome) ....: agenda
-- 
-- date Ultima Alteracao ..: 01/11/2019
--   => Cria funções e procedimentos
--
-- PROJETO => 01 Base de Dados
--         => 04 Tabelas
--         => 03 Funções	 
--         => 01 Procedimento
-- -----------------------------------------------------------------
use agenda;

-- 1) Crie uma função que conta quantas tuplas tem na tabela PESSOA.
CREATE FUNCTION conta_tupla()
RETURNS INT deterministic
RETURN (select count(*)  from pessoa);

select conta_tupla() AS 'TOTAL DE TUPLAS';

--  2) Elabore um procedimento que conte quantos telefones tem uma pessoa específica.
delimiter $$
create procedure conta_telefone()
begin
	select p.nome, count(f.idPessoa) as 'Quantidade de telefones'
		from pessoa p, telefone f
    where p.idPessoa=2 and f.idPessoa=2;
end $$
delimiter ;

call conta_telefone;

-- 3) Crie uma função que mostre a quantidade de produtos específico que será solicitado pelo nome do produto.
create function qtde_produto(nomeProduto varchar(30))
RETURNS INT deterministic
RETURN (SELECT p.qtde 
			FROM produtos p
		WHERE p.nome like nomeProduto);

select qtde_produto('cerveja') AS 'QTDE DE PRODUTOS';

-- 4) Faça uma função que calcule para um produto específico a quantidade financeira em estoque que está
-- armazenado nessa base de dados (total em valor financeiro R$).
create function valor_em_estoque(nomeProduto varchar(30))
RETURNS INT deterministic
RETURN (SELECT p.qtde * p.preco
			FROM produtos p
		WHERE p.nome like nomeProduto);

select valor_em_estoque('cerveja') AS 'Valor total em estoque (R$)';

-- -----------------------------------------

-- Apaga Funções e procedimentos
-- drop function conta_tupla;
-- drop procedure conta_telefone;
-- drop function qtde_produto;
-- drop function valor_em_estoque;
