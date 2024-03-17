package edu.java.bot.userDialog.reply.replyStructure;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.requestHandle.ILinkUntrack;
import edu.java.bot.requestHandle.LinkValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor()
public class UnTrackReply implements Reply {
    private final String invalidLinkMessage = "Неверная ссылка";
    private final String stopTrackingMessage = "Ссылка больше не отслеживается";
    public final String untrackCommandExecuteMessage = "Какую ссылку перестанем отслеживать?";

    private final LinkValidator linkValidator;
    private final ILinkUntrack linksUntracker;

    @Override
    public String reply() {
        return untrackCommandExecuteMessage;
    }

    @Override
    public SendMessage handle(Update update) {
        if (linkValidator.validateLink(update.message().text())) {
            linksUntracker.unTrack(update.message().chat().id(), update.message().text());
            return new SendMessage(update.message().chat().id(), stopTrackingMessage);
        }

        return new SendMessage(update.message().chat().id(), invalidLinkMessage);
    }
}
