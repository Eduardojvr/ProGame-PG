package com.progame.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.progame.dto.DesafiovsDTO;
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
		
//	public boolean verificaStatusItem(ItemEspadaDTO item) throws Exception {
//
//		Connection db = ConnectionManager.getDBConnection();
//		PreparedStatement pstmt = null;
//
//		StringBuilder sql = new StringBuilder();	
//		
//		boolean isOk = false;
//		
//		sql.append(	"update desafio set resultado='"+desafio.getResultado()+"', avaliacao='"+desafio.getAvaliacao()+"' where idDesafio="+desafio.getIdDesafio());
//
//		try {
//			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
//			pstmt.executeUpdate();
//			isOk = true;
//
//		} finally {
//			if (pstmt != null)
//				pstmt.close();
//			db.close();
//		}
//		return isOk;
//	}
	
	public ArrayList<ItemEspadaDTO> minhasCompras(String matricula) throws Exception {
		ItemEspadaDTO espada = null;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		
		ArrayList <ItemEspadaDTO> all = new ArrayList<ItemEspadaDTO>();

		pstmt = db.prepareStatement("select ue.id, ue.idEspada, ue.matricula, ue.usou, ue.contaUsos, e.qtd, e.texto, e.valor, e.fator from usa_espada ue inner join espada e on e.idEspada=ue.idEspada where ue.matricula="+matricula+" and ue.usou='n'");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				espada = new ItemEspadaDTO();
				espada.setId(result.getString("id"));
				espada.setIdEspada(result.getString("idEspada"));
				espada.setMatricula(result.getString("matricula"));
				espada.setUsou(result.getString("usou"));
				espada.setContaUsos(result.getString("contaUsos"));		
				espada.setQtd(result.getString("qtd"));
				espada.setTexto(result.getString("texto"));
				espada.setValor(result.getString("valor"));	
				espada.setFator(result.getString("fator"));	
				all.add(espada);
			}

		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return all;
	}	
	
	public ArrayList <ItemEspadaDTO> getOneEspada(int id) throws Exception {
		ItemEspadaDTO espada = null;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		ResultSet result = null;
		ArrayList <ItemEspadaDTO> item = new ArrayList<ItemEspadaDTO>();

		pstmt = db.prepareStatement("select * from espada where idEspada="+id);
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				espada = new ItemEspadaDTO();
				espada.setIdEspada(result.getString("idEspada"));
				espada.setFator(result.getString("fator"));
				espada.setQtd(result.getString("qtd"));
				espada.setTexto(result.getString("texto"));		
				espada.setValor(result.getString("valor"));	
				item.add(espada);
			}

		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return item;
	}	
	
	public boolean atualizaMinhasCompras(ItemEspadaDTO item) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	
		
		boolean isOk = false;
		ArrayList <ItemEspadaDTO> verifica = getOneEspada(Integer.parseInt(item.getIdEspada()));

		if(Integer.parseInt(item.getContaUsos()) < Integer.parseInt(verifica.get(0).getQtd())) {
			sql.append("update usa_espada set contaUsos='"+item.getContaUsos()+"' where id="+item.getId());			
		} else {
			sql.append("update usa_espada set contaUsos='"+item.getContaUsos()+"', usou='s' where id="+item.getId());			
		}

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
}

