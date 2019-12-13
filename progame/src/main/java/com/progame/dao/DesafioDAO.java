package com.progame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.progame.dto.DesafiovsDTO;

public class DesafioDAO {

	
	public boolean insertDesafioVs1(DesafiovsDTO desafio) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	

		sql.append("INSERT INTO desafio ");
		sql.append(" ( ");
		sql.append(" matriculaDesafiante, ");
		sql.append(" matriculaDesafiado, ");
		sql.append(" tituloDesafio, ");		
		sql.append(" desafio, ");
		sql.append(" respostaDesafiado, ");
		sql.append(" resultado ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, desafio.getMatriculaDesafiante());
			pstmt.setString(2, desafio.getMatriculaDesafiado());
			pstmt.setString(3, desafio.getTituloDesafio());
			pstmt.setString(4, desafio.getDesafio());
			pstmt.setString(5, null);
			pstmt.setString(6, null);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;

	}
	
	public ArrayList<DesafiovsDTO> getAllDesafio(String matriculaDesafiado) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList <DesafiovsDTO> desafios = new ArrayList<DesafiovsDTO>();
		
		pstmt = db.prepareStatement("select * from desafio where matriculaDesafiado="+matriculaDesafiado+" order by idDesafio;");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
					DesafiovsDTO desafio = new DesafiovsDTO();
					desafio.setIdDesafio(result.getString("idDesafio"));	
					desafio.setMatriculaDesafiante(result.getString("matriculaDesafiante"));	
					desafio.setMatriculaDesafiado(result.getString("matriculaDesafiado"));
					desafio.setTituloDesafio(result.getString("tituloDesafio"));	
					desafio.setDesafio(result.getString("desafio"));	
					desafio.setRespostaDesafiado(result.getString("respostaDesafiado"));
					desafio.setResultado(result.getString("resultado"));	
					desafios.add(desafio);
			}
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return desafios;
	}
	
	
	public boolean respondeDesafio(DesafiovsDTO desafio) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	
		
		boolean isOk = false;
		
		sql.append("UPDATE DESAFIO SET respostaDesafiado='"+desafio.getRespostaDesafiado()+"' WHERE idDesafio="+desafio.getIdDesafio());


		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			isOk = true;

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return isOk;
	}
	
	public ArrayList<DesafiovsDTO> getCorrigirDesafio(String matriculaDesafiante) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList <DesafiovsDTO> desafios = new ArrayList<DesafiovsDTO>();
		
		pstmt = db.prepareStatement("select * from desafio where matriculaDesafiante='"+matriculaDesafiante+"';");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
					DesafiovsDTO desafio = new DesafiovsDTO();
					desafio.setIdDesafio(result.getString("idDesafio"));	
					desafio.setMatriculaDesafiante(result.getString("matriculaDesafiante"));	
					desafio.setMatriculaDesafiado(result.getString("matriculaDesafiado"));
					desafio.setTituloDesafio(result.getString("tituloDesafio"));	
					desafio.setDesafio(result.getString("desafio"));	
					desafio.setRespostaDesafiado(result.getString("respostaDesafiado"));
					desafio.setResultado(result.getString("resultado"));	
					desafios.add(desafio);
			}
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return desafios;
	}
	
}


