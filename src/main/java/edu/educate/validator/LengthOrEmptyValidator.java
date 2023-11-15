package edu.educate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthOrEmptyValidator implements ConstraintValidator<LengthOrEmpty, String> {
    private int min;
    private int max;

    @Override
    public void initialize(LengthOrEmpty constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Allow null or empty values
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        int length = value.length();
        return length >= min && length <= max;
    }
}

