package br.com.alisson.cp2.sistemacursos.repository;

import br.com.alisson.cp2.sistemacursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
