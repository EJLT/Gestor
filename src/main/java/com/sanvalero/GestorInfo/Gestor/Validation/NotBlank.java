package com.sanvalero.GestorInfo.Gestor.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotBlankValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {
    String message() default "El campo no puede estar en blanco";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}