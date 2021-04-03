package kz.progger.starter.validator.msisdn;

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
public class MsisdnValidatorTest {
    @InjectMocks
    MsisdnValidator msisdnValidator;
    @Mock
    Msisdn constraint;
    @Mock
    ConstraintValidatorContext constraintValidatorContext;
    @Mock
    ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Test
    void isValid_msisdnNullOrEmpty_requiredTrue() {
        when(constraint.required()).thenReturn(true);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Msisdn cannot be null or empty")).thenReturn(constraintViolationBuilder);
        assertFalse(msisdnValidator.isValid("", constraintValidatorContext));
        assertFalse(msisdnValidator.isValid(null, constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }

    @Test
    void isValid_msisdnNullOrEmpty_requiredFalse() {
        when(constraint.required()).thenReturn(false);
        assertTrue(msisdnValidator.isValid("", constraintValidatorContext));
        assertTrue(msisdnValidator.isValid(null, constraintValidatorContext));
        verifyNoInteractions(constraintValidatorContext);
        verifyNoInteractions(constraintViolationBuilder);
    }

    @Test
    void isValid_whenMsisdnIsNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Illegal msisdn value")).thenReturn(constraintViolationBuilder);
        assertFalse(msisdnValidator.isValid("1len", constraintValidatorContext));
        assertFalse(msisdnValidator.isValid("12345678910111213", constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintValidatorContext, times(2)).buildConstraintViolationWithTemplate("Illegal msisdn value");
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
        verifyNoInteractions(constraint);
    }

    @Test
    void isValid_shouldSuccess() {
        assertTrue(msisdnValidator.isValid("77018128739", constraintValidatorContext));
        verifyNoInteractions(constraintValidatorContext);
        verifyNoInteractions(constraintViolationBuilder);
        verifyNoInteractions(constraint);
    }
}
