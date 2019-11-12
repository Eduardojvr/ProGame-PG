package com.progame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.progame.dto.QuestaoVerdadeiroFalsoDTO;

public class QuestaoVerdadeiroFalsoDAO {
	public List<QuestaoVerdadeiroFalsoDTO> getQuestao(String level) throws Exception {
		
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
				"		where q.idTipoConteudo="+level);

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
}
