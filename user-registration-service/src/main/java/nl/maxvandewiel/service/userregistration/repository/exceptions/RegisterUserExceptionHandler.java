package nl.maxvandewiel.service.userregistration.repository.exceptions;

import nl.maxvandewiel.service.userregistration.restfull.model.RegistrationResponse;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * Created by max on 6/5/16.
 */
@ControllerAdvice
public class RegisterUserExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserExceptionHandler.class);

    @Autowired(required = true)
    private MessageSource messageSource;

    @ExceptionHandler(RegisterUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    RegistrationResponse handleRegisterUserException() {
        RegistrationResponse response = new RegistrationResponse();
        Integer code = resolveLocalizedMessageToInteger("exception.register.user.errorcode");
        String message = resolveLocalizedMessage("exception.register.user");
        response.addRegistrationError(code, message);
        return response;
    }

    @ExceptionHandler(UserAllreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    RegistrationResponse handleUserAllreadyExistsException() {
        RegistrationResponse response = new RegistrationResponse();
        Integer code = resolveLocalizedMessageToInteger("exception.user.allready.exists.errorcode");
        String message = resolveLocalizedMessage("exception.user.allready.exists");
        response.addRegistrationError(code, message);
        return response;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    RegistrationResponse userNotFoundException() {
        RegistrationResponse response = new RegistrationResponse();
        Integer code = resolveLocalizedMessageToInteger("exception.user.not.found.errorcode");
        String message = resolveLocalizedMessage("exception.user.not.found");
        response.addRegistrationError(code, message);
        return response;
    }

    private String resolveLocalizedMessage(String key) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(key, null, currentLocale);
        return localizedErrorMessage;
    }

    private Integer resolveLocalizedMessageToInteger(String key) {
        Integer retVal = null;
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(key, null, currentLocale);
        if (NumberUtils.isNumber(localizedErrorMessage)) {
            retVal = new Integer(localizedErrorMessage);
        }
        return retVal;
    }
}
