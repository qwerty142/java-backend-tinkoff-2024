package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import edu.java.bot.bot.BotApplication;
import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ICommand;
import edu.java.bot.requestHandle.LinkRepository;
import edu.java.bot.userDialog.message.MessageProcessor;
import edu.java.bot.userDialog.reply.ReplyProcessor;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import static org.assertj.core.api.Assertions.as;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BotWorkTest {
    private Long id = 0L;
    Update updateSimulation = Mockito.mock(Update.class);
    TelegramBot bot = Mockito.mock(TelegramBot.class);
    BotApplication botApplication;
    LinkRepository repository;
    @Captor
    ArgumentCaptor<? extends BaseRequest<SendMessage, SendResponse>> messageCaptor;

    void setupTestFields() {
        repository = Mockito.mock(LinkRepository.class);
        var messageProcessor = new MessageProcessor(repository);
        var replyProcessor = new ReplyProcessor(repository);
        botApplication = new BotApplication("token", messageProcessor, replyProcessor);
        // botApplication.bot = bot;
        ReflectionTestUtils.setField(botApplication, "bot", bot);
    }

    void setupUpdate(String initText) {
        var message = Mockito.mock(Message.class);
        var chat = Mockito.mock(Chat.class);
        when(updateSimulation.message()).thenReturn(message);
        when(updateSimulation.message().text()).thenReturn(initText);
        when(updateSimulation.message().chat()).thenReturn(chat);
        when(updateSimulation.message().chat().id()).thenReturn(id);
    }

    void setupReply(String reply) {
        var message = Mockito.mock(Message.class);
        when(updateSimulation.message().replyToMessage()).thenReturn(message);
        when(message.text()).thenReturn(reply);
    }
    String commandExecutor(String command, boolean reply, String replyText) {
        setupUpdate(command);
        if (reply) {
            setupReply(replyText);
        }

        // SendMessage res = botApplication.process(List.of(updateSimulation));
        Mockito.verify(bot).execute(messageCaptor.capture());
        BaseRequest<SendMessage, SendResponse> result = messageCaptor.getValue();
        return result.getParameters().get("text").toString();
    }

    /*<-----------------------------------------------TESTS----------------------------------------------->*/

    /*<-----------------------------------------------HELP----------------------------------------------->*/

    @Test
    public void testMessageProcessorHelp() {
        setupUpdate("/help");
        String res = "/track\n/untrack\n/start\n/list\n/help\n";
        MessageProcessor processor = new MessageProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo(res);
    }

    /*<-----------------------------------------------LIST----------------------------------------------->*/

    @Test
    public void testMessageProcessorList() {
        setupTestFields();
        setupUpdate("/list");
        MessageProcessor processor = new MessageProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Нет отслеживаемых ссылок");
    }

    /*<-----------------------------------------------START----------------------------------------------->*/

    @Test
    public void testMessageProcessorStart() {
        setupTestFields();
        setupUpdate("/start");
        MessageProcessor processor = new MessageProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Готово");
    }

    /*<-----------------------------------------------TRACK----------------------------------------------->*/

    @Test
    public void testMessageProcessorTrack() {
        setupUpdate("/track");
        MessageProcessor processor = new MessageProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Какую ссылку будем отслеживать?");
    }

    /*<-----------------------------------------------UNTRACK----------------------------------------------->*/

    @Test
    public void testMessageProcessorUntrack() {
        setupUpdate("/untrack");
        MessageProcessor processor = new MessageProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Какую ссылку перестанем отслеживать?");
    }

    /*<-----------------------------------------------INVALID COMMAND----------------------------------------------->*/

    @Test
    public void testMessageProcessorInvalidCommand() {
        setupUpdate("/abacaba");
        MessageProcessor processor = new MessageProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Неподдерживаемая команда");
    }

    /*<-----------------------------------------------REPLY TEST TRACK----------------------------------------------->*/

    @Test
    public void testReplyProcessorTrackLink() {
        setupTestFields();
        setupUpdate("Введите ссылку, которую хотите отслеживать");
        setupReply("Введите ссылку, которую хотите отслеживать");
        ReplyProcessor processor = new ReplyProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Ссылка добавлена в список ");
    }

    /*<-----------------------------------------------REPLY TEST UNTRACK----------------------------------------------->*/

    @Test
    public void testReplyProcessorUntrackLink() {
        setupTestFields();
        setupUpdate("Какую ссылку больше не отслеживать?");
        setupReply("Какую ссылку больше не отслеживать?");
        ReplyProcessor processor = new ReplyProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Ссылка больше не отслеживается");
    }

    /*<-----------------------------------------------REPLY TEST UNSUPREPLY----------------------------------------------->*/

    @Test
    public void testReplyProcessorFail() {
        setupTestFields();
        setupUpdate("aba");
        setupReply("aba");
        ReplyProcessor processor = new ReplyProcessor(repository);
        SendMessage message = processor.process(updateSimulation);
        assertThat(message.getParameters().get("text")).isEqualTo("Ошибка");
    }

    @Test
    public void workTest() {
        ICommand command = new HelpCommand();
        assertThat(command.description()).isEqualTo("помощь");
    }
}
