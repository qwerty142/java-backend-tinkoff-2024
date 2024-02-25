package edu.java.bot.userDialog.message;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.ICommand;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("standartMessageProcessor")
public class MessageProcessor implements UserMessageProcessor {
    private final String helpCommandUnavailableMessage = "Недоступно";
    private final String unsupportedCommandMessage = "Неподдерживаемая команда";
    private final List<ICommand> commands;

    @Autowired
    public MessageProcessor(
        List<ICommand> commands
    ) {
        this.commands = commands;
    }

    @Override
    public List<? extends ICommand> commands() {
        return commands;
    }

    @Override
    public SendMessage process(Update update) {
        for (var command : commands) {
            if (command.supports(update)) {
                return command.handle(update);
            }
        }
        return new SendMessage(update.message().chat().id(), unsupportedCommandMessage);
    }
}
