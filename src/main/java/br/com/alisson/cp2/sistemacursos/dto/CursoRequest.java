package br.com.alisson.cp2.sistemacursos.dto;

import br.com.alisson.cp2.sistemacursos.model.Modulo;

import java.util.List;

public record CursoRequest (String titulo, String descricao, List<Modulo> modulos) {
}
