package com.progame.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.progame.dao.AssuntoDAO;
import com.progame.entity.Assunto;

@Path("/assunto")
public class RESTAssunto {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@GET
	@Path("/getAllAssunto")
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList <Assunto> getAllAssunto() {
		AssuntoDAO dao = new AssuntoDAO();
		ArrayList <Assunto> ent = null;
		try {
			ent = dao.todosAssuntos();
			return ent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
