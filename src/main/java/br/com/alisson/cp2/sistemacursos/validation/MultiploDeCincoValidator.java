package br.com.alisson.cp2.sistemacursos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiploDeCincoValidator implements ConstraintValidator<MultiploDeCinco, Integer> {

    private int valor;

    @Override
    public void initialize(MultiploDeCinco anotacao) {
        if (anotacao.valor() <= 0) {
            throw new IllegalArgumentException("O valor de @MultiploDeCinco deve ser maior que zero");
        }
        this.valor = anotacao.valor();
    }

    @Override
    public boolean isValid(Integer cargaHoraria, ConstraintValidatorContext context) {
        if (cargaHoraria == null) {
            return true;
        }
        return cargaHoraria % valor == 0;
    }
}
