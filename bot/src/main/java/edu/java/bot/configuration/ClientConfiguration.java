package edu.java.bot.configuration;

import edu.java.bot.clients.ScraperChatClient;
import edu.java.bot.clients.ScraperLinksClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(ApplicationConfig.class)
// @ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
// @NoArgsConstructor
@Configuration
public class ClientConfiguration {

    @Bean
    public ScraperChatClient createScraperChatClient(@Value("${app.baseLink}") String baseLink) {
        return new ScraperChatClient(baseLink);
    }

    @Bean
    public ScraperLinksClient createScraperLinksClient(@Value("${app.baseLink}") String baseLink) {
        return new ScraperLinksClient(baseLink);
    }
}
