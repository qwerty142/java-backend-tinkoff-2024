package edu.java.configuration;

import edu.java.domain.jdbc.dao.JdbChatRepository;
import edu.java.domain.jdbc.dao.JdbcLinkChatRepository;
import edu.java.domain.jdbc.dao.JdbcLinkRepository;
import edu.java.mapers.LinkMapper;
import edu.java.service.jdbc.JdbcLinkAndChatService;
import edu.java.service.jdbc.JdbcLinkService;
import edu.java.service.jdbc.JdbcTgChatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoJdbcConfiguration {
    @Bean
    public JdbcTgChatService jdbcTgChatService(
        JdbcLinkRepository linkRepository,
        JdbChatRepository chatRepository,
        JdbcLinkChatRepository linkChatRepository
    ) {
        return new JdbcTgChatService(linkRepository, chatRepository, linkChatRepository);
    }

    @Bean
    public JdbcLinkService jdbcLinkService(
        JdbcLinkRepository linkRepository,
        JdbChatRepository chatRepository,
        JdbcLinkChatRepository linkChatRepository
    ) {
        return new JdbcLinkService(linkRepository, chatRepository, linkChatRepository, new LinkMapper());
    }

    @Bean
    public JdbcLinkAndChatService jdbcChatLinkService(
        JdbcLinkChatRepository linkChatRepository
    ) {
        return new JdbcLinkAndChatService(linkChatRepository, new LinkMapper());
    }
}
