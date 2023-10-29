package edu.educate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class DoubleLengthValidator implements ConstraintValidator<DoubleLength, Double> {
    private int min;

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value == null || value.toString().length() >= min;
    }

    @Override
    public void initialize(DoubleLength constraintAnnotation) {
        this.min = constraintAnnotation.minLength();
    }
}
