package kz.progger.starter.validator.iin;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Assert that annotation in order to validate bin value
 *
 * @author assem.balamanova
 * @created 03/02/2020 - 10:20
 */
public class IinValidator implements ConstraintValidator<Iin, String> {

    private static final int VALID_LENGTH = 12;

    private Iin constraint;

    @Override
    public void initialize(Iin constraintAnnotation) {
        this.constraint = constraintAnnotation;
    }

    private boolean checkOnResidentValue(int fifthValue) {
        return fifthValue>0 && fifthValue<7;
    }

    private boolean checkOnBirthDateValidation(String date) {
        DateFormat sdf = new SimpleDateFormat("yyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Iin value cannot be null or empty")
                    .addConstraintViolation();
            return false;
        }

        value = StringUtils.trimToEmpty(value);

        if (value.length() != VALID_LENGTH) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Iin length of value must be " + VALID_LENGTH)
                    .addConstraintViolation();
            return false;
        }

        if(!checkOnResidentValue(value.charAt(6) - '0')) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Iin resident value not valid")
                    .addConstraintViolation();
            return false;
        }
        if (!checkOnBirthDateValidation(value.substring(0, 6))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Iin birth date value not valid")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }


}
