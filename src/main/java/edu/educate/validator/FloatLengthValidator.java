package edu.educate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class FloatLengthValidator implements ConstraintValidator<FloatLength, Float> {
    private int min;

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {
        return value == null || value.toString().length() >= min;
    }

    @Override
    public void initialize(FloatLength constraintAnnotation) {
        this.min = constraintAnnotation.minLength();
    }
}
