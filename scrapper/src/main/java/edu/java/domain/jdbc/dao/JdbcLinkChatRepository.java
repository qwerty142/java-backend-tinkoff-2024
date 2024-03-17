package edu.java.domain.jdbc.dao;

import edu.java.domain.jdbc.dto.Chat;
import edu.java.domain.jdbc.dto.Link;
import edu.java.domain.jdbc.dto.LinkChat;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbcLinkChatRepository {
    private final JdbcTemplate template;

    public LinkChat add(LinkChat linkChat) {
        template.update("INSERT INTO link_chat (link_id, chat_id) VALUES(?, ?) ON CONFLICT DO NOTHING",
            linkChat.getLinkId(),
            linkChat.getChatId());
        return linkChat;
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public List<Chat> findAllByLink(long linkId) {
        return template.query("SELECT chatId FROM chatAndLink WHERE linkId = ?",
            (rs, row) -> new Chat(rs.getLong("chatId")),
            linkId);
    }

    public List<Link> findAllByChat(long linkId) {
        return template.query("SELECT linkId FROM chatAndLink WHERE chatId = ?",
            (rs, row) -> new Link(rs.getLong("linkId"),
                rs.getString("url"),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class)),
            linkId);
    }

    public int removeByLinkId(long linkId) {
        return template.update("DELETE FROM chatAndLink WHERE linkId = ?", linkId);
    }

    public int removeByChatId(long chatId) {
        return template.update("DELETE FROM chatAndLink WHERE chatId = ?", chatId);
    }

    public int removeByLinkAndChatIds(long chatId, long linkId) {
        return template.update("DELETE FROM chatAndLink WHERE chatId = ? AND linkId = ?", chatId, linkId);
    }
}
