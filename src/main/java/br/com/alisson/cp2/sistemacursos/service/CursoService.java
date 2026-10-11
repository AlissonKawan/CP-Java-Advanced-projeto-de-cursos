package br.com.alisson.cp2.sistemacursos.service;


import br.com.alisson.cp2.sistemacursos.dto.CursoRequest;
import br.com.alisson.cp2.sistemacursos.dto.CursoResponse;

import br.com.alisson.cp2.sistemacursos.dto.PageResponse;
import org.springframework.data.domain.Pageable;

public interface CursoService {

    CursoResponse cadastrarCurso(CursoRequest request);

    CursoResponse buscarCursoPorId(Long id);

    PageResponse<CursoResponse> listarCursos(Pageable pageable);

    CursoResponse atualizarCurso(Long id, CursoRequest request);

    void excluirCurso(Long id);
}
