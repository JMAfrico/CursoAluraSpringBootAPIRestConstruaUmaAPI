package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

public class TopicoDTODetalhado {

	//Classe utilizada para especificar o que vai ser mostrado, quais informações vamos receber do tipo GET
	//as demais informações que estão na entidade Topico, não são necessárias para mostrar aqui
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDTO> resposta;
	
	public TopicoDTODetalhado(Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus();
		this.nomeAutor = topico.getAutor().getNome();
		this.resposta = new ArrayList<>();
		this.resposta.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
		
	}
	public String getNomeAutor() {
		return nomeAutor;
	}
	public StatusTopico getStatus() {
		return status;
	}
	public List<RespostaDTO> getResposta() {
		return resposta;
	}
	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	public void setStatus(StatusTopico status) {
		this.status = status;
	}
	public void setResposta(List<RespostaDTO> resposta) {
		this.resposta = resposta;
	}
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public static List<TopicoDTODetalhado> converter(List<Topico> topicos) {
		return topicos.stream().map(TopicoDTODetalhado::new).collect(Collectors.toList());
	}
}
