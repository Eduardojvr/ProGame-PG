package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.progame.dto.*;

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
	
	public boolean updatePontuacao(int pontos, String matricula, String levelAtual) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;

		Boolean execute = false;
		StringBuilder sql = new StringBuilder();

		sql.append("update USUARIO set pontuacao="+pontos+" where matricula="+matricula);
		
		if(pontos >=800 && pontos<1600) {
			updateLevel(2 , matricula);
			
		} else if(pontos >=1600 && pontos < 3200) {
			updateLevel(3 , matricula);

		} else if(pontos >=3200 && pontos < 6400) {
			updateLevel(4 , matricula);
			
		} else if(pontos >=6400 && pontos < 12800) {
			updateLevel(5 , matricula);
			
		} else if(pontos >=12800 && pontos < 25600) {
			updateLevel(6 , matricula);
			
		} else if(pontos >=25600 && pontos < 51200) {
			updateLevel(7 , matricula);
			
		} else if(pontos >=51200 && pontos < 102400) {
			updateLevel(8 , matricula);
			
		} else if(pontos >=102400 && pontos < 204800) {
			updateLevel(9 , matricula);
		} else if(pontos >=204800 && pontos < 409600) {
			updateLevel(10 , matricula);

		} else if(pontos >=409600 && pontos < 819200) {
			updateLevel(11 , matricula);
			
		} else if(pontos >=819200 && pontos < 819200) {
			updateLevel(12 , matricula);
		} 
		
		
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
	

	public boolean updateLevel(int level, String matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;

		Boolean execute = false;
		StringBuilder sql = new StringBuilder();

						
//		int novoLevel = Integer.parseInt(level) + 1;

				
		sql.append("update USUARIO set level="+level+" where matricula="+matricula);
		
		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
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
	
	public ArrayList<Usuario> getAllUsuario() throws Exception {
		Usuario achou = null;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList <Usuario> usuario = new ArrayList<Usuario>();
		
		pstmt = db.prepareStatement("select * from usuario order by pontuacao desc");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
					achou = new Usuario();
					achou.setNomeUsuario(result.getString("nomeUsuario"));
					achou.setMatricula(result.getString("matricula"));
					//achou.setSenha(result.getString("senha"));
					achou.setIdTipoPerfil(result.getString("idTipoPerfil"));
					achou.setIdPersonagem(result.getString("idPersonagem"));
					achou.setEmail(result.getString("email"));		
					achou.setPontuacao(result.getString("pontuacao"));				
					achou.setLevel(result.getString("level"));	
					usuario.add(achou);

			}
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return usuario;
	}
	
}
