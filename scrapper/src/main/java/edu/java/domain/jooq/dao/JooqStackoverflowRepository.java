package edu.java.domain.jooq.dao;

import edu.java.domain.jooq.jooqGen.tables.pojos.Linkstackoverflow;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JooqStackoverflowRepository {
    private final DSLContext dsl;
    private final edu.java.domain.jooq.jooqGen.tables.Linkstackoverflow linkStackoverflowTable =
        edu.java.domain.jooq.jooqGen.tables.Linkstackoverflow.LINKSTACKOVERFLOW;

    public Optional<Linkstackoverflow> findById(Long linkId) {
        return dsl.selectFrom(linkStackoverflowTable)
            .where(linkStackoverflowTable.LINKID.eq(linkId))
            .fetchOptionalInto(Linkstackoverflow.class);
    }

    public int upsertLink(Linkstackoverflow link) {
        return dsl
            .insertInto(linkStackoverflowTable)
            .columns(
                linkStackoverflowTable.LINKID,
                linkStackoverflowTable.ANSWERED,
                linkStackoverflowTable.ANSWERSAMOUNT,
                linkStackoverflowTable.COMMENTSAMOUNT
            )
            .values(
                link.getLinkid(),
                link.getAnswered(),
                link.getAnswersamount(),
                link.getCommentsamount()
            )
            .onDuplicateKeyUpdate()
            .set(linkStackoverflowTable.ANSWERED, link.getAnswered())
            .set(linkStackoverflowTable.ANSWERSAMOUNT, link.getAnswersamount())
            .set(linkStackoverflowTable.COMMENTSAMOUNT, link.getCommentsamount())
            .execute();
    }
}
