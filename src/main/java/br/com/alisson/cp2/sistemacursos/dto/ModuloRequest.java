package br.com.alisson.cp2.sistemacursos.dto;

import br.com.alisson.cp2.sistemacursos.validation.MultiploDeCinco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModuloRequest(@NotBlank @Size(max = 150) String titulo, @NotBlank @Positive Integer ordem, @NotBlank @MultiploDeCinco Integer cargaHoraria) {


}
