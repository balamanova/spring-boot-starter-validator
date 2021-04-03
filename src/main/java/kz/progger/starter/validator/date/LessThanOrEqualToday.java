package kz.progger.starter.validator.date;

import kz.progger.starter.validator.util.MessageCodes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LessThanOrEqualTodayValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LessThanOrEqualToday {

    String message() default MessageCodes.LTE_TODAY;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
