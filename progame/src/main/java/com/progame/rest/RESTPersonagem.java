package com.progame.rest;

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
import javax.ws.rs.core.Response.Status;

import com.progame.dao.PersonagemDAO;
import com.progame.entity.Personagem;

@Path("/personagem")
public class RESTPersonagem {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@GET
	@Path("/getPersonagem/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Personagem getPersonagem(@PathParam("id") String id) {
		PersonagemDAO dao = new PersonagemDAO();
		Personagem p = null;
		try {
			p = dao.buscaPersonagem(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	@GET
	@Path("/setPersonagem/{idPersonagem}/{matricula}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getPersonagem(@PathParam("idPersonagem") String idPersonagem, @PathParam("matricula") String matricula) {
		PersonagemDAO dao = new PersonagemDAO();
		Personagem p = null;
		try {
			dao.setPersonagem(idPersonagem, matricula);
			return Response.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Erro ao selecionar guerreiro!!").build();
		}
		
	}


}
