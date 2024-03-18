package edu.java.httpClients.stackoverflowClients;

import edu.java.httpClients.dto.ResponseStackOverflow;
import edu.java.httpClients.dto.StackoverflowCommentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
            .uri("/questions/{questionId}?site=stackoverflow", id)
            .retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals, (clientResponse) -> Mono.empty())
            .bodyToMono(ResponseStackOverflow.class)
            .block();
    }

    @Override
    public StackoverflowCommentResponse fetchComments(long id) {
        return webClient
            .get()
            .uri("/questions/{questionId}/comments?site=stackoverflow", id)
            .retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals, (response) -> Mono.empty())
            .bodyToMono(StackoverflowCommentResponse.class)
            .block();
    }
}
