package edu.java.bot;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.bot.clients.ScraperChatClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
@WireMockTest(httpPort = 8088)
public class ScrapperChatTest {
    private String chat = "/tg-chat/{id}";
    private ScraperChatClient client = new ScraperChatClient("http://localhost:8088");

    public void stubSuccess() {
        stubFor(
            post(urlPathTemplate(chat))
                .withPathParam("id", equalTo("1"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(""))
        );

        stubFor(
            delete(urlPathTemplate(chat))
                .withPathParam("id", equalTo("1"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(""))
        );
    }

    public void stubFail() {
        stubFor(post(urlPathTemplate(chat))
            .withPathParam("id", equalTo("1"))
            .willReturn(aResponse()
                .withStatus(404))
        );

        stubFor(delete(urlPathTemplate(chat))
            .withPathParam("id", equalTo("1"))
            .willReturn(aResponse()
                .withStatus(404))
        );
    }

    @Test
    public void SuccessfulTest() {
        stubSuccess();
        assertDoesNotThrow(() -> client.add(1));
        assertDoesNotThrow(() -> client.delete(1));
    }

    @Test
    public void FailTest() {
        assertThrows(WebClientResponseException.class,() -> client.add(1));
        assertThrows(WebClientResponseException.class,() -> client.delete(1));
    }
}
