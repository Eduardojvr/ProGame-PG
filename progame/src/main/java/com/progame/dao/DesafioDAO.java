package com.progame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.progame.dto.DesafioDTO;
import com.progame.dto.DesafiovsDTO;
import com.progame.dto.ItemEspadaDTO;
import com.progame.entity.Usuario;

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
		sql.append(" resultado, ");
		sql.append(" avaliacao ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, desafio.getMatriculaDesafiante());
			pstmt.setString(2, desafio.getMatriculaDesafiado());
			pstmt.setString(3, desafio.getTituloDesafio());
			pstmt.setString(4, desafio.getDesafio());
			pstmt.setString(5, null);
			pstmt.setString(6, null);
			pstmt.setString(7, null);

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
					desafio.setAvaliacao(result.getString("avaliacao"));	
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
				desafio.setAvaliacao(result.getString("avaliacao"));	
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
	
	public boolean salvaAvaliacao(DesafiovsDTO desafio) throws Exception {

		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	
		
		boolean isOk = false;
		
		sql.append(	"update desafio set resultado='"+desafio.getResultado()+"', avaliacao='"+desafio.getAvaliacao()+"' where idDesafio="+desafio.getIdDesafio());

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
	
	public ArrayList<DesafioDTO> todosDesafiosProgramado(double matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;
	
		ResultSet result = null;

		ArrayList <DesafioDTO> desafios = new ArrayList<DesafioDTO>();
		ArrayList <DesafioDTO> desafiosFiltrados = todosDesafiosProgramadoFiltrado(matricula);
		
		pstmt = db.prepareStatement("select * from desafio_programado;");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				DesafioDTO desafio = new DesafioDTO();
				desafio.setIdDesafio(result.getString("idDesafio"));	
				desafio.setDesafio(result.getString("desafio"));
				desafio.setImgDesafio(result.getString("imgDesafio"));
				desafio.setIdResposta(" ");
				desafio.setMatricula(" ");	
				desafio.setResposta(" ");	
				desafio.setCorrecaoAvaliador(" ");
				desafios.add(desafio);
			}

			for(int i = 0; i < desafiosFiltrados.size() ; i++){
				for(int j = 0; j < desafios.size(); j++){
					if(desafios.get(j).getIdDesafio().equals(desafiosFiltrados.get(i).getIdDesafio())){
						desafios.remove(j);
					}
				}
			}

			for(int i = 0; i < desafiosFiltrados.size(); i++){
				desafios.add(desafiosFiltrados.get(i));
			}
			
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
	
		return desafios;
	}

	public ArrayList<DesafioDTO> todosDesafiosProgramadoFiltrado(double matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;
	
		ResultSet result = null;

		ArrayList <DesafioDTO> desafios = new ArrayList<DesafioDTO>();


		pstmt = db.prepareStatement("select * from desafio_programado dp left join resposta_desafio_programado rp on rp.idDesafio=dp.idDesafio where rp.matricula='"+matricula+"';");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				DesafioDTO desafio = new DesafioDTO();				
				desafio.setIdDesafio(result.getString("idDesafio"));	
				desafio.setDesafio(result.getString("desafio"));
				desafio.setImgDesafio(result.getString("imgDesafio"));
				desafio.setIdResposta(result.getString("idResposta"));
				desafio.setMatricula(result.getString("matricula"));	
				desafio.setResposta(result.getString("resposta"));	
				desafio.setCorrecaoAvaliador(result.getString("correcaoAvaliador"));
				desafios.add(desafio); 
			}
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
	
		return desafios;
	}
	
	public boolean salvaRespostaDesafioProgramado(DesafioDTO desafio) throws Exception {
		boolean isOk = false;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	

		sql.append("INSERT INTO resposta_desafio_programado ");
		sql.append(" ( ");
		sql.append(" idDesafio, ");
		sql.append(" matricula, ");
		sql.append(" resposta, ");		
		sql.append(" correcaoAvaliador ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, desafio.getIdDesafio());
			pstmt.setString(2, desafio.getMatricula());
			pstmt.setString(3, desafio.getResposta());
			pstmt.setString(4, "Aguardando avaliação");
			pstmt.executeUpdate();
			isOk=true;

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return isOk;
	}
	
	public int totalDesafioCerto(double matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;
	
		ResultSet result = null;

		ArrayList <DesafioDTO> desafios = new ArrayList<DesafioDTO>();
		int total = 0;

		pstmt = db.prepareStatement("select count(*) as Total from resposta_desafio_programado rdp where rdp.matricula="+matricula+" and rdp.correcaoAvaliador='certo' group by rdp.matricula");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				total = result.getInt("total");
			}
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
	
		return total;
	}
	
	public int totalDesafioErrado(double matricula) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;
	
		ResultSet result = null;

		ArrayList <DesafioDTO> desafios = new ArrayList<DesafioDTO>();
		int total = 0;

		pstmt = db.prepareStatement("select count(*) as Total from resposta_desafio_programado rdp where rdp.matricula="+matricula+" and rdp.correcaoAvaliador='Errado' group by rdp.matricula");
		
		try {
			result = pstmt.executeQuery();
			while (result.next()) {
				total = result.getInt("total");
			}
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
	
		return total;
	}
	
	public boolean insereDesafio(DesafioDTO desafio) throws Exception {
		boolean isOk = false;
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();	

		sql.append("INSERT INTO desafio_programado ");
		sql.append(" ( ");
		sql.append(" desafio, ");		
		sql.append(" imgDesafio ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?);");

		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, desafio.getDesafio());
			pstmt.setString(2, desafio.getImgDesafio());
			pstmt.executeUpdate();
			isOk=true;

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return isOk;
	}
}





