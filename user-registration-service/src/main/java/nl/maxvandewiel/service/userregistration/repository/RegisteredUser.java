package nl.maxvandewiel.service.userregistration.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

/**
 * Created by max on 6/4/16.
 */
@Document(collection = "users")
public class RegisteredUser {
    @Id
    private BigInteger id;

    private String username;

    private String password;

    private String emailAddress;

    public RegisteredUser(String username, String password, String emailAddress) {
        super();
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @PersistenceConstructor
    public RegisteredUser(BigInteger id, String username, String password, String emailAddress) {
        super();
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public BigInteger getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', emailAddress='%s']",
                id, username, emailAddress);
    }
}