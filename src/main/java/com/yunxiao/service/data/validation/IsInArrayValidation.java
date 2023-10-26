package com.yunxiao.service.data.validation;

import com.yunxiao.service.data.validation.validator.IsInArrayValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 15:52
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {IsInArrayValidator.class}
)
public @interface IsInArrayValidation {
    String[] value();

    String message() default "参数不在枚举中";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
