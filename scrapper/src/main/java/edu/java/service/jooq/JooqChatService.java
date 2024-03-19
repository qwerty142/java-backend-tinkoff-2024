package edu.java.service.jooq;

import edu.java.domain.jooq.dao.JooqChatRepository;
import edu.java.domain.jooq.dao.JooqLinkAndChatRepository;
import edu.java.domain.jooq.dao.JooqLinkRepository;
import edu.java.domain.jooq.jooqGen.tables.pojos.Chat;
import edu.java.domain.jooq.jooqGen.tables.pojos.Chatandlink;
import edu.java.service.TgChatService;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
public class JooqChatService implements TgChatService {
    private JooqChatRepository chatRepository;
    private JooqLinkRepository linkRepository;
    private JooqLinkAndChatRepository linkAndChatRepository;

    @Override
    public void register(long tgChatId) {

        Chat chat = new Chat();
        chat.setChatid(tgChatId);
        chatRepository.add(chat);
    }

    @Override
    public void unregister(long tgChatId) {
        Collection<Chatandlink> links = linkAndChatRepository.findAllByChatId(tgChatId);
        linkAndChatRepository.deleteAllByChatId(tgChatId);
        for (var link : links) {
            long linkId = link.getLinkid();
            if (linkAndChatRepository.findAllByLinkId(linkId).isEmpty()) {
                linkRepository.deleteById(linkId);
            }
        }

        chatRepository.deleteById(tgChatId);
    }
}
