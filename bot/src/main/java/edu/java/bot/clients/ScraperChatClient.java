package edu.java.bot.clients;

import edu.java.bot.clients.dto.ScraperChatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ScraperChatClient {
    private static final String PATH_CHAT_ID = "/tg-chat/{id}";
    private WebClient client;

    public ScraperChatClient(String url) {
        client = WebClient.builder().baseUrl(url).build();
    }

    public ScraperChatResponse add(long id) {
        return client
            .post()
            .uri(PATH_CHAT_ID, id)
            .retrieve()
            .bodyToMono(ScraperChatResponse.class)
            .block();
    }

    public ScraperChatResponse delete(long id) {
        return client
            .delete()
            .uri(PATH_CHAT_ID, id)
            .retrieve()
            .onStatus(HttpStatus.BAD_REQUEST::equals, (clientResponse) -> Mono.empty())
            .bodyToMono(ScraperChatResponse.class)
            .block();
    }
}
