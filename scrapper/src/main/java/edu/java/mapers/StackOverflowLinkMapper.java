package edu.java.mapers;

import edu.java.domain.jdbc.dao.StackoverflowLink;
import edu.java.domain.jooq.jooqGen.tables.pojos.Linkstackoverflow;
import edu.java.domain.jpa.dto.Link;
import edu.java.httpClients.dto.StackoverlowLinkDto;
import org.springframework.stereotype.Component;

@Component
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

    public StackoverlowLinkDto jpaToDto(edu.java.domain.jpa.dto.StackoverflowLink link) {
        return new StackoverlowLinkDto(
            link.getLinkId(),
            link.getCommentsAmount(),
            link.getAnswersAmount(),
            link.getAnswered()
        );
    }

    public edu.java.domain.jpa.dto.StackoverflowLink dtoToJpa(StackoverlowLinkDto linkDto) {
        return edu.java.domain.jpa.dto.StackoverflowLink.builder()
            .linkId(linkDto.getId())
            .answersAmount(linkDto.getAmountAnswers())
            .commentsAmount(linkDto.getAmountAnswers())
            .answered(linkDto.getStatus()).build();
    }
}
