package nl.maxvandewiel.service.userregistration.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import nl.maxvandewiel.service.userregistration.restfull.model.RegistrationResponse;

import java.util.List;
import java.util.Locale;

/**
 * Created by max on 6/5/16.
 */
@ControllerAdvice
public class ValidationExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);
    @Autowired(required = true)
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RegistrationResponse processValidationError(MethodArgumentNotValidException ex) {
        LOGGER.info("handling validation errors");
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        return processFieldErrors(errors);
    }

    private RegistrationResponse processFieldErrors(List<FieldError> errors) {
        RegistrationResponse messages = new RegistrationResponse();
        if (errors != null) {
            for (FieldError error: errors) {
                String msg = resolveLocalizedErrorMessage(error);
                messages.addValidationError(error.getField(), msg, ValidationMessageType.ERROR);
            }
        }
        return messages;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError.getDefaultMessage());
        //If the message was not found, return the most accurate field error code instead.
        //Remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }
        return localizedErrorMessage;
    }

    private String resolveLocalizedErrorMessage(String messageCode) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(messageCode, null, currentLocale);
        return localizedErrorMessage;
    }
}