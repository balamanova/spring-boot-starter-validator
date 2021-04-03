package kz.progger.starter.validator.iin;

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
public class IinValidatorTest {
    @InjectMocks
    IinValidator iinValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;
    @Mock
    ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Test
    void isValid_shouldSuccess() {
        assertTrue(iinValidator.isValid("911217500090", constraintValidatorContext));
        verifyNoInteractions(constraintValidatorContext);
        verifyNoInteractions(constraintViolationBuilder);
    }

    @Test
    void isValid_birthDateNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Iin birth date value not valid")).thenReturn(constraintViolationBuilder);
        assertFalse(iinValidator.isValid("911317500080", constraintValidatorContext));
        verify(constraintValidatorContext).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    @Test
    void isValid_onResident() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Iin resident value not valid")).thenReturn(constraintViolationBuilder);
        assertFalse(iinValidator.isValid("910217900080", constraintValidatorContext));
        verify(constraintValidatorContext).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    @Test
    void isValid_iinNullOrEmpty() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Iin value cannot be null or empty")).thenReturn(constraintViolationBuilder);
        assertFalse(iinValidator.isValid("", constraintValidatorContext));
        assertFalse(iinValidator.isValid(null, constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }

    @Test
    void isValid_whenLenIsNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Iin length of value must be 12")).thenReturn(constraintViolationBuilder);
        assertFalse(iinValidator.isValid("1len", constraintValidatorContext));
        assertFalse(iinValidator.isValid("12345678910111213", constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }
}
