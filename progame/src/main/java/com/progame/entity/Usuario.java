package com.progame.entity;

public class Usuario {
	private String nomeUsuario;
	private String matricula;
	private String senha;
	private String idTipoPerfil;
	private String idPersonagem;
	private String email;
	private String pontuacao;
	private String level;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getIdTipoPerfil() {
		return idTipoPerfil;
	}
	public void setIdTipoPerfil(String idTipoPerfil) {
		this.idTipoPerfil = idTipoPerfil;
	}
	public String getIdPersonagem() {
		return idPersonagem;
	}
	public void setIdPersonagem(String idPersonagem) {
		this.idPersonagem = idPersonagem;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(String pontuacao) {
		this.pontuacao = pontuacao;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	
}
