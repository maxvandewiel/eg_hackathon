package nl.maxvandewiel.service.userregistration.restfull.model;

/**
 * Created by max on 6/4/16.
 */
public class Message {
    String name;
    String text;

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
