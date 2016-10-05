package nl.maxvandewiel.service.userregistration.validation;

/**
 * Created by max on 6/5/16.
 */
public class ValidationError {
    private String field;
    private String message;
    private ValidationMessageType type;

    public ValidationError() {
        super();
    }

    public ValidationError(String field, String message, ValidationMessageType type) {
        super();
        this.field = field;
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidationMessageType getType() {
        return type;
    }

    public void setType(ValidationMessageType type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
