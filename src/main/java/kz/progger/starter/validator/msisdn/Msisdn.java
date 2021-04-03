package kz.progger.starter.validator.msisdn;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Assert that annotation in order to validate msisdn value
 *
 * @author assem.balamanova
 * @created 30/01/2020 - 19:20
 *
 */
@Documented
@Constraint(validatedBy = MsisdnValidator.class)
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface Msisdn {

    String message() default "Msisdn is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean required() default false;

    /**
     * Defines several {@code @NotEmpty} annotations on the same element.
     */
    @Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Msisdn[] value();
    }

}

