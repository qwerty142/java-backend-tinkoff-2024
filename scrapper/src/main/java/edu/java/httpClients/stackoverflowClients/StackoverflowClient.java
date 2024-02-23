package edu.java.httpClients.stackoverflowClients;

import edu.java.httpClients.dto.ResponseStackOverflow;
import org.springframework.web.reactive.function.client.WebClient;

public class StackoverflowClient implements IStackOverflowWebClient {
    private static final String BASE_URL = "https://api.stackexchange.com/2.3/";
    private WebClient webClient;

    public StackoverflowClient(String url) {
        if (url.isEmpty()) {
            webClient = WebClient.builder().baseUrl(BASE_URL).build();
        } else {
            webClient = WebClient.builder().baseUrl(url).build();
        }
    }

    @Override
    public ResponseStackOverflow fetch(long id) {
        return webClient
            .get()
            .uri("/questions/{questionId}", id)
            .retrieve()
            .bodyToMono(ResponseStackOverflow.class)
            .block();
    }
}
