package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.progame.dto.QuestaoDTO;

public class QuestaoDAO {
	public List<QuestaoDTO> getQuestao(String level) throws Exception {
		
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();

		
		pstmt = db.prepareStatement(""
				+ "SELECT q.idQuestao, q.idTipoConteudo, q.questao, r.respCorreta, r.respIncorreta1, r.respIncorreta2,\n" + 
				"	   r.respIncorreta3, r.comentarioCorreta, r.comentarioErrado, l.nomeLinguagem, t.assunto, q.idTipoQuestao\n" + 
				"	FROM QUESTAO q\n" + 
				"		INNER JOIN resposta_Multipla_Escolha r\n" + 
				"			ON	q.idQuestao=r.idQuestao\n" + 
				"		INNER JOIN LINGUAGEM_PROGRAMACAO l\n" + 
				"			ON l.idLinguagem=q.idLinguagem\n" + 
				"		INNER JOIN TIPO_CONTEUDO t\n" + 
				"			ON t.idConteudo=q.idTipoConteudo\n" + 
				"    WHERE q.idTipoConteudo="+level);

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				QuestaoDTO questao = new QuestaoDTO();
				questao.setIdQuestao(result.getString("idQuestao"));
				questao.setIdTipoConteudo(result.getString("idTipoConteudo"));
				questao.setQuestao(result.getString("questao"));
				questao.setRespCorreta(result.getString("respCorreta"));
				questao.setRespIncorreta1(result.getString("respIncorreta1"));
				questao.setRespIncorreta2(result.getString("respIncorreta2"));
				questao.setRespIncorreta3(result.getString("respIncorreta3"));
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
}
