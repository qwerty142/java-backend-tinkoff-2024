package edu.java.scrapper;

import edu.java.domain.jpa.dao.JpaChatRepository;
import edu.java.domain.jpa.dao.JpaLinkRepository;
import edu.java.service.jpa.JpaChatService;
import edu.java.service.jpa.JpaLinkService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = "app.databaseAccessType=jpa")
public class JpaLinkServiceTests extends IntegrationTest {
    private static final String TEST_LINK = "https://github.com/qwerty142";

    @Autowired private JpaLinkRepository linkRepository;
    @Autowired private JpaChatRepository chatRepository;
    @Autowired private JpaChatService chatService;
    @Autowired private JpaLinkService linkService;

    @Test
    @Transactional
    @Rollback
    void addLinkWithUnregisteredChatFail() {
        long id = 1L;
        assertThrows(
            ResourceNotFoundException.class,
            () -> linkService.add(id, TEST_LINK)
        );
    }

    @Test
    @Transactional
    @Rollback
    void addSameLinkInChats() {
        long chat1 = 1L;
        long chat2 = 2L;
        long chat3 = 3L;
        chatService.register(chat1);
        chatService.register(chat2);
        chatService.register(chat3);

        linkService.add(chat1, TEST_LINK);
        linkService.add(chat2, TEST_LINK);
        linkService.add(chat3, TEST_LINK);

        assertThat(linkRepository.findAllByChatsChatId(chat1)).hasSize(1);
        assertThat(linkRepository.findAllByChatsChatId(chat2)).hasSize(1);
        assertThat(linkRepository.findAllByChatsChatId(chat3)).hasSize(1);
        assertThat(linkRepository.findByUrl(TEST_LINK)).isPresent();
    }

    @Test
    @Transactional
    @Rollback
    void deleteNotAddedLink() {
        long Id = 1L;
        assertThrows(
            ResourceNotFoundException.class,
            () -> linkService.remove(Id, TEST_LINK)
        );
    }

    @Test
    @Transactional
    @Rollback
    void deleteLinks() {
        long chat1 = 1L;
        long chat2 = 2L;
        chatService.register(chat1);
        chatService.register(chat2);
        linkService.add(chat1, TEST_LINK);
        linkService.add(chat2, TEST_LINK);

        linkService.remove(chat1, TEST_LINK);
        assertThat(linkRepository.findByUrl(TEST_LINK)).isPresent();

        linkService.remove(chat2, TEST_LINK);

        assertThat(linkRepository.findAllByChatsChatId(chat1)).isEmpty();
        assertThat(linkRepository.findAllByChatsChatId(chat2)).isEmpty();
        assertThat(linkRepository.findAll()).isEmpty();
    }
}
