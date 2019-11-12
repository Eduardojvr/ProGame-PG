package com.progame.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.progame.dao.PersonagemDAO;
import com.progame.dao.QuestaoDAO;
import com.progame.dto.QuestaoDTO;
import com.progame.dto.QuestaoLacunaDTO;
import com.progame.dto.QuestaoVerdadeiroFalsoDTO;
import com.progame.entity.Personagem;

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


	
}
