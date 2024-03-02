package edu.java.bot.clients;

import edu.java.bot.clients.dto.ScraperChatResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class ScraperChatClient {
    private static final String BASE_URL = "http:///localhost:9080";
    private static final String PATH_CHAT_ID = "/tg-chat/{id}";
    private WebClient client;

    public ScraperChatClient(String url) {
        client = WebClient.builder().baseUrl(url).build();
    }

    public ScraperChatClient() {
        this(BASE_URL);
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
            .bodyToMono(ScraperChatResponse.class)
            .block();
    }
}
