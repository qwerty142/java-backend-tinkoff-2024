package edu.java.service.jdbc;

import edu.java.domain.jdbc.dao.JdbChatRepository;
import edu.java.domain.jdbc.dao.JdbcLinkChatRepository;
import edu.java.domain.jdbc.dao.JdbcLinkRepository;
import edu.java.domain.jdbc.dto.Chat;
import edu.java.service.TgChatService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
public class JdbcTgChatService implements TgChatService {
    private JdbcLinkRepository linkRepository;
    private JdbChatRepository chatRepository;
    private JdbcLinkChatRepository linkChatRepository;

    @Override
    public void register(long tgChatId) {
        if (chatRepository.findById(tgChatId).isPresent()) {
            throw new IllegalArgumentException();
        }

        chatRepository.add(new Chat(tgChatId));
    }

    @Override
    public void unregister(long tgChatId) {
        if (chatRepository.findById(tgChatId).isEmpty()) {
            throw new IllegalArgumentException();
        }

        chatRepository.remove(new Chat(tgChatId));
    }
}
