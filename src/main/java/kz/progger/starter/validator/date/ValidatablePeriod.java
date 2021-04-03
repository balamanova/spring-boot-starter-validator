package kz.progger.starter.validator.date;

import kz.progger.starter.validator.util.MessageCodes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = PeriodValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidatablePeriod {

    String periodEnvName();

    String startPeriodField();

    String endPeriodField();

    String message() default MessageCodes.INVALID_PERIOD;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
