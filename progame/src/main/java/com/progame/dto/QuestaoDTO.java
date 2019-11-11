package com.progame.dto;

public class QuestaoDTO {
	private String idQuestao;
	private String idTipoConteudo;
	private String questao;
	private String respCorreta;
	private String respIncorreta1;
	private String respIncorreta2;
	private String respIncorreta3;
	private String comentarioCorreta;
	private String comentarioErrado;
	private String nomeLinguagem;
	private String assunto;
	
	public String getIdQuestao() {
		return idQuestao;
	}
	public void setIdQuestao(String idQuestao) {
		this.idQuestao = idQuestao;
	}
	public String getIdTipoConteudo() {
		return idTipoConteudo;
	}
	public void setIdTipoConteudo(String idTipoConteudo) {
		this.idTipoConteudo = idTipoConteudo;
	}
	public String getQuestao() {
		return questao;
	}
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	public String getRespCorreta() {
		return respCorreta;
	}
	public void setRespCorreta(String respCorreta) {
		this.respCorreta = respCorreta;
	}
	public String getRespIncorreta1() {
		return respIncorreta1;
	}
	public void setRespIncorreta1(String respIncorreta1) {
		this.respIncorreta1 = respIncorreta1;
	}
	public String getRespIncorreta2() {
		return respIncorreta2;
	}
	public void setRespIncorreta2(String respIncorreta2) {
		this.respIncorreta2 = respIncorreta2;
	}
	public String getRespIncorreta3() {
		return respIncorreta3;
	}
	public void setRespIncorreta3(String respIncorreta3) {
		this.respIncorreta3 = respIncorreta3;
	}
	public String getComentarioCorreta() {
		return comentarioCorreta;
	}
	public void setComentarioCorreta(String comentarioCorreta) {
		this.comentarioCorreta = comentarioCorreta;
	}
	public String getComentarioErrado() {
		return comentarioErrado;
	}
	public void setComentarioErrado(String comentarioErrado) {
		this.comentarioErrado = comentarioErrado;
	}
	public String getNomeLinguagem() {
		return nomeLinguagem;
	}
	public void setNomeLinguagem(String nomeLinguagem) {
		this.nomeLinguagem = nomeLinguagem;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	


}
