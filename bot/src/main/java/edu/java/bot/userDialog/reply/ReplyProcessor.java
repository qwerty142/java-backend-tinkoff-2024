package edu.java.bot.userDialog.reply;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.requestHandle.LinkRepository;
import edu.java.bot.userDialog.reply.replyStructure.Reply;
import edu.java.bot.userDialog.reply.replyStructure.TrackReply;
import edu.java.bot.userDialog.reply.replyStructure.UnTrackReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("standartReplyProcessor")
public class ReplyProcessor implements UserReplyProcessor{
    private static final String unsupportedReplyMessage = "Ошибка";
    private final List<? extends Reply> replies;

    public ReplyProcessor(@Autowired LinkRepository linkRepository) {
        this.replies = List.of(
            new TrackReply(linkRepository),
            new UnTrackReply(linkRepository)
        );
    }

    @Override
    public List<? extends Reply> replies() {
        return replies;
    }

    @Override
    public SendMessage process(Update update) {
        for(var reply : replies) {
            if(reply.supports(update)) {
                return reply.handle(update);
            }
        }
        return new SendMessage(update.message().chat().id(), unsupportedReplyMessage);
    }
}
