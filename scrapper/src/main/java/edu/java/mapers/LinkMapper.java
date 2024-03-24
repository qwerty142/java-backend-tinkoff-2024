package edu.java.mapers;

import edu.java.domain.jdbc.dto.Link;
import edu.java.dtoResponse.ScraperLinkResponse;
import edu.java.service.dto.LinkDto;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {
    public ScraperLinkResponse linkToLinkResponse(LinkDto link) {
        return new ScraperLinkResponse(link.getLinkId(), link.getUrl());
    }

    public LinkDto linkToLinkDto(Link link) {
        return new LinkDto(link.getLinkId(), link.getLink(), link.getLastCreated(), link.getLastUpdated());
    }

    public LinkDto linkFromJooqToLinkDto(edu.java.domain.jooq.jooqGen.tables.pojos.Link link) {
        return new LinkDto(
            link.getLinkid(),
            link.getUrl(),
            link.getCreatedAt(),
            link.getUpdatedAt()
        );
    }

    public LinkDto linkFromJpaToDto(edu.java.domain.jpa.dto.Link link) {
        return new LinkDto(
            link.getId(),
            link.getUrl(),
            link.getCreatedAt(),
            link.getUpdatedAt()
        );
    }
}
