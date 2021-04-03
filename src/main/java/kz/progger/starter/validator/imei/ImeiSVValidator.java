package kz.progger.starter.validator.imei;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author assem.balamanova
 * @created 31/01/2020 - 11:51
 */
public class ImeiSVValidator implements ConstraintValidator<ImeiSV, String> {

    private static final int VALID_LENGTH = 15;

    static int sumDig(int n) {
        int a = 0;
        while (n > 0) {
            a = a + n % 10;
            n = n / 10;
        }
        return a;
    }

    @Override
    public void initialize(ImeiSV constraintAnnotation) {

    }

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

        if (!isValidIMEI(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Imei is not valid")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean isValidIMEI(String string) {
        int c = 0;
        int sum = 0;
        boolean second = false;
        int length = string.length();

        for (int i = length - 1; i >= 0; i--) {
            c = Integer.parseInt("" + string.charAt(i));
            if (second == true)
                c = c * 2;

            sum += c / 10;
            sum += c % 10;
            second = !second;

        }

        return (sum % 10 == 0);
    }
}
