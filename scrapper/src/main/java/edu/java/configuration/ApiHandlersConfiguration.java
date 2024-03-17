package edu.java.configuration;

import edu.java.httpClients.githubClients.GithubClient;
import edu.java.httpClients.stackoverflowClients.StackoverflowClient;
import edu.java.service.handlersOfApi.GithubHandler;
import edu.java.service.handlersOfApi.StackoverflowHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApiHandlersConfiguration {
    @Bean(name = "stackOverflowApiHandler")
    public StackoverflowHandler stackOverflowApiHandler(
        StackoverflowClient stackOverflowClient
    ) {
        return new StackoverflowHandler(stackOverflowClient);
    }

    @Bean
    @Primary
    @DependsOn("stackOverflowApiHandler")
    public GithubHandler gitHubApiHandler(
        GithubClient gitHubClient,
        StackoverflowHandler stackOverflowApiHandler
    ) {
        var gitHubApiHandler = new GithubHandler(gitHubClient, null);
        gitHubApiHandler.next(stackOverflowApiHandler);
        return gitHubApiHandler;
    }
}
