package br.com.alisson.cp2.sistemacursos.model;

import br.com.alisson.cp2.sistemacursos.validation.MultiploDeCinco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @NotBlank(message = "O campo é obrigatório")
    @Size(min = 150, message = "Deve conter no minimo 150 caracteres")
    private String titulo;

    @NotNull
    @Positive
    private Integer ordem;

    @NotNull
    @MultiploDeCinco
    private Integer cargaHoraria;




}
