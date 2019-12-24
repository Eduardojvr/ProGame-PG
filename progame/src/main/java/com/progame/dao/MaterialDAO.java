package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.progame.dto.MaterialDTO;
import com.progame.dto.curteDTO;
import com.progame.entity.Usuario;


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
				m.setTotal("0");
				m.setTotalDislike("0");

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
	
	public ArrayList<MaterialDTO> getAllMaterial() throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList <MaterialDTO> material = new ArrayList<MaterialDTO>();

		pstmt = db.prepareStatement("select urlMaterial, tituloMaterial, assunto, desConteudo, nomeTipo, nomeLinguagem from material m\n" + 
				"inner join tipo_conteudo tc\n" + 
				"	on tc.idConteudo=m.idConteudo\n" + 
				"inner join tipo_midia tm\n" + 
				"	on tm.idTipo=m.idTipoMidia\n" + 
				"inner join linguagem_programacao lp\n" + 
				"	on lp.idLinguagem=m.idLinguagem;");
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				MaterialDTO m = new MaterialDTO();
				m.setUrlMaterial(result.getString("urlMaterial"));
				m.setTituloMaterial(result.getString("tituloMaterial"));
				m.setAssunto(result.getString("assunto"));
				m.setDesConteudo(result.getString("desConteudo"));
				m.setNomeTipo(result.getString("nomeTipo"));
				m.setNomeLinguagem(result.getString("nomeLinguagem"));
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
	
	public boolean insereMaterial(MaterialDTO material) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO material ");
		sql.append(" ( ");
		sql.append(" idConteudo, ");
		sql.append(" idTipoMidia, ");		
		sql.append(" urlMaterial, ");
		sql.append(" idLinguagem, ");
		sql.append(" tituloMaterial ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, material.getIdConteudo());
			pstmt.setString(2, material.getIdTipoMidia());
			pstmt.setString(3, material.getUrlMaterial());
			pstmt.setString(4, material.getIdLinguagem());
			pstmt.setString(5, material.getTituloMaterial());

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;

	}
	
	public boolean controlaCurtida(String idMaterial, String matricula, String tipo) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;
		boolean isOk = false;

		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO curte ");
		sql.append(" ( ");
		sql.append(" idMaterial, ");
		sql.append(" matricula, ");	
		sql.append(" tipoCurtida ");		
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, idMaterial);
			pstmt.setString(2, matricula);
			pstmt.setString(3, tipo);

			pstmt.executeUpdate();
			isOk = true;

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return isOk;

	}
	
	public ArrayList<curteDTO> getAllLike() throws Exception{
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		ArrayList<curteDTO> allLike = new ArrayList<curteDTO>();
		
		pstmt = db.prepareStatement("select m.idMaterial, count(c.idMaterial) as total , c.tipoCurtida from material m left join curte c on m.idMaterial=c.idMaterial where c.tipoCurtida='l' group by m.idMaterial");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				curteDTO m = new curteDTO();
				m.setIdMaterial(result.getString("idMaterial"));
				if(result.getString("total") == null) {
					m.setTotal("0");					
				} else {
					m.setTotal(result.getString("total"));					
				}
				allLike.add(m);
			}
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			} else {
				db.close();
			}
		}
		
		return allLike;
	}
	
	public ArrayList<curteDTO> getAllDislike() throws Exception{
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		ArrayList<curteDTO> allDislike = new ArrayList<curteDTO>();
		
		pstmt = db.prepareStatement("select m.idMaterial, count(c.idMaterial) as total , c.tipoCurtida from material m left join curte c on m.idMaterial=c.idMaterial where c.tipoCurtida='d' group by m.idMaterial");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				curteDTO m = new curteDTO();
				m.setIdMaterial(result.getString("idMaterial"));
				if(result.getString("total") == null) {
					m.setTotal("0");					
				} else {
					m.setTotal(result.getString("total"));					
				}
				
				allDislike.add(m);
			}
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			} else {
				db.close();
			}
		}
		
		return allDislike;
	}
	
}

