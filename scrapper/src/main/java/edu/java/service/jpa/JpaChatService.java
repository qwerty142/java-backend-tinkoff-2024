package edu.java.service.jpa;

import edu.java.domain.jpa.dao.JpaChatRepository;
import edu.java.domain.jpa.dao.JpaLinkRepository;
import edu.java.domain.jpa.dto.Chat;
import edu.java.service.TgChatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

@Transactional
@RequiredArgsConstructor
public class JpaChatService implements TgChatService {
    private final JpaChatRepository chatRepository;
    private final JpaLinkRepository linkRepository;
    @Override
    public void register(long tgChatId) {
        if (chatRepository.findById(tgChatId).isPresent()) {
            return;
        }

        chatRepository.save(new Chat(tgChatId, new ArrayList<>()));
    }

    @Override
    public void unregister(long tgChatId) {
        if (chatRepository.findById(tgChatId).isEmpty()) {
            return;
        }

        linkRepository.deleteAllByChatsChatId(tgChatId);

        chatRepository.deleteById(tgChatId);
    }
}
