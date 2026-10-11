package br.com.alisson.cp2.sistemacursos.mapper;

import br.com.alisson.cp2.sistemacursos.dto.CursoResponse;
import br.com.alisson.cp2.sistemacursos.model.Curso;

public final class CursoMapper {

    private CursoMapper() {}

    public static CursoResponse toResponse(Curso curso) {
        return new CursoResponse(curso.getId(), curso.getTitulo(), curso.getDescricao());
    }
}
