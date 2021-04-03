package kz.progger.starter.validator.bin;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BinValidatorTest {
    @InjectMocks
    BinValidator binValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;
    @Mock
    ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Test
    void isValid_binNullOrEmpty() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Bin value cannot be null or empty")).thenReturn(constraintViolationBuilder);
        assertFalse(binValidator.isValid("", constraintValidatorContext));
        assertFalse(binValidator.isValid(null, constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }

    @Test
    void isValid_whenLenIsNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Bin length of value must be 12")).thenReturn(constraintViolationBuilder);
        assertFalse(binValidator.isValid("1len", constraintValidatorContext));
        assertFalse(binValidator.isValid("12345678910111213", constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }

    @Test
    void isValid_whenFifthValNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Not valid Bin ")).thenReturn(constraintViolationBuilder);
        assertFalse(binValidator.isValid("100630014963", constraintValidatorContext));
        verify(constraintValidatorContext).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    @Test
    void isValid_shouldSuccess() {
        assertTrue(binValidator.isValid("100640014963", constraintValidatorContext));
        verifyNoInteractions(constraintValidatorContext);
        verifyNoInteractions(constraintViolationBuilder);
    }
}
