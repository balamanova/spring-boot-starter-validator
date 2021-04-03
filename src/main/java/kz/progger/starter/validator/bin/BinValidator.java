package kz.progger.starter.validator.bin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * C
 *
 * @author assem.balamanova
 * @created 30/01/2020 - 18:47
 */
public class BinValidator implements ConstraintValidator<Bin, String> {

    private static final int VALID_LENGTH = 12;
    private static final int[] checkArr1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private static final int[] checkArr2 = new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 2};

    private boolean checkSumStep(String value, int[] checkArr) {
        int sum = 0;
        int length = value.length();
        for (int i = 0; i < length - 1; i++) {
            sum += (value.charAt(i) - '0') * checkArr[i];
        }
        return (sum % 11) % 10 == (value.charAt(length - 1) - '0');
    }

    private boolean checkOnFifthValue(int fifthValue) {
        return fifthValue >= 4 && fifthValue <= 6;
    }

    @Override
    public void initialize(Bin constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Bin value cannot be null or empty")
                .addConstraintViolation();
            return false;
        }

        if (StringUtils.length(value) != VALID_LENGTH) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Bin length of value must be " + VALID_LENGTH)
                .addConstraintViolation();
            return false;
        }

        if(!checkOnFifthValue(value.charAt(4) - '0')) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Not valid Bin ")
                    .addConstraintViolation();
            return false;
        }

        value = StringUtils.trimToEmpty(value);

        return checkSumStep(value, checkArr1) || checkSumStep(value, checkArr2);
    }


}

