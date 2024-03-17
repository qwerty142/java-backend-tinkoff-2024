package edu.java.bot.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackoverflowLinkValidator implements LinkValidator {
    private static final Pattern PATTERN =
        Pattern.compile("https?://stackoverflow.com/questions/\\d+/.+", Pattern.CASE_INSENSITIVE);
    private LinkValidator nextValidator;

    @Override
    public boolean isValid(String link) {
        Matcher matcher = PATTERN.matcher(link);
        if (matcher.matches()) {
            return true;
        }

        return nextValidator != null && nextValidator.isValid(link);
    }

    @Override
    public void next(LinkValidator nextValidator) {
        this.nextValidator = nextValidator;
    }
}
