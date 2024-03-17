package edu.java.bot.service.repositories;

import edu.java.bot.clients.ScraperChatClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScrapperChatRepository implements ChatRepository {
    private ScraperChatClient client;

    @Override
    public void registerChat(long chatId) {
        client.add(chatId);
    }

    @Override
    public void unregisterChat(long chatId) {
        client.delete(chatId);
    }
}
