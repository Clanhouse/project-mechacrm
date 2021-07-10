package com.crm.exception.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidParam.ValidSizeValidator.class})
public @interface ValidParam {

    String emptyParamMessage() default "empty parameter";

    String invalidParamMessage() default "invalid parameter";

    String message() default "blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidSizeValidator implements ConstraintValidator<ValidParam, String> {

        private final Pattern positiveNumbersPattern = Pattern.compile("^[0-9]*$");
        private String emptyParamMessage;
        private String invalidParamMessage;

        @Override
        public void initialize(ValidParam constraintAnnotation) {
            emptyParamMessage = constraintAnnotation.emptyParamMessage();
            invalidParamMessage = constraintAnnotation.invalidParamMessage();
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            String message = "";
            if (value == null) {
                message = emptyParamMessage;
            } else if (value.equals("")) {
                message = emptyParamMessage;
            } else if (!positiveNumbersPattern.matcher(value).matches()) {
               message = invalidParamMessage;
            }

            if (!message.isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                return false;
            }
            return true;
        }
    }
}
