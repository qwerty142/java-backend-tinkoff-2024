package edu.java.configuration;

import edu.java.clients.BotClient;
import edu.java.httpClients.githubClients.GithubClient;
import edu.java.httpClients.githubClients.IGithubWebClient;
import edu.java.httpClients.stackoverflowClients.IStackOverflowWebClient;
import edu.java.httpClients.stackoverflowClients.StackoverflowClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class ClientConfiguration {

    @Bean
    public IStackOverflowWebClient stackOverflowWebClient(@Value("${app.stackoverlowlink}") String val) {
        return new StackoverflowClient(val);
    }

    @Bean
    public IGithubWebClient githubClient(@Value("${app.githublink}") String val) {
        return new GithubClient(val);
    }

    @Bean
    public BotClient createBotClient(@Value("${app.baselink}") String baseLink) {
        return new BotClient(baseLink);
    }
}
