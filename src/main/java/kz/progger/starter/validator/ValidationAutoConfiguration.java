package kz.progger.starter.validator;

import kz.progger.starter.validator.bin.BinValidator;
import kz.progger.starter.validator.date.LessThanOrEqualTodayValidator;
import kz.progger.starter.validator.date.PeriodValidator;
import kz.progger.starter.validator.iin.IinValidator;
import kz.progger.starter.validator.imei.ImeiSVValidator;
import kz.progger.starter.validator.imei.ImeiValidator;
import kz.progger.starter.validator.msisdn.InvalidMsisdnException;
import kz.progger.starter.validator.msisdn.MsisdnValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author assem.balamanova
 * @created 31/01/2020 - 15:30
 */

@Configuration
@ComponentScan(basePackages = "kz.kcell.starter.validator.*")
@ConditionalOnMissingBean({
    ImeiSVValidator.class,
    ImeiValidator.class,
    BinValidator.class,
    IinValidator.class,
    InvalidMsisdnException.class,
    PeriodValidator.class,
    LessThanOrEqualTodayValidator.class,
    MsisdnValidator.class})
public class ValidationAutoConfiguration {
}
