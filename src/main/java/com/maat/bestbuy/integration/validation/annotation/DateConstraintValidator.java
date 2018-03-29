package com.maat.bestbuy.integration.validation.annotation;

import org.apache.commons.lang.time.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConstraintValidator implements ConstraintValidator<DateValid, String> {

    private String pattern;

    private static final int DATE_LENGTH_BOUNDARY = 10;

    private String[] patterns;

    private static String DEFAULT = "default";

    @Override
    public void initialize(DateValid constraint) {
        pattern = constraint.pattern();
        if (DEFAULT.equals(pattern)) {
            patterns = new String[] { "yyyy-MM-dd", "MM-dd-yyyy" };
        } else {
            patterns = new String[] { pattern };
        }
    }

    @Override
    public boolean isValid(String dateField, ConstraintValidatorContext cxt) {
        boolean isValid = false;
        if (dateField != null) {
            if (dateField.length() > DATE_LENGTH_BOUNDARY) {
                isValid = false;
            } else if (!DEFAULT.equals(pattern)) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.setLenient(false);
                try {
                    sdf.parse(dateField);
                    isValid = true;
                } catch (ParseException e) {
                    isValid = false;
                }
            } else {
                try {
                    DateUtils.parseDateStrictly(dateField, patterns);
                    isValid = true;
                } catch (ParseException e) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

}
