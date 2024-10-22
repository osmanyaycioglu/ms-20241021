package org.training.microservice.msorder.input.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { CheckWordsImpl.class })
public @interface CheckWords {

    String[] value();

    String message() default "Not allowed words {value}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
