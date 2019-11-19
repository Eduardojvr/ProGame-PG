package com.progame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.progame.dto.MaterialDTO;

public class MaterialDAO {
	public ArrayList<MaterialDTO> getMaterial(String idTipoMidia, String idConteudo, String idLinguagem) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList <MaterialDTO> material = new ArrayList<MaterialDTO>();

		pstmt = db.prepareStatement("select m.idMaterial, m.idTipoMidia, m.urlMaterial, m.tituloMaterial, lp.idLinguagem, lp.nomeLinguagem,\n" + 
				"	   lp.descLinguagem, tm.nomeTipo, tm.idTipo, tc.idConteudo,tc.assunto, tc.desConteudo	 from MATERIAL m\n" + 
				"	inner join TIPO_CONTEUDO tc\n" + 
				"		on tc.idConteudo = m.idConteudo\n" + 
				"	inner join TIPO_MIDIA tm\n" + 
				"		on tm.idTipo=m.idTipoMidia\n" + 
				"	inner join LINGUAGEM_PROGRAMACAO lp\n" + 
				"		on lp.idLinguagem=m.idLinguagem\n" + 
				"where m.idTipoMidia="+idTipoMidia+" and tc.idConteudo="+idConteudo+" and lp.idLinguagem="+idLinguagem+";");
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				MaterialDTO m = new MaterialDTO();
				m.setIdMaterial(result.getString("idMaterial"));
				m.setIdTipoMidia(result.getString("idTipoMidia"));
				m.setUrlMaterial(result.getString("urlMaterial"));
				m.setTituloMaterial(result.getString("tituloMaterial"));
				m.setIdLinguagem(result.getString("idLinguagem"));
				m.setNomeLinguagem(result.getString("nomeLinguagem"));
				m.setDescLinguagem(result.getString("descLinguagem"));
				m.setNomeTipo(result.getString("nomeTipo"));
				m.setIdTipo(result.getString("idTipo"));
				m.setIdConteudo(result.getString("idConteudo"));
				m.setAssunto(result.getString("assunto"));
				m.setDesConteudo(result.getString("desConteudo"));
				material.add(m);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return material;
	}
}
