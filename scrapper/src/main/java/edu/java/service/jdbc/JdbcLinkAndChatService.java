package edu.java.service.jdbc;

import edu.java.domain.jdbc.dao.JdbcLinkChatRepository;
import edu.java.mapers.LinkMapper;
import edu.java.service.LinkAndChatService;
import edu.java.service.dto.ChatDto;
import edu.java.service.dto.LinkDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
public class JdbcLinkAndChatService implements LinkAndChatService {
    private JdbcLinkChatRepository linkChatRepository;
    private LinkMapper mapper;

    @Override
    public List<LinkDto> allLinksByChatId(long chatId) {
        return linkChatRepository.findAllByChat(chatId)
            .stream().map(mapper::linkToLinkDto)
            .toList();
    }

    @Override
    public List<ChatDto> allChatsByLinkId(long linkId) {
        return linkChatRepository.findAllByLink(linkId)
            .stream()
            .map(chat -> new ChatDto(chat.getChatId()))
            .toList();
    }
}
