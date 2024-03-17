package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface ICommand {
    String command();

    String description();

    SendMessage handle(Update update);

    default boolean supports(Update update) {
        return update != null
            && update.message() != null
            && update.message().text() != null
            && update.message().text().startsWith(command());
    }
}
