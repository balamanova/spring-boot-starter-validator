package kz.progger.starter.validator.imei;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Assert that annotation in order to validate ImeiSV value
 * read more about difference of Imei and ImeiSV
 * https://ru.wikipedia.org/wiki/IMEI
 *
 * @author assem.balamanova
 * @created 31/01/2020 - 11:51
 */

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImeiSVValidator.class)
public @interface ImeiSV {
    /**
     * Validation message.
     *
     * @return validation message text
     */
    String message() default "Imei not valid";

    /**
     * Group validator belong to default empty.
     */
    Class<?>[] groups() default {};

    /**
     * Validator payload.
     */
    Class<? extends Payload>[] payload() default {};

}
