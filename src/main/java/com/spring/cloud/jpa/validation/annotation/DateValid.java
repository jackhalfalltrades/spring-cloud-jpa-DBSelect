package com.spring.cloud.jpa.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValid {
    String message() default "DateValid";

    String pattern() default "default";

    Class<?>[] groups() default {};// NOSONAR

    Class<? extends Payload>[] payload() default {};// NOSONAR

}