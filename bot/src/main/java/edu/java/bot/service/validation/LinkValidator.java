package edu.java.bot.service.validation;

public interface LinkValidator {
    boolean isValid(String link);

    void next(LinkValidator next);
}
