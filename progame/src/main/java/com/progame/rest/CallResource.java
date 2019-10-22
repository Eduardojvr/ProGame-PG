package com.progame.rest;

import java.util.ArrayList;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.progame.dao.CategoriaDAO;
import com.progame.entity.*;;

@Path("/callservice")
public class CallResource {

	@GET
	@Path("/calls")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> getCalls(){
		try {
			CategoriaDAO gerenciador = new CategoriaDAO();
			List todas = new ArrayList <Categoria>();
			todas = gerenciador.todasCategorias();
			return todas;
		}catch(Exception e) {
			return null;	
		}
				
	}
}
