package edu.java.service.jpa;

import edu.java.domain.jpa.dao.JpaChatRepository;
import edu.java.domain.jpa.dao.JpaLinkRepository;
import edu.java.domain.jpa.dao.JpaStackoverflowLinkRepository;
import edu.java.domain.jpa.dto.Chat;
import edu.java.domain.jpa.dto.Link;
import edu.java.mapers.LinkMapper;
import edu.java.service.LinkService;
import edu.java.service.dto.LinkDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Limit;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class JpaLinkService implements LinkService {
    private final JpaLinkRepository linkRepository;
    private final JpaChatRepository chatRepository;
    private final JpaStackoverflowLinkRepository stackoverflowLinkRepository;
    private final LinkMapper linkMapper;
    @Override
    public LinkDto add(long chatId, String url) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        if (chatOptional.isEmpty()) {
            throw new ResourceNotFoundException(String.valueOf(chatId));
        }

        Link link = linkRepository.findByUrl(url)
            .orElseGet(() -> linkRepository.saveAndFlush(
                Link.builder()
                    .url(url)
                    .updatedAt(OffsetDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC))
                    .createdAt(OffsetDateTime.now())
                    .build())
            );

        linkRepository.insertChatAndLink(link.getId(), chatId);

        return linkMapper.linkFromJpaToDto(link);
    }

    @Override
    public LinkDto remove(long chatId, String url) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        if (chatOptional.isEmpty()) {
            throw new ResourceNotFoundException(String.valueOf(chatId));
        }

        Link link = linkRepository.findByUrl(url)
            .orElseThrow(() -> new ResourceNotFoundException(url));

        Long linkId = link.getId();
        linkRepository.removeChatAndLink(linkId, chatId);
        if (chatRepository.findAllByLinksLinkId(linkId).isEmpty()) {
            stackoverflowLinkRepository.deleteById(linkId);
            linkRepository.deleteById(linkId);
        }

        return linkMapper.linkFromJpaToDto(link);
    }

    @Override
    public Collection<LinkDto> listAll(long tgChatId) {
        return linkRepository.findAll()
            .stream()
            .map(linkMapper::linkFromJpaToDto)
            .toList();
    }

    @Override
    public List<LinkDto> getLinksLastCheckedBefore(int amount) {
        return linkRepository.findByOrderByUpdatedAtAsc(Limit.of(amount))
            .stream()
            .map(linkMapper::linkFromJpaToDto)
            .toList();
    }

    @Override
    public void updateCreation(long linkId, OffsetDateTime time) {
        Optional<Link> linkOptional = linkRepository.findById(linkId);
        if (linkOptional.isEmpty()) {
            throw new ResourceNotFoundException(String.valueOf(linkId));
        }

        Link link = linkOptional.get();
        link.setCreatedAt(time);
        linkRepository.save(link);
    }

    @Override
    public void updateUpdating(long linkId, OffsetDateTime time) {
        Optional<Link> linkOptional = linkRepository.findById(linkId);
        if (linkOptional.isEmpty()) {
            throw new ResourceNotFoundException(String.valueOf(linkId));
        }

        Link link = linkOptional.get();
        link.setUpdatedAt(time);
        linkRepository.save(link);
    }
}
