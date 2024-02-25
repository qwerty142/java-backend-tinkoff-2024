package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HelpCommand implements ICommand {
    private static final String HELP_COMMAND = "/help";
    private static final String HELP_DESCRIPTION = "помощь";
    private List<ICommand> commands;

    public HelpCommand() {
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
        for (var command : commands) {
            stringBuilder.append(command.command());
            stringBuilder.append(" ");
            stringBuilder.append(command.description());
            stringBuilder.append('\n');
        }
        return new SendMessage(update.message().chat().id(), stringBuilder.toString());
    }
}
