package edu.java.bot.userDialog.reply;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.userDialog.reply.replyStructure.Reply;
import java.util.List;

public interface UserReplyProcessor {
    List<? extends Reply> replies();

    SendMessage process(Update update);
}
