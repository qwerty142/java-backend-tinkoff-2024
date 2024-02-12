package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.requestHandle.LinkRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StartCommand implements ICommand{
    private final String startCommand = "/start";
    private final String startDescription = "Комманда регестрирует пользователя";
    private final String executeMessage = "Готово";
    private final LinkRepository linkRepository;
    @Override
    public String command() {
        return startCommand;
    }

    @Override
    public String description() {
        return startDescription;
    }

    @Override
    public SendMessage handle(Update update) {
        linkRepository.register(update.message().chat().id());
        return new SendMessage(update.message().chat().id(), executeMessage);
    }
}
