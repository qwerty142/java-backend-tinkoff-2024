package edu.java.scrapper;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.httpClients.dto.ResponseStackOverflow;
import edu.java.httpClients.stackoverflowClients.IStackOverflowWebClient;
import edu.java.httpClients.stackoverflowClients.StackoverflowClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WireMockTest(httpPort = 8088)
public class StackOverflowTest {
    OffsetDateTime time = OffsetDateTime.of(2024, 2, 23, 23, 23, 23, 0, ZoneOffset.UTC);
    void setStubGoodResult() {
        WireMock.configureFor("localhost", 8088);
        stubFor(get(urlMatching("/questions/1"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withStatus(200)
                    .withBody(
                        """
                            {
                            "title": "aba",
                            "last_activity_date": %d
                            }
                            """.formatted(time.toEpochSecond()))
            )
        );
    }

    void setStubBadResult() {
        stubFor(get(urlMatching("/questions/1"))
            .willReturn(
                aResponse()
                    .withStatus(404)
            ));
    }

    @Test
    public void successfulResponseTest() {
        setStubGoodResult();
        IStackOverflowWebClient stackOverflowWebClient = new StackoverflowClient("http://localhost:8088");
        ResponseStackOverflow responseStackOverflow = stackOverflowWebClient.fetch(1);
        assertThat(responseStackOverflow.title()).isEqualTo("aba");
        assertThat(responseStackOverflow.lastUpdated()).isEqualTo(time);
    }

    @Test
    public void failResponseTest() {
        setStubBadResult();
        IStackOverflowWebClient stackOverflowWebClient = new StackoverflowClient("http://localhost:8088");
        assertThrows(WebClientException.class, () -> stackOverflowWebClient.fetch(1));
    }
}
