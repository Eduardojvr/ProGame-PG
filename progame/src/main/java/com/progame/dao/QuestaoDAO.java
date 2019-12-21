package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.progame.dto.DesafiovsDTO;
import com.progame.dto.QuestaoCodigoDTO;
import com.progame.dto.QuestaoDTO;
import com.progame.dto.QuestaoLacunaDTO;
import com.progame.dto.QuestaoTodasDTO;
import com.progame.dto.QuestaoVerdadeiroFalsoDTO;
import com.progame.entity.Questao;
import com.progame.entity.RespostaLacuna;
import com.progame.entity.RespostaMultipla;
import com.progame.entity.RespostaVF;
import com.progame.entity.TipoQuestao;

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
	
	public ArrayList<QuestaoTodasDTO> getTodasQuestoes(String level) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList<QuestaoTodasDTO> listaQuestoes = new ArrayList<QuestaoTodasDTO>();


		pstmt = db.prepareStatement("select q.idQuestao, q.idTipoConteudo, q.idTipoQuestao, q.questao, rm.alternativa1,\n" + 
				"	   rm.alternativa2, rm.alternativa3, rm.alternativa4, rm.respostaCorretaAlternativa,\n" + 
				"	   rm.comentarioCorreta as comentarioCorretaMultipla, rm.comentarioErrado, rl.resposta as respostaLacuna, rl.respostaAlternativa,\n" + 
				"       rl.comentarioCorreta, rl.comentarioIncorreta, rvf.resposta, lp.nomeLinguagem 	\n" + 
				"       from Questao q\n" + 
				"			left JOIN resposta_multipla_escolha rm\n" + 
				"				ON rm.idQuestao=q.idQuestao\n" + 
				"			left JOIN resposta_lacuna rl\n" + 
				"				ON rl.idQuestao=q.idQuestao  \n" + 
				"			left JOIN resposta_verdadeiro_falso rvf\n" + 
				"				ON rvf.idQuestao=q.idQuestao\n" + 
				"			inner join linguagem_programacao lp\n" + 
				"				ON lp.idLinguagem=q.idLinguagem\n" + 
				"		where q.idTipoConteudo="+level+"\n" + 
				"		order by rand();");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				QuestaoTodasDTO questao = new QuestaoTodasDTO();
				questao.setIdQuestao(result.getString("idQuestao"));
				questao.setIdTipoConteudo(result.getString("idTipoConteudo"));
				questao.setIdTipoQuestao(result.getString("idTipoQuestao"));
				questao.setQuestao(result.getString("questao"));
				questao.setAlternativa1(result.getString("alternativa1"));
				questao.setAlternativa2(result.getString("alternativa2"));
				questao.setAlternativa3(result.getString("alternativa3"));
				questao.setAlternativa4(result.getString("alternativa4"));
				questao.setRespostaCorretaAlternativa(result.getString("respostaCorretaAlternativa"));
				questao.setComentarioCorretaMultipla(result.getString("comentarioCorretaMultipla"));
				questao.setComentarioErrado(result.getString("comentarioErrado"));
				questao.setRespostaLacuna(result.getString("respostaLacuna"));
				questao.setRespostaAlternativa(result.getString("respostaAlternativa"));
				questao.setComentarioCorreta(result.getString("comentarioCorreta"));
				questao.setComentarioIncorreta(result.getString("comentarioIncorreta"));
				questao.setResposta(result.getString("resposta"));
				questao.setNomeLinguagem(result.getString("nomeLinguagem"));
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
	
	public ArrayList<TipoQuestao> getTipoQuestao() throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList<TipoQuestao> listaQuestoes = new ArrayList<TipoQuestao>();


		pstmt = db.prepareStatement("select * from tipo_questao;");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				TipoQuestao questao = new TipoQuestao();
				questao.setIdTipo(result.getString("idTipo"));
				questao.setTipo(result.getString("tipo"));
			
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
	
	
	public boolean insertQuestao(Questao questao) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	

		sql.append("INSERT INTO questao ");
		sql.append(" ( ");
		sql.append(" idTipoConteudo, ");
		sql.append(" idLinguagem, ");
		sql.append(" idTipoQuestao, ");		
		sql.append(" questao ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, questao.getIdTipoConteudo());
			pstmt.setString(2, questao.getIdLinguagem());
			pstmt.setString(3, questao.getIdTipoQuestao());
			pstmt.setString(4, questao.getQuestao());
			pstmt.executeUpdate();
			
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;

	}
	
	public String getIdQuestao() throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		String id = null;


		pstmt = db.prepareStatement("SELECT idQuestao FROM questao ORDER BY idQuestao DESC limit 1;");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				id = result.getString("idQuestao");
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return id;
	}
	
	public boolean insertRespostaQuestaoVF(RespostaVF resposta) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	

		sql.append("INSERT INTO resposta_verdadeiro_falso ");
		sql.append(" ( ");
		sql.append(" idQuestao, ");
		sql.append(" resposta ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, getIdQuestao());
			pstmt.setString(2, resposta.getResposta());
			pstmt.executeUpdate();
			
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;

	}
	
	public boolean insertRespostaQuestaoLacuna(RespostaLacuna resposta) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	

		sql.append("INSERT INTO resposta_lacuna ");
		sql.append(" ( ");
		sql.append(" idQuestao, ");
		sql.append(" resposta, ");
		sql.append(" respostaAlternativa, ");
		sql.append(" comentarioCorreta, ");		
		sql.append(" comentarioIncorreta ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, getIdQuestao());
			pstmt.setString(2, resposta.getResposta());
			pstmt.setString(3, resposta.getRespostaAlternativa());
			pstmt.setString(4, resposta.getComentarioCorreta());
			pstmt.setString(5, resposta.getComentarioIncorreta());
			pstmt.executeUpdate();
			
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;

	}
	
	public boolean insertRespostaQuestaoMultipla(RespostaMultipla resposta) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	

		sql.append("INSERT INTO resposta_multipla_escolha ");
		sql.append(" ( ");
		sql.append(" idQuestao, ");
		sql.append(" alternativa1, ");
		sql.append(" alternativa2, ");
		sql.append(" alternativa3, ");
		sql.append(" alternativa4, ");
		sql.append(" respostaCorretaAlternativa, ");
		sql.append(" comentarioCorreta, ");
		sql.append(" comentarioErrado ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, getIdQuestao());
			pstmt.setString(2, resposta.getAlternativa1());
			pstmt.setString(3, resposta.getAlternativa2());
			pstmt.setString(4, resposta.getAlternativa3());
			pstmt.setString(5, resposta.getAlternativa4());
			pstmt.setString(6, resposta.getRespostaCorretaAlternativa());
			pstmt.setString(7, resposta.getComentarioCorreta());
			pstmt.setString(8, resposta.getComentarioErrado());
			pstmt.executeUpdate();
			
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;

	}
	
	
	
	
	
	
}



