package edu.java.service.jpa;

import edu.java.domain.jpa.dao.JpaChatRepository;
import edu.java.domain.jpa.dao.JpaLinkRepository;
import edu.java.mapers.LinkMapper;
import edu.java.service.LinkAndChatService;
import edu.java.service.dto.ChatDto;
import edu.java.service.dto.LinkDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
@Transactional
@RequiredArgsConstructor
public class JpaChatAndLinkService implements LinkAndChatService {
    private final JpaChatRepository chatRepository;
    private final JpaLinkRepository linkRepository;
    private final LinkMapper mapper;
    @Override
    public List<LinkDto> allLinksByChatId(long chatId) {
        return linkRepository.findAllByChatsChatId(chatId)
            .stream()
            .map(mapper::linkFromJpaToDto)
            .toList();
    }

    @Override
    public List<ChatDto> allChatsByLinkId(long linkId) {
        return chatRepository.findAllByLinksLinkId(linkId)
            .stream()
            .map(jpaChat -> new ChatDto(jpaChat.getChatId()))
            .toList();
    }
}
