package com.progame.dto;

public class QuestaoLacunaDTO {
	private String idQuestao;
	private String idTipoQuestao;
	private String questao;
	private String resposta;
	private String respostaAlternativa;
	private String comentarioCorreta;
	private String comentarioIncorreta;
	public String getComentarioCorreta() {
		return comentarioCorreta;
	}
	public void setComentarioCorreta(String comentarioCorreta) {
		this.comentarioCorreta = comentarioCorreta;
	}
	private String nomeLinguagem;
	private String assunto;
	
	public String getIdQuestao() {
		return idQuestao;
	}
	public void setIdQuestao(String idQuestao) {
		this.idQuestao = idQuestao;
	}
	public String getIdTipoQuestao() {
		return idTipoQuestao;
	}
	public void setIdTipoQuestao(String idTipoQuestao) {
		this.idTipoQuestao = idTipoQuestao;
	}
	public String getQuestao() {
		return questao;
	}
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
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
	public String getRespostaAlternativa() {
		return respostaAlternativa;
	}
	public void setRespostaAlternativa(String respostaAlternativa) {
		this.respostaAlternativa = respostaAlternativa;
	}
	public String getComentarioIncorreta() {
		return comentarioIncorreta;
	}
	public void setComentarioIncorreta(String comentarioIncorreta) {
		this.comentarioIncorreta = comentarioIncorreta;
	}
	
	
	
}
