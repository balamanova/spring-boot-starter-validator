package kz.progger.starter.validator.msisdn;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author assem.balamanova
 * @created 30/01/2020 - 19:21
 */

public class MsisdnValidator implements ConstraintValidator<Msisdn, String> {

    private Msisdn constraint;

    public void initialize(Msisdn constraint) {
        this.constraint = constraint;
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value) ) {
            if (constraint.required()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Msisdn cannot be null or empty")
                    .addConstraintViolation();
                return false;
            } else {
                return true;
            }
        }
        try {
            if(!value.matches("^77[0-9]{9}"))
                throw InvalidMsisdnException.invalidRangeForInputString(value);

        } catch (InvalidMsisdnException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getLocalizedMessage())
                .addConstraintViolation();
            return false;
        }
        return true;
    }

}

