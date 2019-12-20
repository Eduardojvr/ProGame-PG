package com.progame.entity;

public class RespostaLacuna {
	private String idQuestao;
	private String resposta;
	private String respostaAlternativa;
	private String comentarioCorreta;
	private String comentarioIncorreta;
	
	public String getIdQuestao() {
		return idQuestao;
	}
	public void setIdQuestao(String idQuestao) {
		this.idQuestao = idQuestao;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public String getRespostaAlternativa() {
		return respostaAlternativa;
	}
	public void setRespostaAlternativa(String respostaAlternativa) {
		this.respostaAlternativa = respostaAlternativa;
	}
	public String getComentarioCorreta() {
		return comentarioCorreta;
	}
	public void setComentarioCorreta(String comentarioCorreta) {
		this.comentarioCorreta = comentarioCorreta;
	}
	public String getComentarioIncorreta() {
		return comentarioIncorreta;
	}
	public void setComentarioIncorreta(String comentarioIncorreta) {
		this.comentarioIncorreta = comentarioIncorreta;
	}
	
	

}
