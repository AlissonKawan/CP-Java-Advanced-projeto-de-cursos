package br.com.alisson.cp2.sistemacursos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultiploDeCincoValidator.class)
public @interface MultiploDeCinco {
    String message() default "Aceito apenas valores multiplos de 5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
