package nl.maxvandewiel.service.userregistration.restfull.model;

/**
 * Created by max on 6/4/16.
 */
public class RegistrationError {
    private String errorMessage;
    private Integer errorCode;

    public RegistrationError() {
        super();
    }

    public RegistrationError(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}