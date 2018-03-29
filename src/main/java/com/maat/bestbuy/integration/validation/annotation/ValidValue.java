package com.maat.bestbuy.integration.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidValueValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidValue {
    String message() default "value.notaccepted";

    //String[] acceptedValues();

    boolean ignoreNull() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}