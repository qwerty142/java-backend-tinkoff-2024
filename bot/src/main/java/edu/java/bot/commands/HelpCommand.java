package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements ICommand{
    private static final String HELP_COMMAND = "/help";
    private static final String HELP_DESCRIPTION = "помощь";
    private List<ICommand> commands;

    public HelpCommand(@NonNull List<ICommand> commands){
        this.commands = commands;
    }

    public HelpCommand(){
        this.commands = new ArrayList<>();
    }
    @Override
    public String command() {
        return HELP_COMMAND;
    }

    @Override
    public String description() {
        return HELP_DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        var stringBuilder = new StringBuilder();
        for(var command : commands) {
            stringBuilder.append(command.command());
            stringBuilder.append('\n');
        }
        return new SendMessage(update.message().chat().id(), stringBuilder.toString()).parseMode(ParseMode.Markdown);
    }
}
