package edu.java.service.jdbc;

import edu.java.domain.jdbc.dao.JdbChatRepository;
import edu.java.domain.jdbc.dao.JdbcLinkChatRepository;
import edu.java.domain.jdbc.dao.JdbcLinkRepository;
import edu.java.domain.jdbc.dto.Link;
import edu.java.domain.jdbc.dto.LinkChat;
import edu.java.mapers.LinkMapper;
import edu.java.service.LinkService;
import edu.java.service.dto.LinkDto;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
public class JdbcLinkService implements LinkService {
    private JdbcLinkRepository linkRepository;
    private JdbChatRepository chatRepository;
    private JdbcLinkChatRepository linkChatRepository;
    private LinkMapper mapper;

    @Override
    public LinkDto add(long tgChatId, String url) {
        Optional<Link> optionalLink = linkRepository.findByUrl(url);
        Link link = optionalLink.orElseGet(() -> linkRepository.add(Link.builder().link(url).build()));

        linkChatRepository.add(new LinkChat(link.getLinkId(), tgChatId));

        return mapper.linkToLinkDto(link);
    }

    @Override
    public LinkDto remove(long tgChatId, String url) {
        Link link = linkRepository.findByUrl(url)
            .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(tgChatId), url));
        linkChatRepository.removeByLinkAndChatIds(link.getLinkId(), tgChatId);

        if (linkChatRepository.findAllByLink(link.getLinkId()).isEmpty()) {
            linkRepository.remove(link.getLink());
        }

        return mapper.linkToLinkDto(link);
    }

    @Override
    public Collection<LinkDto> listAll(long tgChatId) {
        return linkRepository.findAll()
            .stream()
            .map(mapper::linkToLinkDto)
            .toList();
    }

    @Override
    public List<LinkDto> getLinksLastCheckedBefore(int amount) {
        return linkRepository.getLinksInOrderByUpdateTime(amount);
    }

    @Override
    public void updateCreation(long linkId, OffsetDateTime time) {
        Link link = linkRepository.findById(linkId)
            .orElseThrow();
        link.setLastUpdated(time);
        linkRepository.updateLink(link);
    }

    @Override
    public void updateUpdating(long linkId, OffsetDateTime time) {
        Link link = linkRepository.findById(linkId)
            .orElseThrow();
        link.setLastUpdated(time);
        linkRepository.updateLink(link);
    }

}
