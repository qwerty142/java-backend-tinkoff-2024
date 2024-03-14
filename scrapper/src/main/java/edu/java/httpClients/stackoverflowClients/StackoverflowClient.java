package edu.java.httpClients.stackoverflowClients;

import edu.java.httpClients.dto.ResponseStackOverflow;
import org.springframework.web.reactive.function.client.WebClient;

public class StackoverflowClient implements IStackOverflowWebClient {
    /*private static final String BASE_URL = "https://api.stackexchange.com/2.3/";*/
    private final WebClient webClient;

    public StackoverflowClient(String url) {
        webClient = WebClient.builder().baseUrl(url).build();
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
