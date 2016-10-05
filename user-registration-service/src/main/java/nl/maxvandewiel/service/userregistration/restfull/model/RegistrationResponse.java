package nl.maxvandewiel.service.userregistration.restfull.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import nl.maxvandewiel.service.userregistration.validation.ValidationError;
import nl.maxvandewiel.service.userregistration.validation.ValidationMessageType;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
/**
 * Created by max on 6/4/16.
 */
@JsonInclude(NON_NULL)
public class RegistrationResponse extends RegistrationRequest {
    private List<RegistrationError> registrationErrors = null;

    private List<ValidationError> validationErrors = null;

    public void addRegistrationError(@NotNull Integer errorCode, @NotNull String errorMessage) {
        if (this.registrationErrors == null) {
            this.registrationErrors = new ArrayList<RegistrationError>();
        }
        registrationErrors.add(new RegistrationError(errorCode, errorMessage));
    }

    public void addValidationError(String field, @NotNull String message, @NotNull ValidationMessageType type) {
        if (this.validationErrors == null) {
            this.validationErrors = new ArrayList<ValidationError>();
        }
        validationErrors.add(new ValidationError(field, message, type));
    }

    public List<RegistrationError> getRegistrationErrors() {
        return registrationErrors;
    }

    public void setRegistrationErrors(List<RegistrationError> registrationErrors) {
        this.registrationErrors = registrationErrors;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}