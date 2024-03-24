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
}
