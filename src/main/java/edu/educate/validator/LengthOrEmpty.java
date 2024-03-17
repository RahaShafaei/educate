package edu.educate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = LengthOrEmptyValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface LengthOrEmpty {
    String message() default "Invalid field length";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min() default 0;
    int max() default Integer.MAX_VALUE;
}
