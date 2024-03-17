package edu.java.bot.userDialog.reply.replyStructure;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.requestHandle.ILinkTrack;
import edu.java.bot.requestHandle.LinkValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor()
public class TrackReply implements Reply {
    private final String invalidLinkMessage = "Неверная ссылка";
    private final String beginTrackingMessage = "Ссылка добавлена в список ";
    public final String trackCommandExecuteMessage = "Какую ссылку будем отслеживать?";

    private final LinkValidator linkValidator;
    private final ILinkTrack linksTracker;

    @Override
    public String reply() {
        return trackCommandExecuteMessage;
    }

    @Override
    public SendMessage handle(Update update) {
        if (linkValidator.validateLink(update.message().text())) {
            linksTracker.track(update.message().chat().id(), update.message().text());
            return new SendMessage(update.message().chat().id(), beginTrackingMessage);
        }

        return new SendMessage(update.message().chat().id(), invalidLinkMessage);
    }
}
