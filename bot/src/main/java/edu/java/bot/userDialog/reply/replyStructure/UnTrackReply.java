package edu.java.bot.userDialog.reply.replyStructure;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.requestHandle.ILinkUntrack;
import edu.java.bot.requestHandle.LinkValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnTrackReply implements Reply {
    private static final String invalidLinkMessage = "Неверная ссылка";
    private static final String stopTrackingMessage = "Ссылка больше не отслеживается";
    public static final String untrackCommandExecuteMessage = "Какую ссылку больше не отслеживать?";

    private static final LinkValidator linkValidator = new LinkValidator();
    private final ILinkUntrack linksUntracker;
    @Override
    public String reply() {
        return untrackCommandExecuteMessage;
    }

    @Override
    public SendMessage handle(Update update) {
        if(linkValidator.validateLink(update.message().text())) {
            linksUntracker.unTrack(update.message().chat().id(), update.message().text());
            return new SendMessage(update.message().chat().id(), stopTrackingMessage);
        }

        return new SendMessage(update.message().chat().id(), invalidLinkMessage);
    }
}
