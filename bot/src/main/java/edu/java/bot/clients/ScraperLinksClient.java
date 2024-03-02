package edu.java.bot.clients;

import edu.java.bot.clients.dto.ScraperLinkRequest;
import edu.java.bot.clients.dto.ScraperLinkResponse;
import edu.java.bot.clients.dto.ScraperListLinksResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ScraperLinksClient {
    private static final String BASE_URL = "http:///localhost:9080";
    private static final String PATH_LINK = "/links";
    private static final String HEADER = "Tg-Chat-Id";
    private WebClient client;

    public ScraperLinksClient(String url) {
        client = WebClient.builder().baseUrl(url).build();
    }

    public ScraperLinksClient() {
        this(BASE_URL);
    }

    public ScraperListLinksResponse get(Long id) {
        return client
            .get()
            .uri(PATH_LINK)
            .header(HEADER, id.toString())
            .retrieve()
            .bodyToMono(ScraperListLinksResponse.class)
            .block();
    }

    public ScraperLinkResponse add(Long id, String link) {
        return client
            .post()
            .uri(PATH_LINK)
            .header(HEADER)
            .body(Mono.just(new ScraperLinkRequest(link)), ScraperLinkRequest.class)
            .retrieve()
            .bodyToMono(ScraperLinkResponse.class)
            .block();
    }
}
