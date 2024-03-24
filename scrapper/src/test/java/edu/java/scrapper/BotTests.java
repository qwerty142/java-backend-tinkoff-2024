package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.clients.BotClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WireMockTest(httpPort = 8088)
public class BotTests {
    private String update = "/updates";
    private BotClient client = new BotClient("http://localhost:8088");

    public void stubSuccess() {
        stubFor(post(urlEqualTo(update))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("")
            ));
    }

    public void stubFail() {
        stubFor(post(update)
            .willReturn(
                aResponse()
                    .withStatus(404)
            ));
    }

    @Test
    public void SuccessfulTest() {
        stubSuccess();
        assertDoesNotThrow(() -> client.update(
            1,
            "https://github.com/qwerty142/java-backend-tinkoff-2024",
            "",
            new Long[] {0L}
        ));
    }

    @Test
    public void FailTest() {
        stubFail();
        assertThrows(WebClientResponseException.class, () -> client.update(
            1,
            "https://github.com/qwerty142/java-backend-tinkoff-2024",
            "",
            new Long[] {0L}
        ));
    }
}
