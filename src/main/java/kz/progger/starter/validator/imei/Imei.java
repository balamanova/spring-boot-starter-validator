package kz.progger.starter.validator.imei;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Assert that annotation in order to validate Imei value
 * read more about difference of Imei and ImeiSV
 * https://ru.wikipedia.org/wiki/IMEI
 *
 * @author assem.balamanova
 * @created 31/01/2020 - 11:14
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImeiValidator.class)
public @interface Imei {
    /**
     * Validation message.
     *
     * @return validation message text
     */
    String message() default "Imsi not valid";

    /**
     * Group validator belong to default empty.
     */
    Class<?>[] groups() default {};

    /**
     * Validator payload.
     */
    Class<? extends Payload>[] payload() default {};

}
