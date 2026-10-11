package br.com.alisson.cp2.sistemacursos.mapper;

import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.model.Modulo;

public final class ModuloMapper {

    private ModuloMapper() {

    }

    public static ModuloResponse toResponse(Modulo modulo) {
        return new ModuloResponse(modulo.getId(), modulo.getCurso().getId(), modulo.getTitulo(), modulo.getOrdem(), modulo.getCargaHoraria());
    }
}
