package kz.progger.starter.validator.iin;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Assert annotation in order to validate IIN
 *
 * @author assem.balamanova
 * @created 03/02/2020 - 10:19
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IinValidator.class)
public @interface Iin {

    /**
     * Validation message.
     *
     * @return validation message text
     */
    String message() default "Iin is not valid";

    /**
     * Group validator belong to default empty.
     */
    Class<?>[] groups() default {};

    /**
     * Validator payload.
     */
    Class<? extends Payload>[] payload() default {};

}


