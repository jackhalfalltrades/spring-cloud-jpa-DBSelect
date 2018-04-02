package com.spring.cloud.jpa.validation.annotation;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EnableEncryptableProperties
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "job")
@Data
public class ValidValueValidator implements
        ConstraintValidator<ValidValue, String> {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ValidValueValidator.class);


    private String[] status;
    private List<String> validValueList;

    @Override
    public void initialize(ValidValue constraintAnnotation) {

        validValueList = new ArrayList<>();
        validValueList = Arrays.stream(status)
                .map(e -> e.toLowerCase())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String requestField,
                           ConstraintValidatorContext context) {
        boolean isValid = false;

        if (requestField == null) {
            return true;
        } else {
            if (validValueList.stream().anyMatch(e -> e.equalsIgnoreCase(requestField))) {
                isValid = true;
            }
        }
        return isValid;
    }
}