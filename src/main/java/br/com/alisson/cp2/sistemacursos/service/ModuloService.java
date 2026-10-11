package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;

public interface ModuloService {

    ModuloResponse cadastrarModulo(Long cursoId, ModuloRequest moduloRequest);

    ModuloResponse buscarModuloPorId(Long id);


}
