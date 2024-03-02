package edu.java.clients;

import edu.java.dtoRequest.BotRequest;
import edu.java.dtoResponse.BotLinkUpdateResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BotClient {
    private static final String BASE_URL = "http:///localhost:8080";
    private static final String UPDATE = "/updates";
    private WebClient client;

    public BotClient(String url) {
        client = WebClient.builder().baseUrl(url).build();
    }

    public BotClient() {
        this(BASE_URL);
    }

    public BotLinkUpdateResponse update(
        long id,
        String url,
        String description,
        long[] ids
    ) {
        return client
            .post()
            .uri(UPDATE)
            .body(
                Mono.just(new BotRequest(id, url, description, ids)),
                BotRequest.class
            )
            .retrieve()
            .bodyToMono(BotLinkUpdateResponse.class)
            .block();
    }
}
