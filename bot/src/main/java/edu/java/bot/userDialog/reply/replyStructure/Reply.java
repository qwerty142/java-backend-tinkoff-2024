package edu.java.bot.userDialog.reply.replyStructure;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Reply {
    String reply();

    SendMessage handle(Update update);

    default boolean supports(Update update) {
        return
            update.message() != null
            && update.message().replyToMessage() != null
                && update.message().replyToMessage().text().equals(reply());
    }
}
