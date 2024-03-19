package edu.java.mapers;

import edu.java.domain.jdbc.dao.StackoverflowLink;
import edu.java.domain.jooq.jooqGen.tables.pojos.Linkstackoverflow;
import edu.java.httpClients.dto.StackoverlowLinkDto;

public class StackOverflowLinkMapper {
    public StackoverflowLink dtoToJdbc(StackoverlowLinkDto link) {
        return new StackoverflowLink(
            link.getId(),
            link.getAmountAnswers(),
            link.getAmountComments(),
            link.getStatus()
        );
    }

    public StackoverlowLinkDto jdbcToDto(StackoverflowLink link) {
        return new StackoverlowLinkDto(
            link.getId(),
            link.getAmountAnswers(),
            link.getAmountComment(),
            link.getStatus()
        );
    }

    public Linkstackoverflow dtoToJooq(StackoverlowLinkDto link) {
        Linkstackoverflow linkJooq = new Linkstackoverflow();
        linkJooq.setLinkid(link.getId());
        linkJooq.setAnswersamount(link.getAmountAnswers());
        linkJooq.setCommentsamount(link.getAmountComments());
        linkJooq.setAnswered(link.getStatus());

        return linkJooq;
    }

    public StackoverlowLinkDto jooqToDto(Linkstackoverflow link) {
        return new StackoverlowLinkDto(
            link.getLinkid(),
            link.getCommentsamount(),
            link.getAnswersamount(),
            link.getAnswered()
        );
    }
}
