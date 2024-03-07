package edu.java.httpClients.githubClients;

import edu.java.httpClients.dto.ResponseGitHub;
import lombok.NonNull;
import org.springframework.web.reactive.function.client.WebClient;

public class GithubClient  implements IGithubWebClient {
    /*private static final String BASE_URL = "https://api.github.com";*/
    WebClient webClient;

    public GithubClient(String url) {
        webClient = WebClient.builder().baseUrl(url).build();
    }

    @Override
    public ResponseGitHub fetch(@NonNull String user, @NonNull String repository) {
        return webClient
            .get()
            .uri("/{user}/{repository}", user, repository)
            .retrieve()
            .bodyToMono(ResponseGitHub.class)
            .block();

    }
}
