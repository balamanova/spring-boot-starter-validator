package kz.progger.starter.validator.date;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Slf4j
public class LessThanOrEqualTodayValidator implements ConstraintValidator<LessThanOrEqualToday, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (LocalDate.now().isBefore(value)) {
            return false;
        }
        return true;
    }
}
