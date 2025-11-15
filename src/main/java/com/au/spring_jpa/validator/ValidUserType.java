package com.au.spring_jpa.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserTypeValidator.class)
public @interface ValidUserType {
    String message() default "Invalid user type context";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}