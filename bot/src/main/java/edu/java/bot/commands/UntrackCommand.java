package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;

public class UntrackCommand implements ICommand {
    public final String untrackCommand = "/untrack";
    public final String untrackCommandDescription = "прекратить отслеживание ссылки";
    public final String untrackCommandExecuteMessage = "Какую ссылку перестанем отслеживать?";

    @Override
    public String command() {
        return untrackCommand;
    }

    @Override
    public String description() {
        return untrackCommandDescription;
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(
            update.message().chat().id(),
            untrackCommandExecuteMessage
        ).replyMarkup(new ForceReply());
    }
}
