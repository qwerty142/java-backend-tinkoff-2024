package edu.java.bot.userDialog.message;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.ICommand;
import java.util.List;

public interface UserMessageProcessor {
    List<? extends ICommand> commands();

    SendMessage process(Update update);
}
