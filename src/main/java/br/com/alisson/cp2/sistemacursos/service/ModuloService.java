package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.dto.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ModuloService {

    ModuloResponse cadastrarModulo(Long cursoId, ModuloRequest moduloRequest);

    ModuloResponse buscarModuloPorId(Long id);

    PageResponse<ModuloResponse> listarModulosPorCurso(Long cursoId, Pageable pageAble);

    void excluirModulo(Long id);



}
