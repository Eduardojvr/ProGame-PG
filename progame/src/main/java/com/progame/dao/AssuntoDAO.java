package com.progame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.progame.entity.Assunto;

public class AssuntoDAO {
	
	public AssuntoDAO(){
		
	}
	
	public ArrayList<Assunto> todosAssuntos() throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		ArrayList<Assunto> arrayList = new ArrayList<Assunto>();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = db.prepareStatement("SELECT * FROM tipo_conteudo");
			result = pstmt.executeQuery();

			while (result.next()) {
				Assunto assunto = new Assunto();
				assunto.setAssunto(result.getString("assunto"));
				assunto.setDesConteudo(result.getString("desConteudo"));
				assunto.setIdConteudo(result.getString("idConteudo"));				

				arrayList.add(assunto);
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
	

}
