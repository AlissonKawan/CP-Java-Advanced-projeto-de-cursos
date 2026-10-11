package br.com.alisson.cp2.sistemacursos.dto;

import br.com.alisson.cp2.sistemacursos.validation.MultiploDeCinco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModuloRequest(@NotBlank @Size(max = 150) String titulo, @NotNull @Positive Integer ordem, @NotNull @MultiploDeCinco Integer cargaHoraria) {


}
