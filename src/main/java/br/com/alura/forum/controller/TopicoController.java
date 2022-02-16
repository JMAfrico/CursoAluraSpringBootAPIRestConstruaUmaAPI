package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTODetalhado;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

//	@RequestMapping("/topicos")
//	public List<TopicoDTO> lista() {
//		Curso java = new Curso("java", "back end");
//		Topico t1 = new Topico("Dúvida exception", "Erro na hora de executar o main", java);
//		
//		Curso c = new Curso("c", "back end");
//		Topico t2 = new Topico("Mensagem no terminal", "Não aparece a mensagem", c);
//		
//		Curso ruby = new Curso("ruby", "automação");
//		Topico t3 = new Topico("Dúvida assert", "Erro na hora chamar o assert", ruby);
//		
//		return TopicoDTO.converter(Arrays.asList(t1,t2,t3));
//	}

//	@RequestMapping("/topicos")
//	public List<TopicoDTO> lista() {	
//		List<Topico> topicos = topicoRepository.findAll();
//		return TopicoDTO.converter(topicos);
//	}

	
	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
			return TopicoDTO.converter(topicos);
		}
	}
	
//	@PostMapping
//	public void cadastrar(@RequestBody TopicoForm form) {
//		Topico topico = form.converter(cursoRepository);
//		topicoRepository.save(topico);
//	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	
	@GetMapping("/{id}")
	public TopicoDTODetalhado detalhar(@PathVariable Long id) {
		Topico topico = topicoRepository.getById(id);
		return new TopicoDTODetalhado(topico);
	}
}
