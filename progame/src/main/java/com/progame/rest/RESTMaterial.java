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
import com.progame.dto.MaterialDTO;
import com.progame.dto.curteDTO;;


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
	
	@GET
	@Path("/likeVideo/{idMaterial}/{matricula}/{tipo}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean controlaLike(@PathParam("idMaterial") String idMaterial, @PathParam("matricula") String matricula, @PathParam("tipo") String tipo) {
		MaterialDAO dao = new MaterialDAO();
		Boolean status = false;
		try {
			status = dao.controlaCurtida(idMaterial, matricula, tipo);
			status = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	@GET
	@Path("/todosLikes")
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<curteDTO> todosLikes() {
		MaterialDAO dao = new MaterialDAO();
		ArrayList <curteDTO> todos = null;
		try {
			todos = dao.getAllLike();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return todos;
	}
	

	@GET
	@Path("/todosDislikes")
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<curteDTO> todosDislikes() {
		MaterialDAO dao = new MaterialDAO();
		ArrayList <curteDTO> todos = null;
		try {
			todos = dao.getAllDislike();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return todos;
	}
	
}
