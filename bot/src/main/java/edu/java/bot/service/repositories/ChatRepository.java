package edu.java.bot.service.repositories;

public interface ChatRepository {
    void registerChat(long chatId);

    void unregisterChat(long chatId);
}
