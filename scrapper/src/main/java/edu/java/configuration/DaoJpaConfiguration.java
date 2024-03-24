package edu.java.configuration;

import edu.java.domain.jpa.dao.JpaChatRepository;
import edu.java.domain.jpa.dao.JpaLinkRepository;
import edu.java.domain.jpa.dao.JpaStackoverflowLinkRepository;
import edu.java.mapers.LinkMapper;
import edu.java.mapers.StackOverflowLinkMapper;
import edu.java.service.jpa.JpaChatAndLinkService;
import edu.java.service.jpa.JpaChatService;
import edu.java.service.jpa.JpaLinkService;
import edu.java.service.jpa.JpaStackoverflowLinkService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class DaoJpaConfiguration {
    @Bean
    public JpaLinkService jpaLinkService(
        JpaChatRepository chatRepository,
        JpaLinkRepository linkRepository,
        JpaStackoverflowLinkRepository stackOverflowRepository,
        LinkMapper linkMapper
    ) {
        return new JpaLinkService(
            linkRepository,
            chatRepository,
            stackOverflowRepository,
            linkMapper
        );
    }

    @Bean
    public JpaChatService jpaChatService(
        JpaChatRepository chatRepository,
        JpaLinkRepository linkRepository
    ) {
        return new JpaChatService(
            chatRepository,
            linkRepository
        );
    }

    @Bean
    public JpaChatAndLinkService jpaChatAndLinkService(
        JpaChatRepository chatRepository,
        JpaLinkRepository linkRepository,
        LinkMapper linkMapper
    ) {
        return new JpaChatAndLinkService(
            chatRepository,
            linkRepository,
            linkMapper
        );
    }

    @Bean
    public JpaStackoverflowLinkService jpaStackoverflowLinkService(
        JpaStackoverflowLinkRepository stackoverflowRepository,
        StackOverflowLinkMapper stackOverflowLinkMapper
    ) {
        return new JpaStackoverflowLinkService(
            stackoverflowRepository,
            stackOverflowLinkMapper
        );
    }
}
