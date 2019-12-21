package com.progame.rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.progame.dao.CompiladorDAO;
import com.progame.dao.PersonagemDAO;
import com.progame.dao.QuestaoDAO;
import com.progame.dto.QuestaoCodigoDTO;
import com.progame.dto.QuestaoDTO;
import com.progame.dto.QuestaoLacunaDTO;
import com.progame.dto.QuestaoTodasDTO;
import com.progame.dto.QuestaoVerdadeiroFalsoDTO;
import com.progame.entity.Codigo;
import com.progame.entity.Personagem;
import com.progame.entity.Questao;
import com.progame.entity.RespostaLacuna;
import com.progame.entity.RespostaMultipla;
import com.progame.entity.RespostaVF;
import com.progame.entity.TipoQuestao;


@Path("/questao")
public class RESTQuestao {
	
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@GET
	@Path("/getQuestao/{level}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<QuestaoDTO> getQuestao(@PathParam("level") String level) {
		QuestaoDAO dao = new QuestaoDAO();
		List <QuestaoDTO> lista  = null;
		try {
			lista = dao.getQuestao(level);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	@GET
	@Path("/getQuestaoVerdadeiroFalso/{level}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<QuestaoVerdadeiroFalsoDTO> getQuestaoVerdadeiroFalso(@PathParam("level") String level) {
		QuestaoDAO dao = new QuestaoDAO();
		List <QuestaoVerdadeiroFalsoDTO> lista  = null;
		try {
			lista = dao.getQuestaoVF(level);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	@GET
	@Path("/getQuestaoLacuna/{level}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<QuestaoLacunaDTO> getQuestaoLacuna(@PathParam("level") String level) {
		QuestaoDAO dao = new QuestaoDAO();
		List <QuestaoLacunaDTO> lista  = null;
		try {
			lista = dao.getQuestaoLacuna(level);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	@GET
	@Path("/getQuestaoCodigo/{level}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<QuestaoCodigoDTO> getQuestaoCodigo(@PathParam("level") String level) {
		QuestaoDAO dao = new QuestaoDAO();
		List <QuestaoCodigoDTO> lista  = null;
		try {
			lista = dao.getQuestaoCodigo(level);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	@POST
	@Path("/verificacodigo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> verificacodigo(Codigo codigo) {
			ArrayList<String> resultado = null;
			final CompiladorDAO shell = new CompiladorDAO();
	        try {
	        	String comando = "cd && mkdir "+codigo.getMatricula()+" && cd "+ codigo.getMatricula() +" && git clone https://github.com/Eduardojvr/bibliotecasC.git && cd bibliotecasC/STDIO && echo '"+codigo.getCodigo()+"' >> "+codigo.getMatricula()+".c"+
	        			" && gcc "+codigo.getMatricula()+".c"+" -o "+codigo.getMatricula()+" && ./"+codigo.getMatricula();
//	        			+" && rm "+codigo.getMatricula()+".c"+" && rm "+codigo.getMatricula();
				resultado = shell.executeCommand(comando);
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
	}
	

	@GET
	@Path("/todasQuestoes/{level}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<QuestaoTodasDTO> todasQuestoes(@PathParam("level") String level){
		ArrayList<QuestaoTodasDTO> questoes = null;
		QuestaoDAO dao = new QuestaoDAO();
		
		try {
			questoes = dao.getTodasQuestoes(level);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return questoes;
	}

	@GET
	@Path("/tipoQuestao")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TipoQuestao> tipoQuestao(){
		ArrayList<TipoQuestao> questoes = null;
		QuestaoDAO dao = new QuestaoDAO();
		
		try {
			questoes = dao.getTipoQuestao();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return questoes;
	}
	
	@POST
	@Path("/insertQuestao")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean insertQuestao(Questao questao){
		boolean isOk = false;
		QuestaoDAO dao = new QuestaoDAO();
		try {
			isOk = dao.insertQuestao(questao);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}
	
	@POST
	@Path("/insertRespostaVF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean insertRespostaVF(RespostaVF resposta){
		boolean isOk = false;
		QuestaoDAO dao = new QuestaoDAO();
		try {
			isOk = dao.insertRespostaQuestaoVF(resposta);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}
	
	@POST
	@Path("/insertRespostaLacuna")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean insertRespostaLacuna(RespostaLacuna resposta){
		boolean isOk = false;
		QuestaoDAO dao = new QuestaoDAO();
		try {
			isOk = dao.insertRespostaQuestaoLacuna(resposta);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}
	
	@POST
	@Path("/insertRespostaMultipla")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean insertRespostaMultipla(RespostaMultipla resposta){
		boolean isOk = false;
		QuestaoDAO dao = new QuestaoDAO();
		try {
			isOk = dao.insertRespostaQuestaoMultipla(resposta);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}
	
}
