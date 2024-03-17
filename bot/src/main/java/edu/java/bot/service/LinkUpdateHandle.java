package edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkUpdateHandle {
    private final TelegramBot bot;

    public void handle(String url, String description, Long[] tgChatIds) {
        String notificationMessage = getNotificationMessage(url, description);

        for (long chatId : tgChatIds) {
            SendMessage sendMessage = new SendMessage(chatId, notificationMessage)
                .disableWebPagePreview(true);

            bot.execute(sendMessage);
        }
    }

    private static String getNotificationMessage(String url, String description) {
        return "Появилось обновление для ссылки:\n"
            + url
            + '\n'
            + description;
    }
}
