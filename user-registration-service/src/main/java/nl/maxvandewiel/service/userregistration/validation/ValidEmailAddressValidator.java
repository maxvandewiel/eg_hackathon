package nl.maxvandewiel.service.userregistration.validation;

import nl.maxvandewiel.service.userregistration.validation.constraints.ValidEmailAddress;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by max on 6/4/16.
 */
public class ValidEmailAddressValidator implements ConstraintValidator<ValidEmailAddress, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidEmailAddressValidator.class);

    public void initialize(ValidEmailAddress constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        LOGGER.info("validating email address {}", value);
        return EmailValidator.getInstance().isValid(value);
    }
}
