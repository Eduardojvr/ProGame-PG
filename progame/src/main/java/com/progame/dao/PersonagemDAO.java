package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.core.Response;

import com.progame.entity.Personagem;
import com.progame.entity.Usuario;

public class PersonagemDAO {

	public Personagem buscaPersonagem(String id) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		Personagem p = new Personagem();

		pstmt = db.prepareStatement("select idPersonagem, nomePersonagem, imgPersonagem from PERSONAGEM where idPersonagem="+id);
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				if (id.equals(result.getString("idPersonagem"))){
					p.setIdPersonagem(result.getString("idPersonagem"));			
					p.setNomePersonagem(result.getString("nomePersonagem"));
					p.setImgPersonagem(result.getString("imgPersonagem"));			
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return p;
	}
	
	public Response setPersonagem(String idPersonagem, String matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		sql.append("update usuario set idPersonagem="+idPersonagem+" where matricula="+matricula);

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			return Response.ok().build();

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

	}

}
