package com.progame.rest;

import java.util.ArrayList;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.progame.dao.LinguagemProgramacaoDAO;
import com.progame.entity.*;;

@Path("/linguagem")
public class RESTLinguagemProgramacao {

	@GET
	@Path("/todasLinguagens")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LinguagemProgramacao> getLinguagens(){
		
		try {
			LinguagemProgramacaoDAO gerenciador = new LinguagemProgramacaoDAO();
			List todas = new ArrayList <LinguagemProgramacao>();
			todas = gerenciador.todasLinguagens();
			return todas;
		}catch(Exception e) {
			return null;	
		}
				
	}
	

	@POST
	@Path("/inserir")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insert(LinguagemProgramacao linguagem) {
		LinguagemProgramacaoDAO dao = new LinguagemProgramacaoDAO();

		
		try {
			dao.insereLinguagem(linguagem);
			return Response.ok().entity("Linguagem cadastrada!").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Erro ao cadastrar!").build();
		}

	}
}
