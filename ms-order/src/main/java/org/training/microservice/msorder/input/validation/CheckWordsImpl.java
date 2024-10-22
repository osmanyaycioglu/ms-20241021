package org.training.microservice.msorder.input.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CheckWordsImpl implements ConstraintValidator<CheckWords,String> {


    private CheckWords anno;

    @Override
    public void initialize(final CheckWords anno) {
        this.anno = anno;
    }

    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        return Arrays.stream(anno.value()).noneMatch(value::contains);
    }
}
