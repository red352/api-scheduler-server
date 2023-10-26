package com.yunxiao.service.data.validation.validator;

import com.yunxiao.service.data.validation.IsInArrayValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 16:09
 */
public class IsInArrayValidator implements ConstraintValidator<IsInArrayValidation, String> {

    private String[] values;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return Arrays.asList(values).contains(value);
    }

    @Override
    public void initialize(IsInArrayValidation constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }
}

