package kz.progger.starter.validator.imei;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ImeiValidatorTest {
    @InjectMocks
    ImeiValidator imeiValidator;
    @Mock
    ConstraintValidatorContext constraintValidatorContext;
    @Mock
    ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Test
    void isValid_shouldSuccess() {
        assertTrue(imeiValidator.isValid("91031807597061", constraintValidatorContext));
        verifyNoInteractions(constraintValidatorContext);
        verifyNoInteractions(constraintViolationBuilder);
    }

    @Test
    void isValid_imeiNullOrEmpty() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Imei value cannot be null or empty")).thenReturn(constraintViolationBuilder);
        assertFalse(imeiValidator.isValid("", constraintValidatorContext));
        assertFalse(imeiValidator.isValid(null, constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }

    @Test
    void isValid_whenLenIsNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Imei length of value must to be 14")).thenReturn(constraintViolationBuilder);
        assertFalse(imeiValidator.isValid("1len", constraintValidatorContext));
        assertFalse(imeiValidator.isValid("123456789101112131623", constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }

}
