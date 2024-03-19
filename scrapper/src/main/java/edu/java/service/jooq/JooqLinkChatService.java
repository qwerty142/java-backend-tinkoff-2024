package edu.java.service.jooq;

import edu.java.domain.jooq.dao.JooqChatRepository;
import edu.java.domain.jooq.dao.JooqLinkAndChatRepository;
import edu.java.domain.jooq.dao.JooqLinkRepository;
import edu.java.domain.jooq.jooqGen.tables.pojos.Chatandlink;
import edu.java.mapers.LinkMapper;
import edu.java.service.LinkAndChatService;
import edu.java.service.dto.ChatDto;
import edu.java.service.dto.LinkDto;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
public class JooqLinkChatService implements LinkAndChatService {
    private JooqLinkAndChatRepository linkAndChatRepository;
    private JooqLinkRepository linkRepository;
    private LinkMapper mapper;

    @Override
    public List<LinkDto> allLinksByChatId(long chatId) {
        return linkAndChatRepository.findAllByChatId(chatId)
            .stream()
            .map(Chatandlink::getLinkid)
            .map(linkRepository::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(mapper::linkFromJooqToLinkDto)
            .toList();
    }

    @Override
    public List<ChatDto> allChatsByLinkId(long linkId) {
        return linkAndChatRepository.findAllByLinkId(linkId)
            .stream()
            .map(jdbcTgChat -> new ChatDto(jdbcTgChat.getChatid()))
            .toList();
    }
}
