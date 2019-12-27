package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.progame.dto.ItemEspadaDTO;
import com.progame.entity.Usuario;

public class ItemDAO {
	
	public ItemDAO() {	
	}
	
	public boolean insert_uso_espada(ItemEspadaDTO item) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO usa_espada ");
		sql.append(" ( ");
		sql.append(" idEspada, ");
		sql.append(" matricula, ");
		sql.append(" usou, ");		
		sql.append(" contaUsos ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, item.getIdEspada());
			pstmt.setString(2, item.getMatricula());
			pstmt.setString(3, item.getUsou());
			pstmt.setString(4, item.getContaUsos());

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

		return true;
	}
	

	public ArrayList<ItemEspadaDTO> getEspadas() throws Exception {
		ItemEspadaDTO espada = null;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList <ItemEspadaDTO> espadas = new ArrayList<ItemEspadaDTO>();

		pstmt = db.prepareStatement("select * from espada");

		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				espada = new ItemEspadaDTO();
				espada.setIdEspada(result.getString("idEspada"));
				espada.setFator(result.getString("fator"));
				//achou.setSenha(result.getString("senha"));
				espada.setQtd(result.getString("qtd"));
				espada.setTexto(result.getString("texto"));
				espada.setValor(result.getString("valor"));		
				espadas.add(espada);

			}

		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return espadas;
	}
	

}
