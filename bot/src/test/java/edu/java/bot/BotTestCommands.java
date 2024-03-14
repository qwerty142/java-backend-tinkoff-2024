package edu.java.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import edu.java.bot.bot.BotApplication;
import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ICommand;
import edu.java.bot.commands.ListCommand;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.commands.TrackCommand;
import edu.java.bot.commands.UntrackCommand;
import edu.java.bot.requestHandle.LinkRepository;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BotTestCommands {
    static Stream<Arguments> correctCommand() {
        return Stream.of(
            Arguments.of(new HelpCommand(), "/help", "помощь"),
            Arguments.of(new ListCommand(new LinkRepository()), "/list", "показать список отслеживаемых ссылок"),
            Arguments.of(new StartCommand(new LinkRepository()), "/start", "Комманда регестрирует пользователя"),
            Arguments.of(new TrackCommand(), "/track", "начать отслеживание ссылки"),
            Arguments.of(new UntrackCommand(), "/untrack", "прекратить отслеживание ссылки")
        );
    }

    @ParameterizedTest
    @MethodSource("correctCommand")
    public void commandsShouldKnowThereNameAndDescription(ICommand command, String name, String description) {
        assertThat(command.command()).isEqualTo(name);
        assertThat(command.description()).isEqualTo(description);
    }
}
