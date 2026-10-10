package br.com.alisson.cp2.sistemacursos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiploDeCincoValidator implements ConstraintValidator<MultiploDeCinco, Integer> {

    @Override
    public boolean isValid(Integer cargaHoraria, ConstraintValidatorContext constraintValidatorContext) {
        if (cargaHoraria == null) {
            return true;
        }
        if (cargaHoraria % 5 == 0) {
            return true;
        }
        return false;
    }
}
