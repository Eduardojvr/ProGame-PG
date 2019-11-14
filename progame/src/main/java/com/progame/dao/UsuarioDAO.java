package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.progame.entity.Usuario;

public class UsuarioDAO {
	public UsuarioDAO() {

	}

	public boolean insert(Usuario usuario) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();
		
		String senha = usuario.getSenha();
		MessageDigest m=MessageDigest.getInstance("MD5");
	    m.update(senha.getBytes(),0,senha.length());
	    String criptografia = new BigInteger(1,m.digest()).toString(16);

		sql.append("INSERT INTO usuario ");
		sql.append(" ( ");
		sql.append(" nomeUsuario, ");
		sql.append(" matricula, ");
		sql.append(" senha, ");		
		sql.append(" idTipoPerfil, ");
		sql.append(" idPersonagem, ");
		sql.append(" email, ");
		sql.append(" pontuacao, ");
		sql.append(" level ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, usuario.getNomeUsuario());
			pstmt.setString(2, usuario.getMatricula());
			pstmt.setString(3, criptografia);
			pstmt.setString(4, usuario.getIdTipoPerfil());
			pstmt.setString(5, "7");
			pstmt.setString(6, usuario.getEmail());
			pstmt.setString(7, "0");
			pstmt.setString(8, "1");

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;

	}

	public Usuario getLogin(Usuario user) throws Exception {
		Usuario achou = null;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;

			
		String senha = user.getSenha();
		MessageDigest m=MessageDigest.getInstance("MD5");
	    m.update(senha.getBytes(),0,senha.length());
	    String criptografia = new BigInteger(1,m.digest()).toString(16);
		
		
		pstmt = db.prepareStatement("select nomeUsuario, matricula, senha, idTipoPerfil, idPersonagem, email, pontuacao, level from usuario");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				if (user.getMatricula().equals(result.getString("matricula"))
						&& criptografia.equals(result.getString("senha"))) {
					achou = new Usuario();
					achou.setNomeUsuario(result.getString("nomeUsuario"));
					achou.setMatricula(result.getString("matricula"));
					//achou.setSenha(result.getString("senha"));
					achou.setIdTipoPerfil(result.getString("idTipoPerfil"));
					achou.setIdPersonagem(result.getString("idPersonagem"));
					achou.setEmail(result.getString("email"));		
					achou.setPontuacao(result.getString("pontuacao"));				
					achou.setLevel(result.getString("level"));				

				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return achou;
	}
	
	public boolean updatePontuacao(int pontos, String matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;

		Boolean execute = false;
		StringBuilder sql = new StringBuilder();

		sql.append("update USUARIO set pontuacao="+pontos+" where matricula="+matricula);

		
		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			execute = true;
		}catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return execute;
	}
	

	public boolean updateLevel(String levelatual, String matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		
		int novoLevel = Integer.parseInt(levelatual) + 1;
				
		Boolean execute = false;
		pstmt = db.prepareStatement("update USUARIO set level="+novoLevel+" where matricula="+matricula);

		try {
			result = pstmt.executeQuery();
			execute = true;
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return execute;
	}
	
	
	public Usuario getUsuario(String matricula) throws Exception {
		Usuario achou = null;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;

		pstmt = db.prepareStatement("select nomeUsuario, matricula, senha, idTipoPerfil, idPersonagem, email, "
				+ "pontuacao, level from usuario where matricula="+matricula);

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				if (matricula.equals(result.getString("matricula"))) {
					achou = new Usuario();
					achou.setNomeUsuario(result.getString("nomeUsuario"));
					achou.setMatricula(result.getString("matricula"));
					//achou.setSenha(result.getString("senha"));
					achou.setIdTipoPerfil(result.getString("idTipoPerfil"));
					achou.setIdPersonagem(result.getString("idPersonagem"));
					achou.setEmail(result.getString("email"));		
					achou.setPontuacao(result.getString("pontuacao"));				
					achou.setLevel(result.getString("level"));				

				}
			}
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return achou;
	}
}
