package edu.java.bot.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubLinkValidator implements LinkValidator {

    private static final Pattern PATTERN = Pattern.compile("https?://github.com/[^/]+/[^/]+", Pattern.CASE_INSENSITIVE);
    private LinkValidator nextValidator = null;

    @Override
    public boolean isValid(String link) {
        Matcher matcher = PATTERN.matcher(link);
        if (matcher.matches()) {
            return true;
        }

        return nextValidator != null && nextValidator.isValid(link);
    }

    @Override
    public void next(LinkValidator next) {
        this.nextValidator = next;
    }
}
