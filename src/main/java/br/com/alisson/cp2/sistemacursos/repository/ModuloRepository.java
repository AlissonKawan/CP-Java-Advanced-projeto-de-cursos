package br.com.alisson.cp2.sistemacursos.repository;

import br.com.alisson.cp2.sistemacursos.mapper.CursoMapper;
import br.com.alisson.cp2.sistemacursos.model.Curso;
import br.com.alisson.cp2.sistemacursos.model.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    Page<Modulo> findByCurso_Id(Long cursoId, Pageable pageable);
}
