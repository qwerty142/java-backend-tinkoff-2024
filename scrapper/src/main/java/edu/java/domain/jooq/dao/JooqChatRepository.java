package edu.java.domain.jooq.dao;

import edu.java.domain.jooq.jooqGen.tables.pojos.Chat;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JooqChatRepository {
    private final DSLContext dsl;
    private final edu.java.domain.jooq.jooqGen.tables.Chat chatTable =
        edu.java.domain.jooq.jooqGen.tables.Chat.CHAT;

    public Optional<Chat> getById(Long chatId) {
        return dsl.selectFrom(chatTable)
            .where(chatTable.CHATID.eq(chatId))
            .fetchOptionalInto(Chat.class);
    }

    public List<Chat> getAll() {
        return dsl.selectFrom(chatTable)
            .fetchInto(Chat.class);
    }

    public int add(Chat newChat) {
        return dsl.insertInto(chatTable)
            .set(dsl.newRecord(chatTable, newChat))
            .execute();
    }

    public int deleteById(Long chatId) {
        return dsl.deleteFrom(chatTable)
            .where(chatTable.CHATID.eq(chatId))
            .execute();
    }
}
