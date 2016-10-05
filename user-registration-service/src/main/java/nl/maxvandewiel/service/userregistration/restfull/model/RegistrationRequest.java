package nl.maxvandewiel.service.userregistration.restfull.model;

import nl.maxvandewiel.service.userregistration.validation.constraints.ValidEmailAddress;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by max on 6/4/16.
 */
public class RegistrationRequest {
    @NotNull(message = "invalid.username.null")
    @Size(min = 4, max = 15,
            message = "invalid.username.size")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,15}$",
            message = "invalid.username.pattern")
    private String username;

    @NotNull(message = "invalid.email.null")
    @Size(min = 4, max = 125,
            message = "invalid.email.size")
    @ValidEmailAddress(message = "invalid.email.pattern")
    private String emailAddress;

    @NotNull(message = "invalid.password.null")
    @Size(min = 8, max = 30,
            message = "invalid.password.size")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{8,30}$",
            message = "invalid.password.pattern")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
