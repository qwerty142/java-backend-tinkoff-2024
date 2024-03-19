package edu.java.domain.jooq.dao;

import edu.java.domain.jooq.jooqGen.tables.pojos.Chatandlink;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JooqLinkAndChatRepository {
    private final DSLContext dsl;
    private final edu.java.domain.jooq.jooqGen.tables.Chatandlink linkChatTable =
        edu.java.domain.jooq.jooqGen.tables.Chatandlink.CHATANDLINK;

    public List<Chatandlink> findAll() {
        return dsl.selectFrom(linkChatTable)
            .fetchInto(Chatandlink.class);
    }

    public List<Chatandlink> findAllByChatId(long chatId) {
        return dsl.selectFrom(linkChatTable)
            .where(linkChatTable.CHATID.eq(chatId))
            .fetchInto(Chatandlink.class);
    }

    public List<Chatandlink> findAllByLinkId(long linkId) {
        return dsl.selectFrom(linkChatTable)
            .where(linkChatTable.LINKID.eq(linkId))
            .fetchInto(Chatandlink.class);
    }

    public int add(Chatandlink newChatLink) {
        return dsl.insertInto(linkChatTable)
            .set(dsl.newRecord(linkChatTable, newChatLink))
            .execute();
    }

    public int delete(Long chatId, Long linkId) {
        return dsl.deleteFrom(linkChatTable)
            .where(linkChatTable.CHATID.eq(chatId).and(linkChatTable.LINKID.eq(linkId)))
            .execute();
    }

    public int deleteAllByChatId(Long chatId) {
        return dsl.deleteFrom(linkChatTable)
            .where(linkChatTable.CHATID.eq(chatId))
            .execute();
    }
}
