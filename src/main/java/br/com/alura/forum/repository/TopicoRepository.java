package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	//faz um select ,findBy = nomenclatura padrão.
	// o que vem depois pode ser o nome do atributo , ou o nome da classe mais atributo
	//nesse caso, busca pelo nome da classe(curso) e pela propriedade da classe(nome)
	//mas isso só é possível por que a entidade tópico tem relacionamento com a entidade Curso
	List<Topico> findByCurso_Nome(String nomeCurso);

	
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarCursoPorNome(@Param("nomeCurso") String nomeCurso);
}
