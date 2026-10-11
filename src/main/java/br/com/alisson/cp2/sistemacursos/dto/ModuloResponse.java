package br.com.alisson.cp2.sistemacursos.dto;


public record ModuloResponse(Long id, Long cursoId, String titulo, Integer ordem, Integer cargaHoraria) {

}
