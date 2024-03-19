package edu.java.service.jooq;

import edu.java.domain.jooq.dao.JooqChatRepository;
import edu.java.domain.jooq.dao.JooqLinkAndChatRepository;
import edu.java.domain.jooq.dao.JooqLinkRepository;
import edu.java.domain.jooq.jooqGen.tables.pojos.Chatandlink;
import edu.java.domain.jooq.jooqGen.tables.pojos.Link;
import edu.java.mapers.LinkMapper;
import edu.java.service.LinkService;
import edu.java.service.dto.LinkDto;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;

@AllArgsConstructor
public class JooqLinkService implements LinkService {
    private JooqLinkAndChatRepository linkAndChatRepository;
    private JooqLinkRepository linkRepository;
    private JooqChatRepository chatRepository;
    private LinkMapper mapper;

    @Override
    public LinkDto add(long tgChatId, String url) {
        if (chatRepository.getById(tgChatId).isEmpty()) {
            throw new ResourceNotFoundException(String.valueOf(tgChatId));
        }

        Link finalLink = new Link();
        finalLink.setUrl(url);

        Optional<Link> optionalLink = linkRepository.findByUrl(url);
        Link link = optionalLink.orElseGet(() -> linkRepository.add(finalLink));

        Chatandlink chatLink = new Chatandlink();
        chatLink.setLinkid(link.getLinkid());
        chatLink.setChatid(tgChatId);
        linkAndChatRepository.add(chatLink);

        return mapper.linkFromJooqToLinkDto(link);
    }

    @Override
    public LinkDto remove(long tgChatId, String url) {
        Link link = linkRepository.findByUrl(url)
            .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(tgChatId), url));
        linkAndChatRepository.delete(tgChatId, link.getLinkid());

        if (linkAndChatRepository.findAllByLinkId(link.getLinkid()).isEmpty()) {
            linkRepository.deleteById(link.getLinkid());
        }

        return mapper.linkFromJooqToLinkDto(link);
    }

    @Override
    public Collection<LinkDto> listAll(long tgChatId) {
        return linkRepository.findAll()
            .stream()
            .map(mapper::linkFromJooqToLinkDto)
            .toList();
    }

    @Override
    public List<LinkDto> getLinksLastCheckedBefore(int amount) {
        return linkRepository.findNByOldestLastCheck(amount)
            .stream()
            .map(mapper::linkFromJooqToLinkDto)
            .toList();
    }

    @Override
    public void updateCreation(long linkId, OffsetDateTime time) {
        Link link = linkRepository.findById(linkId)
            .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(linkId)));

        link.setCreatedAt(time);
        linkRepository.updateLink(link);
    }

    @Override
    public void updateUpdating(long linkId, OffsetDateTime time) {
        Link link = linkRepository.findById(linkId)
            .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(linkId)));

        link.setUpdatedAt(time);
        linkRepository.updateLink(link);
    }
}
