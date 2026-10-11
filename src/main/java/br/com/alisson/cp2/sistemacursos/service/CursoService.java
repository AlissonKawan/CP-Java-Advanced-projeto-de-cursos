package br.com.alisson.cp2.sistemacursos.service;


import br.com.alisson.cp2.sistemacursos.dto.CursoRequest;
import br.com.alisson.cp2.sistemacursos.dto.CursoResponse;

public interface CursoService {

    CursoResponse cadastrarCurso(CursoRequest request);

    CursoResponse buscarCursoPorId(Long id);
}
