package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;

public class TrackCommand implements ICommand {
    public final String trackCommand = "/track";
    public final String trackCommandDescription = "начать отслеживание ссылки";
    public final String trackCommandExecuteMessage = "Какую ссылку будем отслеживать?";

    @Override
    public String command() {
        return trackCommand;
    }

    @Override
    public String description() {
        return trackCommandDescription;
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), trackCommandExecuteMessage).replyMarkup(new ForceReply());
    }
}
