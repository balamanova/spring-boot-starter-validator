package kz.progger.starter.validator.date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PeriodValidator implements ConstraintValidator<ValidatablePeriod, Object> {

    private String startPeriodFieldName;
    private String endPeriodFieldName;
    private Integer maxPeriodDiff;

    @Autowired
    private Environment environment;

    @Override
    public void initialize(ValidatablePeriod constraintAnnotation) {
        startPeriodFieldName = constraintAnnotation.startPeriodField();
        endPeriodFieldName = constraintAnnotation.endPeriodField();
        maxPeriodDiff = Integer.valueOf(environment.getProperty(constraintAnnotation.periodEnvName()));
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Class clazz = value.getClass();
        try {
            Field startPeriodField = getField(clazz, startPeriodFieldName);
            Field endPeriodField = getField(clazz, endPeriodFieldName);
            if (startPeriodField == null || endPeriodField == null) {
                throw new IllegalAccessException();
            }
            LocalDate startPeriodDate = (LocalDate) startPeriodField.get(value);
            LocalDate endPeriodDate = (LocalDate) endPeriodField.get(value);
            if (startPeriodDate != null && endPeriodDate != null) {
                if (startPeriodDate.isAfter(endPeriodDate)) {
                    return false;
                }
                LocalDate today = LocalDate.now();
                long diffBetweenStartDateAndToday = ChronoUnit.DAYS.between(startPeriodDate, today);
                if (diffBetweenStartDateAndToday > maxPeriodDiff) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            log.error("Cannot get field value from object.");
            throw new RuntimeException("PeriodValidator cannot access required model field.");
        }
        return true;
    }

    private Field getField(Class currentClass, String fieldName) {
        Field field = null;
        String className = currentClass.getCanonicalName();
        List<String> classChain = new ArrayList<>();
        while (field == null || !Object.class.equals(currentClass)) {
            classChain.add(className);
            try {
                field = currentClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                log.debug("Field [{}] found in class chain [{}]", fieldName, String.join(" -> ", classChain));
                return field;
            } catch (Exception e) {
            }
            currentClass = currentClass.getSuperclass();
            className = currentClass.getCanonicalName();
        }
        log.error("Not found field [{}] in class chain = {}", fieldName, String.join(" -> ", classChain));
        return field;
    }

}
