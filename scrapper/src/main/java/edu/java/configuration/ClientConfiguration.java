package edu.java.configuration;

import edu.java.httpClients.githubClients.GithubClient;
import edu.java.httpClients.githubClients.IGithubWebClient;
import edu.java.httpClients.stackoverflowClients.IStackOverflowWebClient;
import edu.java.httpClients.stackoverflowClients.StackoverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public IStackOverflowWebClient stackOverflowWebClient() {
        return new StackoverflowClient("");
    }

    @Bean
    public IGithubWebClient githubClient() {
        return new GithubClient("");
    }
}
