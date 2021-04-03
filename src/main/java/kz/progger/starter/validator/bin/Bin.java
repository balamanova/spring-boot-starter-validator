package kz.progger.starter.validator.bin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Assert that annotation in order to validate bin value
 *
 * @author assem.balamanova
 * @created 30/01/2020 - 18:46
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BinValidator.class)
public @interface Bin {

    /**
     * Validation message.
     *
     * @return validation message text
     */
    String message() default "Bin is not valid";

    /**
     * Group validator belong to default empty.
     */
    Class<?>[] groups() default {};

    /**
     * Validator payload.
     */
    Class<? extends Payload>[] payload() default {};

}

