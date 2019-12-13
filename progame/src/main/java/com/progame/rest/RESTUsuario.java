
package com.progame.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.progame.entity.Usuario;
import com.progame.dao.*;
import com.progame.dto.DesafiovsDTO;;

@Path("/user")
public class RESTUsuario {
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@POST
	@Path("/insert")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		
		try {
			dao.insert(usuario);
			return Response.ok().entity("Usuario cadastrado").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Erro ao cadastrar!").build();
		}

	}
	

	@POST
	@Path("/pontos")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response atualizaPontos(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		
		try {
			dao.insert(usuario);
			return Response.ok().entity("Usuario cadastrado").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity("Erro ao cadastrar!").build();
		}

	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario login(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();

		try {
			Usuario login = dao.getLogin(usuario);

			if (login != null) {
				request.getSession().setAttribute("logado", true);
				request.getSession().setAttribute("level", login.getLevel());
				request.getSession().setAttribute("matricula", login.getMatricula());
				request.getSession().setAttribute("pontuacao", login.getPontuacao());
				request.getSession().setAttribute("email", login.getEmail());
				request.getSession().setAttribute("nomeUsuario", login.getNomeUsuario());
				request.getSession().setAttribute("idTipoPerfil", login.getIdTipoPerfil());
				request.getSession().setAttribute("idPersonagem", login.getIdPersonagem());				
				return login;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// System.out.println("Erro RESTUsuario");
			return null;
		}
		return null;

	}

	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout() {
		HttpSession session = request.getSession();
		try {
			if (session != null) {
				request.getSession().setAttribute("logado", false);
				session.invalidate();
				return Response.ok().status(Status.OK).build();
			} else {
				return Response.status(Status.FORBIDDEN).build();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.FORBIDDEN).entity("Erro ao realizar logout!").build();
		}

	}

	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public  Usuario usuario() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuarioTmp = null;
		try {
			usuarioTmp = dao.getUsuario(request.getSession().getAttribute("matricula").toString());
			
			Usuario usuario = new Usuario();
//			usuario.setLevel(request.getSession().getAttribute("level").toString());
			usuario.setMatricula(request.getSession().getAttribute("matricula").toString());
//			usuario.setPontuacao(request.getSession().getAttribute("pontuacao").toString());
//			usuario.setEmail(request.getSession().getAttribute("email").toString());
			usuario.setNomeUsuario(request.getSession().getAttribute("nomeUsuario").toString());
//			usuario.setIdTipoPerfil(request.getSession().getAttribute("idTipoPerfil").toString());
			usuario.setIdPersonagem(request.getSession().getAttribute("idPersonagem").toString());
			usuario.setPontuacao(usuarioTmp.getPontuacao());			
			usuario.setLevel(usuarioTmp.getLevel());
			usuario.setEmail(usuarioTmp.getEmail());
			usuario.setIdTipoPerfil(usuarioTmp.getIdTipoPerfil());
			
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@GET
	@Path("/atualizaPontuacao/{pontuacao}/{matricula}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public Boolean atualizaPontuacao(@PathParam("pontuacao") int pontuacao, @PathParam("matricula") String matricula) {
		UsuarioDAO dao = new UsuarioDAO();
		boolean update = false;
		try {
			update = dao.updatePontuacao(pontuacao, matricula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}
	
	@GET
	@Path("/atualizaLevel/{levelatual}/{matricula}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public Boolean atualizaLevel(@PathParam("levelatual") String levelatual, @PathParam("matricula") String matricula) {
		UsuarioDAO dao = new UsuarioDAO();
		boolean update = false;
		try {
			update = dao.updateLevel(levelatual, matricula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}
	
	@GET
	@Path("/todosUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Usuario>  getAllUser() {
		UsuarioDAO dao = new UsuarioDAO();
		ArrayList <Usuario> usuarios = null;
		
		try {
			usuarios = dao.getAllUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios; 
	}
	
}
