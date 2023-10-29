package edu.educate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DoubleLengthValidator.class})
public @interface DoubleLength {
    int minLength();

    String message() default "{doubleLength.min} {minLength}.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
