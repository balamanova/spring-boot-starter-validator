package kz.progger.starter.validator.imei;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author assem.balamanova
 * @created 31/01/2020 - 11:15
 */
public class ImeiValidator  implements ConstraintValidator<Imei, String> {

    private static final int VALID_LENGTH = 14;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Imei value cannot be null or empty")
                .addConstraintViolation();
            return false;
        }

        if (StringUtils.length(value) != VALID_LENGTH) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Imei length of value must to be " + VALID_LENGTH)
                .addConstraintViolation();
            return false;
        }

        return true;
    }


}
