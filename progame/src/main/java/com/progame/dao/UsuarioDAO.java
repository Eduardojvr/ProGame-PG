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
		sql.append(" email ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, usuario.getNomeUsuario());
			pstmt.setString(2, usuario.getMatricula());
			pstmt.setString(3, criptografia);
			pstmt.setString(4, usuario.getIdTipoPerfil());
			pstmt.setString(5, "0");
			pstmt.setString(6, usuario.getEmail());
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
		
		
		pstmt = db.prepareStatement("select nomeUsuario, matricula, senha, idTipoPerfil, idPersonagem, email from usuario");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				if (user.getMatricula().equals(result.getString("matricula"))
						&& criptografia.equals(result.getString("senha"))) {
					achou = new Usuario();
					achou.setNomeUsuario(result.getString("nomeUsuario"));
					achou.setMatricula(result.getString("matricula"));
					achou.setSenha(result.getString("senha"));
					achou.setIdTipoPerfil(result.getString("idTipoPerfil"));
					achou.setIdPersonagem(result.getString("idPersonagem"));
					achou.setEmail(result.getString("email"));				
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

}
