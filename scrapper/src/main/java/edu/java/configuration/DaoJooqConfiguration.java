package edu.java.configuration;

import edu.java.domain.jooq.dao.JooqChatRepository;
import edu.java.domain.jooq.dao.JooqLinkAndChatRepository;
import edu.java.domain.jooq.dao.JooqLinkRepository;
import edu.java.domain.jooq.dao.JooqStackoverflowRepository;
import edu.java.mapers.LinkMapper;
import edu.java.mapers.StackOverflowLinkMapper;
import edu.java.service.jooq.JooqChatService;
import edu.java.service.jooq.JooqLinkChatService;
import edu.java.service.jooq.JooqLinkService;
import edu.java.service.jooq.JooqStackoverflowService;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoJooqConfiguration {
    @Bean
    public DefaultConfigurationCustomizer postgresJooqCustomizer() {
        return (DefaultConfiguration c) -> c.settings()
            .withRenderSchema(false)
            .withRenderFormatted(true)
            .withRenderQuotedNames(RenderQuotedNames.NEVER);
    }

    @Bean
    public JooqLinkService jooqLinkService(
        JooqChatRepository chatRepository,
        JooqLinkRepository linkRepository,
        JooqLinkAndChatRepository linkChatRepository,
        LinkMapper linkMapper
    ) {
        return new JooqLinkService(
            linkChatRepository,
            linkRepository,
            chatRepository,
            linkMapper
        );
    }

    @Bean
    public JooqChatService jooqTgChatService(
        JooqLinkRepository linkRepository,
        JooqChatRepository chatRepository,
        JooqLinkAndChatRepository linkChatRepository
    ) {
        return new JooqChatService(
            chatRepository,
            linkRepository,
            linkChatRepository
        );
    }

    @Bean
    public JooqLinkChatService jooqLinkChatService(
        JooqLinkRepository linkRepository,
        JooqLinkAndChatRepository linkChatRepository,
        LinkMapper linkMapper
    ) {
        return new JooqLinkChatService(
            linkChatRepository,
            linkRepository,
            linkMapper
        );
    }

    @Bean
    public JooqStackoverflowService jooqStackoverflowService(
        JooqStackoverflowRepository stackOverflowLinkRepository,
        StackOverflowLinkMapper stackOverflowLinkMapper
    ) {
        return new JooqStackoverflowService(
            stackOverflowLinkRepository,
            stackOverflowLinkMapper
        );
    }
}
