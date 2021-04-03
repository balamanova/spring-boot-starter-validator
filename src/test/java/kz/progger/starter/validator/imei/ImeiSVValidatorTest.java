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

@ExtendWith(MockitoExtension.class)
public class ImeiSVValidatorTest {
    @InjectMocks
    ImeiSVValidator imeiSVValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;
    @Mock
    ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Test
    void isValid_shouldSuccess() {
        assertTrue(imeiSVValidator.isValid("354190023896443", constraintValidatorContext));
        verifyNoInteractions(constraintValidatorContext);
        verifyNoInteractions(constraintViolationBuilder);
    }

    @Test
    void isValid_imeiCVNullOrEmpty() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Imei value cannot be null or empty")).thenReturn(constraintViolationBuilder);
        assertFalse(imeiSVValidator.isValid("", constraintValidatorContext));
        assertFalse(imeiSVValidator.isValid(null, constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }

    @Test
    void isValid_whenIsNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Imei is not valid")).thenReturn(constraintViolationBuilder);
        assertFalse(imeiSVValidator.isValid("123456789123445", constraintValidatorContext));
        verify(constraintValidatorContext).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    @Test
    void isValid_whenLenIsNotValid() {
        when(constraintValidatorContext.buildConstraintViolationWithTemplate("Imei length of value must to be 15")).thenReturn(constraintViolationBuilder);
        assertFalse(imeiSVValidator.isValid("1len", constraintValidatorContext));
        assertFalse(imeiSVValidator.isValid("1234567891011121316023", constraintValidatorContext));
        verify(constraintValidatorContext, times(2)).disableDefaultConstraintViolation();
        verify(constraintViolationBuilder, times(2)).addConstraintViolation();
    }
}
