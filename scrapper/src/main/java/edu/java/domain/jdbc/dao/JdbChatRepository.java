package edu.java.domain.jdbc.dao;

import edu.java.domain.jdbc.dto.Chat;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbChatRepository {
    private final JdbcTemplate template;

    public Chat add(Chat chat) {
        template.update("INSERT INTO chat VALUES (?)", chat.getChatId());
        return chat;
    }

    public List<Chat> findAll() {
        return template.query("SELECT * FROM chat",
            (rs, row) -> new Chat(rs.getLong("chatId")));
    }

    public Chat remove(Chat chat) {
        template.update("DELETE FROM chat WHERE chat_id = ?", chat.getChatId());
        return new Chat(chat.getChatId());
    }

    public Optional<Chat> findById(long chatId) {
        try {
            return Optional.ofNullable(
                template.queryForObject(
                    "SELECT * FROM chat WHERE chatId = ?",
                    (rs, rowNum) -> new Chat(rs.getLong("chatId")),
                    chatId
                )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
