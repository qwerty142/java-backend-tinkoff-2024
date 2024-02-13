package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.requestHandle.ILinkProvider;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListCommand implements ICommand {
    private final String listCommand = "/list";
    private final String listCommandDescription = "показать список отслеживаемых ссылок";
    private final String executeMessage = "Отслеживаются следующие ссылки:\n\n";
    private final String noLinksMessage = "Нет отслеживаемых ссылок";
    private final ILinkProvider linksProvider;

    @Override
    public String command() {
        return listCommand;
    }

    @Override
    public String description() {
        return listCommandDescription;
    }

    @Override
    public SendMessage handle(Update update) {
        if (linksProvider.getLinks(update.message().chat().id()).isEmpty()) {
            return new SendMessage(update.message().chat().id(), noLinksMessage);
        }

        var stringBuilder = new StringBuilder();
        stringBuilder.append(executeMessage);

        for (var link : linksProvider.getLinks(update.message().chat().id())) {
            stringBuilder.append(link);
            stringBuilder.append('\n');
            stringBuilder.append('\n');
        }

        return new SendMessage(update.message().chat().id(), stringBuilder.toString()).disableWebPagePreview(true);
    }
}
