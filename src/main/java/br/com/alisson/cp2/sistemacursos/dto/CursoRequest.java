package br.com.alisson.cp2.sistemacursos.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CursoRequest (@NotBlank(message = "Titulo é obrigatório!") @Size(max = 150) String titulo, String descricao) {
}
