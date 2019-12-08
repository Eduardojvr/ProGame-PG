package com.progame.rest;

import java.util.ArrayList;

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

import com.progame.dao.MaterialDAO;
import com.progame.dao.PersonagemDAO;
import com.progame.dto.MaterialDTO;;


@Path("/material")
public class RESTMaterial {

		@Context
		private HttpServletRequest request;

		@Context
		private HttpServletResponse response;

		protected void setRequest(HttpServletRequest request) {
			this.request = request;
		}
	
	
	@GET
	@Path("/getmaterial/{idTipoMidia}/{idConteudo}/{idLinguagem}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ArrayList <MaterialDTO> getMaterial(@PathParam("idTipoMidia") String idTipoMidia, @PathParam("idConteudo") String idConteudo,
			@PathParam("idLinguagem") String idLinguagem) {
		MaterialDAO dao = new MaterialDAO();
		ArrayList <MaterialDTO> dto = null;
		try {
			dto = dao.getMaterial(idTipoMidia, idConteudo, idLinguagem);
			return dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@GET
	@Path("/getAllMaterial")
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList <MaterialDTO> getAllMaterial() {
		MaterialDAO dao = new MaterialDAO();
		ArrayList <MaterialDTO> dto = null;
		try {
			dto = dao.getAllMaterial();
			return dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@POST
	@Path("/insereMaterial")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean insereMaterial(MaterialDTO material) {
		MaterialDAO dao = new MaterialDAO();
		Boolean status = false;
		try {
			status = dao.insereMaterial(material);
			status = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	}

}
