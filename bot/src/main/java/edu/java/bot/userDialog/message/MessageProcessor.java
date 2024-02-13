package edu.java.bot.userDialog.message;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ICommand;
import edu.java.bot.commands.ListCommand;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.commands.TrackCommand;
import edu.java.bot.commands.UntrackCommand;
import edu.java.bot.requestHandle.LinkRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("standartMessageProcessor")
public class MessageProcessor implements UserMessageProcessor {
    private final String helpCommandUnavailableMessage = "Недоступно";
    private final String unsupportedCommandMessage = "Неподдерживаемая команда";
    private final List<ICommand> commands;

    public MessageProcessor(
        @Autowired LinkRepository linkRepository
    ) {
        var cmd = new ArrayList<>(
        List.of(
            new TrackCommand(),
            new UntrackCommand(),
            new StartCommand(linkRepository),
            new ListCommand(linkRepository)
        )
        );
       cmd.add(new HelpCommand(cmd));
       commands = cmd;
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
