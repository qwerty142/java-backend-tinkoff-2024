package edu.java.scrapper;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.httpClients.dto.ResponseGitHub;
import edu.java.httpClients.githubClients.GithubClient;
import edu.java.httpClients.githubClients.IGithubWebClient;
import org.junit.jupiter.api.Test;
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
public class GitHubTest {
    OffsetDateTime time = OffsetDateTime.of(2024, 2, 23, 23, 23, 23, 0, ZoneOffset.UTC);
    void setStubGoodResult() {
        WireMock.configureFor("localhost", 8088);
        stubFor(get(urlMatching("/qwerty142/java-backend-tinkoff-2024"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withStatus(200)
                    .withBody(
                        """
                            {
                            "title": "java-backend-tinkoff-2024",
                            "updated_at": %d
                            }
                            """.formatted(time.toEpochSecond()))
                    )
            );
    }

    void setStubBadResult() {
        stubFor(get(urlMatching("/repos/qwerty142/java-backend-tinkoff-2024"))
            .willReturn(
                aResponse()
                    .withStatus(404)
            ));
    }

    @Test
    public void successfulResult() {
        setStubGoodResult();
        IGithubWebClient githubClient = new GithubClient("http://localhost:8088");
        ResponseGitHub responseGitHub = githubClient.fetch("qwerty142", "java-backend-tinkoff-2024");
        assertThat(responseGitHub.title()).isEqualTo("java-backend-tinkoff-2024");
        assertThat(responseGitHub.lastUpdated()).isEqualTo(time);
    }

    @Test
    public void failResult() {
        setStubBadResult();
        IGithubWebClient githubClient = new GithubClient("http://localhost:8088");
        assertThrows(WebClientResponseException.class, () -> githubClient.fetch("qwerty142", "java-backend-tinkoff-2024"));
    }
}
