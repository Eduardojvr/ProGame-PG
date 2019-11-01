package com.progame.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.progame.entity.*;

public class LinguagemProgramacaoDAO {

	public LinguagemProgramacaoDAO() {

	}

	public List<LinguagemProgramacao> todasLinguagens() throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		List<LinguagemProgramacao> arrayList = new ArrayList<LinguagemProgramacao>();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = db.prepareStatement("SELECT * FROM LINGUAGEM_PROGRAMACAO");
			result = pstmt.executeQuery();

			while (result.next()) {
				LinguagemProgramacao linguagem = new LinguagemProgramacao();
				linguagem.setIdLinguagem(result.getString("idLinguagem"));
				linguagem.setNomeLinguagem(result.getString("nomeLinguagem"));				
				linguagem.setDescLinguagem(result.getString("descLinguagem"));
				arrayList.add(linguagem);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return arrayList;
	}
	
	public boolean insereLinguagem(LinguagemProgramacao linguagem) throws Exception {
		Connection db = ConnectionManager.getDBConnection();

		PreparedStatement pstmt = null;
		
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO LINGUAGEM_PROGRAMACAO ");
		sql.append(" ( ");
		sql.append(" nomeLinguagem, ");
		sql.append(" descLinguagem ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?);");


		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, linguagem.getNomeLinguagem());
			pstmt.setString(2, linguagem.getDescLinguagem());
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return true;
	}


}
