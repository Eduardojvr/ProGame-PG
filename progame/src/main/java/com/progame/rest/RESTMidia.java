package com.progame.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.progame.dao.MaterialDAO;
import com.progame.dao.MidiaDAO;
import com.progame.dto.MaterialDTO;
import com.progame.entity.Midia;

@Path("/midia")
public class RESTMidia {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	

@GET
@Path("/getAllMidia")
@Produces({ MediaType.APPLICATION_JSON })
public ArrayList <Midia> getAllMidia() {
	MidiaDAO dao = new MidiaDAO();
	ArrayList <Midia> ent = null;
	try {
		ent = dao.todasMidias();
		return ent;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}

}	
}
