package com.progame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.progame.entity.Midia;

public class MidiaDAO {
	
	public MidiaDAO() {
		
	}
	

	public ArrayList<Midia> todasMidias() throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		ArrayList<Midia> arrayList = new ArrayList<Midia>();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = db.prepareStatement("SELECT * FROM tipo_midia");
			result = pstmt.executeQuery();

			while (result.next()) {
				Midia midia = new Midia();
				midia.setIdMidia(result.getString("idTipo"));
				midia.setNomeTipo(result.getString("nomeTipo"));				
				arrayList.add(midia);
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
