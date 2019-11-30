package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.progame.dto.QuestaoCodigoDTO;
import com.progame.dto.QuestaoDTO;
import com.progame.dto.QuestaoLacunaDTO;
import com.progame.dto.QuestaoVerdadeiroFalsoDTO;

public class QuestaoDAO {
	public List<QuestaoDTO> getQuestao(String level) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();


		pstmt = db.prepareStatement(""
				+ "SELECT q.idQuestao, q.idTipoConteudo, q.questao, r.respostaCorretaAlternativa, r.alternativa1, r.alternativa2,\n" + 
				"	   r.alternativa3, r.alternativa4, r.comentarioCorreta, r.comentarioErrado, l.nomeLinguagem, t.assunto, q.idTipoQuestao\n" + 
				"	FROM QUESTAO q\n" + 
				"		INNER JOIN resposta_multipla_escolha r\n" + 
				"			ON	q.idQuestao=r.idQuestao\n" + 
				"		INNER JOIN LINGUAGEM_PROGRAMACAO l\n" + 
				"			ON l.idLinguagem=q.idLinguagem\n" + 
				"		INNER JOIN TIPO_CONTEUDO t\n" + 
				"			ON t.idConteudo=q.idTipoConteudo\n" + 
				"    WHERE q.idTipoConteudo="+level+" order by rand()");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				QuestaoDTO questao = new QuestaoDTO();
				questao.setIdQuestao(result.getString("idQuestao"));
				questao.setIdTipoConteudo(result.getString("idTipoConteudo"));
				questao.setQuestao(result.getString("questao"));
				questao.setRespostaCorretaAlternativa(result.getString("respostaCorretaAlternativa"));
				questao.setAlternativa1(result.getString("alternativa1"));
				questao.setAlternativa2(result.getString("alternativa2"));
				questao.setAlternativa3(result.getString("alternativa3"));
				questao.setAlternativa4(result.getString("alternativa4"));
				questao.setComentarioCorreta(result.getString("comentarioCorreta"));
				questao.setComentarioErrado(result.getString("comentarioErrado"));
				questao.setNomeLinguagem(result.getString("nomeLinguagem"));
				questao.setAssunto(result.getString("assunto"));
				questao.setIdTipoQuestao(result.getString("idTipoQuestao"));
				listaQuestoes.add(questao);				
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return listaQuestoes;
	}

	public List<QuestaoVerdadeiroFalsoDTO> getQuestaoVF(String level) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		List<QuestaoVerdadeiroFalsoDTO> listaQuestoes = new ArrayList<QuestaoVerdadeiroFalsoDTO>();


		pstmt = db.prepareStatement("select q.idQuestao, q.idTipoQuestao, q.questao, vf.resposta, l.nomeLinguagem, t.assunto\n" + 
				"		from QUESTAO q\n" + 
				"        Inner join resposta_verdadeiro_falso vf\n" + 
				"			ON vf.idQuestao=q.idQuestao\n" + 
				"		Inner join LINGUAGEM_PROGRAMACAO l\n" + 
				"			ON l.idLinguagem=q.idLinguagem\n" + 
				"		Inner join TIPO_CONTEUDO t\n" + 
				"			ON t.idConteudo=q.idTipoConteudo\n" + 
				"		where q.idTipoConteudo="+level+" order by rand()");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				QuestaoVerdadeiroFalsoDTO questao = new QuestaoVerdadeiroFalsoDTO();
				questao.setIdQuestao(result.getString("idQuestao"));
				questao.setIdTipoQuestao(result.getString("idTipoQuestao"));				
				questao.setQuestao(result.getString("questao"));
				questao.setResposta(result.getString("resposta"));
				questao.setNomeLinguagem(result.getString("nomeLinguagem"));
				questao.setAssunto(result.getString("assunto"));
				listaQuestoes.add(questao);				
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return listaQuestoes;
	}


	public List<QuestaoLacunaDTO> getQuestaoLacuna(String level) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		List<QuestaoLacunaDTO> listaQuestoes = new ArrayList<QuestaoLacunaDTO>();


		pstmt = db.prepareStatement("	select q.idQuestao, q.idTipoQuestao, q.questao, la.resposta, la.respostaAlternativa, la.comentarioCorreta, la.comentarioIncorreta , l.nomeLinguagem, t.assunto\n" + 
				"		from QUESTAO q\n" + 
				"        Inner join resposta_lacuna la\n" + 
				"			ON la.idQuestao=q.idQuestao\n" + 
				"		Inner join LINGUAGEM_PROGRAMACAO l\n" + 
				"			ON l.idLinguagem=q.idLinguagem\n" + 
				"		Inner join TIPO_CONTEUDO t\n" + 
				"			ON t.idConteudo=q.idTipoConteudo\n" + 
				"		where q.idTipoConteudo="+level+" order by rand()");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				QuestaoLacunaDTO questao = new QuestaoLacunaDTO();
				questao.setIdQuestao(result.getString("idQuestao"));
				questao.setIdTipoQuestao(result.getString("idTipoQuestao"));				
				questao.setQuestao(result.getString("questao"));
				questao.setResposta(result.getString("resposta"));
				questao.setRespostaAlternativa(result.getString("respostaAlternativa"));
				questao.setComentarioCorreta(result.getString("comentarioCorreta"));
				questao.setComentarioIncorreta(result.getString("comentarioIncorreta"));
				questao.setNomeLinguagem(result.getString("nomeLinguagem"));
				questao.setAssunto(result.getString("assunto"));
				listaQuestoes.add(questao);		
				
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return listaQuestoes;
	}
	

	public List<QuestaoCodigoDTO> getQuestaoCodigo(String level) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		List<QuestaoCodigoDTO> listaQuestoes = new ArrayList<QuestaoCodigoDTO>();


		pstmt = db.prepareStatement("select q.idQuestao, q.idTipoConteudo, q.idLinguagem, q.idTipoQuestao, q.questao,\n" + 
				"	   rc.resposta, l.nomeLinguagem, t.assunto \n" + 
				"from questao q\n" + 
				"	inner join resposta_codigo rc\n" + 
				"			on rc.idQuestao=q.idQuestao\n" + 
				"	Inner join LINGUAGEM_PROGRAMACAO l\n" + 
				"			ON l.idLinguagem=q.idLinguagem\n" + 
				"	inner join TIPO_CONTEUDO t\n" + 
				"			ON t.idConteudo=q.idTipoConteudo\n" + 
				"	where q.idTipoConteudo="+level+" order by rand();");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				QuestaoCodigoDTO questao = new QuestaoCodigoDTO();
				questao.setIdQuestao(result.getString("idQuestao"));
				questao.setIdTipoConteudo(result.getString("idTipoConteudo"));
				questao.setIdLinguagem(result.getString("idLinguagem"));
				questao.setIdTipoQuestao(result.getString("idTipoQuestao"));
				questao.setQuestao(result.getString("questao"));
				questao.setResposta(result.getString("resposta"));
				questao.setNomeLinguagem(result.getString("nomeLinguagem"));
				questao.setAssunto(result.getString("assunto"));
				listaQuestoes.add(questao);		
				
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return listaQuestoes;
	}
}
