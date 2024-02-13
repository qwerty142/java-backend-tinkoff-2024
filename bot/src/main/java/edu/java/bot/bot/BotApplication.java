package edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.botcommandscope.BotCommandScopeDefault;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import edu.java.bot.userDialog.message.UserMessageProcessor;
import edu.java.bot.userDialog.reply.UserReplyProcessor;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BotApplication implements AutoCloseable, UpdatesListener {
    private final TelegramBot bot;
    private final UserMessageProcessor userMessageProcessor;
    private final UserReplyProcessor userReplyProcessor;

    public BotApplication(
        @Value("${app.telegram-token}") String token,
        @Qualifier("standartMessageProcessor") UserMessageProcessor userMessageProcessor,
        @Qualifier("standartReplyProcessor") UserReplyProcessor userReplyProcessor
    ) {
        this.bot = new TelegramBot(token);
        this.userMessageProcessor = userMessageProcessor;
        this.userReplyProcessor = userReplyProcessor;
        start();
    }

    public void start() {
        bot.setUpdatesListener(this);
        setMyCommandsMenu();
    }

    <T extends BaseRequest<T, R>, R extends BaseResponse>

    void execute(BaseRequest<T, R> request) {
        bot.execute(request);
    }

    @Override
    public int process(List<Update> listUpdate) {
            for (var update : listUpdate) {
                SendMessage request =
                    isReply(update) ? userReplyProcessor.process(update) : userMessageProcessor.process(update);
                execute(request);
            }

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void close() throws Exception {

    }

    private void setMyCommandsMenu() {
        /*List<BotCommand> com = new ArrayList<>();
        com.add(new BotCommand("/start", "abab"));*/
        BotCommand[] commands = userMessageProcessor.commands()
            .stream()
            .map(x -> new BotCommand(x.command(), x.description()))
            .toArray(BotCommand[]::new);

        SetMyCommands cmds = new SetMyCommands(commands);
        cmds.languageCode("ru");
        cmds.scope(new BotCommandScopeDefault());

        bot.execute(new SetMyCommands(commands));
    }

    private boolean isReply(Update update) {
        Message reply = update.message().replyToMessage();
        return reply != null;
    }
}
