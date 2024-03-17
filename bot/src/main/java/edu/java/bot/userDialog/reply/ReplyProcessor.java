package edu.java.bot.userDialog.reply;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.userDialog.reply.replyStructure.Reply;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("standartReplyProcessor")
public class ReplyProcessor implements UserReplyProcessor {
    private final String unsupportedReplyMessage = "Ошибка";
    private final List<? extends Reply> replies;

    @Autowired
    public ReplyProcessor(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public List<? extends Reply> replies() {
        return replies;
    }

    @SuppressWarnings("checkstyle:LineLength") @Override
    public SendMessage process(Update update) {
        /*return new SendMessage(update.message().chat().id(), update.message().replyToMessage().text() + replies.get(0).reply());*/

        for (var reply : replies) {
            if (reply.supports(update)) {
                return reply.handle(update);
            }
        }
        return new SendMessage(update.message().chat().id(), unsupportedReplyMessage);
    }
}
